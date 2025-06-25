package com.f4.feed.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class FeedItemTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static FeedItem getFeedItemSample1() {
        return new FeedItem()
            .id(UUID.fromString("23d8dc04-a48b-45d9-a01d-4b728f0ad4aa"))
            .userId(UUID.fromString("23d8dc04-a48b-45d9-a01d-4b728f0ad4aa"))
            .imageUrl("imageUrl1")
            .videoUrl("videoUrl1")
            .location("location1")
            .likesCount(1)
            .sharesCount(1);
    }

    public static FeedItem getFeedItemSample2() {
        return new FeedItem()
            .id(UUID.fromString("ad79f240-3727-46c3-b89f-2cf6ebd74367"))
            .userId(UUID.fromString("ad79f240-3727-46c3-b89f-2cf6ebd74367"))
            .imageUrl("imageUrl2")
            .videoUrl("videoUrl2")
            .location("location2")
            .likesCount(2)
            .sharesCount(2);
    }

    public static FeedItem getFeedItemRandomSampleGenerator() {
        return new FeedItem()
            .id(UUID.randomUUID())
            .userId(UUID.randomUUID())
            .imageUrl(UUID.randomUUID().toString())
            .videoUrl(UUID.randomUUID().toString())
            .location(UUID.randomUUID().toString())
            .likesCount(intCount.incrementAndGet())
            .sharesCount(intCount.incrementAndGet());
    }
}
