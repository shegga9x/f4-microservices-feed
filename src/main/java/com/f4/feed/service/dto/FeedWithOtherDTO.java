package com.f4.feed.service.dto;

public class FeedWithOtherDTO {
    private FeedItemDTO feedItem;;
    private String userName;
    private String userAvatar;
    private Long likeCount;
    private Long commentCount;
    private Long shareCount;

    public FeedItemDTO getFeedItem() {
        return feedItem;
    }

    public void setFeedItem(FeedItemDTO feedItem) {
        this.feedItem = feedItem;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
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

    @Override
    public String toString() {
        return "FeedWithOtherDTO [feedItem=" + feedItem + ", userName=" + userName + ", userAvatar=" + userAvatar
                + ", likeCount=" + likeCount + ", commentCount=" + commentCount + ", shareCount=" + shareCount + "]";
    }

}
