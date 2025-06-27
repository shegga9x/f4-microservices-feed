package com.f4.feed.service.dto;

import com.f4.feed.client.model.RedisUserDTO;

public class FeedWithOtherDTO {
    private FeedItemDTO feedItem;;
    private RedisUserDTO redisUserDTO;
    private Long likeCount;
    private Long commentCount;
    private Long shareCount;

    public FeedItemDTO getFeedItem() {
        return feedItem;
    }

    public void setFeedItem(FeedItemDTO feedItem) {
        this.feedItem = feedItem;
    }

    public RedisUserDTO getRedisUserDTO() {
        return redisUserDTO;
    }

    public void setRedisUserDTO(RedisUserDTO redisUserDTO) {
        this.redisUserDTO = redisUserDTO;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    public Long getShareCount() {
        return shareCount;
    }

    public void setShareCount(Long shareCount) {
        this.shareCount = shareCount;
    }

}
