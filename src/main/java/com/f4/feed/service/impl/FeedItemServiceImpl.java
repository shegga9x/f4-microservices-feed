package com.f4.feed.service.impl;

import com.f4.feed.client.api.CommentResourceApi;
import com.f4.feed.client.api.LikeResourceApi;
import com.f4.feed.client.api.UserResourceApi;
import com.f4.feed.client.model.RedisUserDTO;
import com.f4.feed.client.model.UserDTO;
import com.f4.feed.domain.FeedItem;
import com.f4.feed.repository.FeedItemRepository;
import com.f4.feed.repository.search.FeedItemSearchRepository;
import com.f4.feed.service.FeedItemService;
import com.f4.feed.service.dto.FeedItemDTO;
import com.f4.feed.service.dto.FeedWithOtherDTO;
import com.f4.feed.service.mapper.FeedItemMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.f4.feed.domain.FeedItem}.
 */
@Service
@Transactional
public class FeedItemServiceImpl implements FeedItemService {

    private static final Logger LOG = LoggerFactory.getLogger(FeedItemServiceImpl.class);

    private final FeedItemRepository feedItemRepository;
    private final FeedItemMapper feedItemMapper;
    private final FeedItemSearchRepository feedItemSearchRepository;
    private final UserResourceApi userResourceApi;
    private final CommentResourceApi commentResourceApi;
    private final LikeResourceApi likeResourceApi;

    public FeedItemServiceImpl(
            FeedItemRepository feedItemRepository,
            FeedItemMapper feedItemMapper,
            FeedItemSearchRepository feedItemSearchRepository,
            UserResourceApi userResourceApi,
            CommentResourceApi commentResourceApi,
            LikeResourceApi likeResourceApi) {
        this.feedItemRepository = feedItemRepository;
        this.feedItemMapper = feedItemMapper;
        this.feedItemSearchRepository = feedItemSearchRepository;
        this.userResourceApi = userResourceApi;
        this.commentResourceApi = commentResourceApi;
        this.likeResourceApi = likeResourceApi;
    }

    @Override
    public FeedItemDTO save(FeedItemDTO feedItemDTO) {
        LOG.debug("Request to save FeedItem : {}", feedItemDTO);
        FeedItem feedItem = feedItemMapper.toEntity(feedItemDTO);
        feedItem = feedItemRepository.save(feedItem);
        feedItemSearchRepository.index(feedItem);
        return feedItemMapper.toDto(feedItem);
    }

    @Override
    public FeedItemDTO update(FeedItemDTO feedItemDTO) {
        LOG.debug("Request to update FeedItem : {}", feedItemDTO);
        FeedItem feedItem = feedItemMapper.toEntity(feedItemDTO);
        feedItem = feedItemRepository.save(feedItem);
        feedItemSearchRepository.index(feedItem);
        return feedItemMapper.toDto(feedItem);
    }

    @Override
    public Optional<FeedItemDTO> partialUpdate(FeedItemDTO feedItemDTO) {
        LOG.debug("Request to partially update FeedItem : {}", feedItemDTO);

        return feedItemRepository
                .findById(feedItemDTO.getId())
                .map(existingFeedItem -> {
                    feedItemMapper.partialUpdate(existingFeedItem, feedItemDTO);

                    return existingFeedItem;
                })
                .map(feedItemRepository::save)
                .map(savedFeedItem -> {
                    feedItemSearchRepository.index(savedFeedItem);
                    return savedFeedItem;
                })
                .map(feedItemMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FeedItemDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all FeedItems");
        return feedItemRepository.findAll(pageable).map(feedItemMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<FeedItemDTO> findOne(UUID id) {
        LOG.debug("Request to get FeedItem : {}", id);
        return feedItemRepository.findById(id).map(feedItemMapper::toDto);
    }

    @Override
    public void delete(UUID id) {
        LOG.debug("Request to delete FeedItem : {}", id);
        feedItemRepository.deleteById(id);
        feedItemSearchRepository.deleteFromIndexById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FeedItemDTO> search(String query, Pageable pageable) {
        LOG.debug("Request to search for a page of FeedItems for query {}", query);
        return feedItemSearchRepository.search(query, pageable).map(feedItemMapper::toDto);
    }

    @Override
    public Page<FeedWithOtherDTO> findFeedWithOther(String query, Pageable pageable) {
        LOG.debug("Request to find FeedItems with other data for query: {}", query);

        try {
            // Get the page of feed items
            Page<FeedItemDTO> feedItemPage = findAll(pageable);
            List<FeedItemDTO> feedItems = feedItemPage.getContent();

            // Transform each feed item to FeedWithOtherDTO
            List<FeedWithOtherDTO> feedWithOtherList = feedItems.stream()
                    .map(this::enrichFeedItemWithOtherData)
                    .collect(Collectors.toList());

            // Return as Page
            return new PageImpl<>(
                    feedWithOtherList,
                    pageable,
                    feedItemPage.getTotalElements());
        } catch (Exception e) {
            LOG.error("Error finding feed items with other data", e);
            return new PageImpl<>(new ArrayList<>(), pageable, 0);
        }
    }

    private FeedWithOtherDTO enrichFeedItemWithOtherData(FeedItemDTO feedItemDTO) {
        FeedWithOtherDTO feedWithOtherDTO = new FeedWithOtherDTO();

        try {
            feedWithOtherDTO.setFeedItem(feedItemDTO);

            // Use injected userResourceApi
            RedisUserDTO userDTO = userResourceApi.getUserFromRedis(feedItemDTO.getUserId());
            if (userDTO != null) {
                feedWithOtherDTO.setRedisUserDTO(userDTO);
            }

            try {
                Long commentCount = commentResourceApi.countByParentIdAndParentType1(feedItemDTO.getId(), "feed");
                feedWithOtherDTO.setCommentCount(commentCount != null ? commentCount : 0);
            } catch (Exception e) {
                LOG.warn("Failed to get comment count for feed item {}: {}", feedItemDTO.getId(), e.getMessage());
                feedWithOtherDTO.setCommentCount(0L);
            }

            try {
                Long likeCount = likeResourceApi.countByParentIdAndParentType(feedItemDTO.getId(), "feed");
                feedWithOtherDTO.setLikeCount(likeCount != null ? likeCount : 0);
            } catch (Exception e) {
                LOG.warn("Failed to get like count for feed item {}: {}", feedItemDTO.getId(), e.getMessage());
                feedWithOtherDTO.setLikeCount(0L);
            }

            feedWithOtherDTO.setShareCount(0L); // Placeholder

        } catch (Exception e) {
            LOG.error("Error enriching feed item {} with other data: {}", feedItemDTO.getId(), e.getMessage());
            feedWithOtherDTO.setFeedItem(feedItemDTO);
            feedWithOtherDTO.setCommentCount(0L);
            feedWithOtherDTO.setLikeCount(0L);
            feedWithOtherDTO.setShareCount(0L);
        }

        return feedWithOtherDTO;
    }

}
