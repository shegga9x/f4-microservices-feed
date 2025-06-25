package com.f4.feed.service.dto;

import com.f4.feed.domain.enumeration.FeedVisibility;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.f4.feed.domain.FeedItem} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FeedItemDTO implements Serializable {

    @NotNull
    private UUID id;

    @NotNull
    private UUID userId;

    @Lob
    private String content;

    private String imageUrl;

    private String videoUrl;

    private FeedVisibility visibility;

    private String location;

    @NotNull
    private Instant createdAt;

    @NotNull
    private Instant updatedAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public FeedVisibility getVisibility() {
        return visibility;
    }

    public void setVisibility(FeedVisibility visibility) {
        this.visibility = visibility;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FeedItemDTO)) {
            return false;
        }

        FeedItemDTO feedItemDTO = (FeedItemDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, feedItemDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FeedItemDTO{" +
            "id='" + getId() + "'" +
            ", userId='" + getUserId() + "'" +
            ", content='" + getContent() + "'" +
            ", imageUrl='" + getImageUrl() + "'" +
            ", videoUrl='" + getVideoUrl() + "'" +
            ", visibility='" + getVisibility() + "'" +
            ", location='" + getLocation() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
