package com.f4.feed.handler.events;

import org.springframework.stereotype.Component;

import com.f4.feed.handler.EventHandler;
import com.f4.feed.service.FeedItemService;
import com.f4.feed.service.dto.FeedItemDTO;

@Component
public class PostReelHandler implements EventHandler<FeedItemDTO> {
    private final FeedItemService svc;

    public PostReelHandler(FeedItemService svc) {
        this.svc = svc;
    }

    public String getEventName() {
        return "postReel";
    }

    public void handle(FeedItemDTO dto) {
        svc.save(dto);
    }
}
