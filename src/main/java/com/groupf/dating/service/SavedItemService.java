package com.groupf.dating.service;

import com.groupf.dating.dto.HistoryResponse;
import com.groupf.dating.dto.SavedItemResponse;

import java.util.UUID;

public interface SavedItemService {
    SavedItemResponse saveBio(String userId, String content);
    SavedItemResponse saveStarter(String userId, String content);
    HistoryResponse getHistory(String userId);
    void deleteItem(UUID id, String userId);
}
