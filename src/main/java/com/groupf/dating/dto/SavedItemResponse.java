package com.groupf.dating.dto;

import com.groupf.dating.model.SavedItem;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SavedItemResponse {
    private UUID id;
    private String content;
    private LocalDateTime createdAt;

    public static SavedItemResponse from(SavedItem item) {
        SavedItemResponse r = new SavedItemResponse();
        r.setId(item.getId());
        r.setContent(item.getContent());
        r.setCreatedAt(item.getCreatedAt());
        return r;
    }
}
