package com.f4.feed.kafka.handler.events;

import org.springframework.stereotype.Component;

import com.f4.feed.kafka.handler.EventHandler;
import com.f4.feed.service.ReelService;
import com.f4.feed.service.dto.ReelDTO;

@Component
public class PostReelHandler implements EventHandler<ReelDTO> {
    private final ReelService svc;

    public PostReelHandler(ReelService svc) {
        this.svc = svc;
    }

    public String getEventName() {
        return "postReel";
    }

    public void handle(ReelDTO dto) {
        svc.save(dto);
    }
}
