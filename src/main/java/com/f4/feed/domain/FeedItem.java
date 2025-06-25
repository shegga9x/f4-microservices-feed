package com.f4.feed.domain;

import com.f4.feed.domain.enumeration.FeedVisibility;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

/**
 * A FeedItem.
 */
@Entity
@Table(name = "feed_item")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "feeditem")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FeedItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "id", length = 36, nullable = false)
    private UUID id;

    @NotNull
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "user_id", length = 36, nullable = false)
    private UUID userId;

    @Lob
    @Column(name = "content")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String content;

    @Column(name = "image_url")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String imageUrl;

    @Column(name = "video_url")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String videoUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "visibility")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Keyword)
    private FeedVisibility visibility;

    @Column(name = "location")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Text)
    private String location;

    @Column(name = "likes_count")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Integer)
    private Integer likesCount;

    @Column(name = "comments_count")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Integer)
    private Integer commentsCount;

    @Column(name = "shares_count")
    @org.springframework.data.elasticsearch.annotations.Field(type = org.springframework.data.elasticsearch.annotations.FieldType.Integer)
    private Integer sharesCount;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public UUID getId() {
        return this.id;
    }

    public FeedItem id(UUID id) {
        this.setId(id);
        return this;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return this.userId;
    }

    public FeedItem userId(UUID userId) {
        this.setUserId(userId);
        return this;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getContent() {
        return this.content;
    }

    public FeedItem content(String content) {
        this.setContent(content);
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public FeedItem imageUrl(String imageUrl) {
        this.setImageUrl(imageUrl);
        return this;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoUrl() {
        return this.videoUrl;
    }

    public FeedItem videoUrl(String videoUrl) {
        this.setVideoUrl(videoUrl);
        return this;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public FeedVisibility getVisibility() {
        return this.visibility;
    }

    public FeedItem visibility(FeedVisibility visibility) {
        this.setVisibility(visibility);
        return this;
    }

    public void setVisibility(FeedVisibility visibility) {
        this.visibility = visibility;
    }

    public String getLocation() {
        return this.location;
    }

    public FeedItem location(String location) {
        this.setLocation(location);
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getLikesCount() {
        return this.likesCount;
    }

    public FeedItem likesCount(Integer likesCount) {
        this.setLikesCount(likesCount);
        return this;
    }

    public void setLikesCount(Integer likesCount) {
        this.likesCount = likesCount;
    }

    public Integer getCommentsCount() {
        return this.commentsCount;
    }

    public FeedItem commentsCount(Integer commentsCount) {
        this.setCommentsCount(commentsCount);
        return this;
    }

    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }

    public Integer getSharesCount() {
        return this.sharesCount;
    }

    public FeedItem sharesCount(Integer sharesCount) {
        this.setSharesCount(sharesCount);
        return this;
    }

    public void setSharesCount(Integer sharesCount) {
        this.sharesCount = sharesCount;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public FeedItem createdAt(Instant createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return this.updatedAt;
    }

    public FeedItem updatedAt(Instant updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FeedItem)) {
            return false;
        }
        return getId() != null && getId().equals(((FeedItem) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FeedItem{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", content='" + getContent() + "'" +
            ", imageUrl='" + getImageUrl() + "'" +
            ", videoUrl='" + getVideoUrl() + "'" +
            ", visibility='" + getVisibility() + "'" +
            ", location='" + getLocation() + "'" +
            ", likesCount=" + getLikesCount() +
            ", commentsCount=" + getCommentsCount() +
            ", sharesCount=" + getSharesCount() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
