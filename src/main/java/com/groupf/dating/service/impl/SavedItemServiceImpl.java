package com.groupf.dating.service.impl;

import com.groupf.dating.dto.HistoryResponse;
import com.groupf.dating.dto.SavedItemResponse;
import com.groupf.dating.model.SavedItem;
import com.groupf.dating.repository.SavedItemRepository;
import com.groupf.dating.service.SavedItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SavedItemServiceImpl implements SavedItemService {

    private final SavedItemRepository savedItemRepository;

    @Override
    public SavedItemResponse saveBio(String userId, String content) {
        return save(userId, content, SavedItem.ItemType.BIO);
    }

    @Override
    public SavedItemResponse saveStarter(String userId, String content) {
        return save(userId, content, SavedItem.ItemType.STARTER);
    }

    @Override
    public HistoryResponse getHistory(String userId) {
        List<SavedItemResponse> bios = savedItemRepository
                .findByUserIdAndTypeOrderByCreatedAtDesc(userId, SavedItem.ItemType.BIO)
                .stream().map(SavedItemResponse::from).toList();
        List<SavedItemResponse> starters = savedItemRepository
                .findByUserIdAndTypeOrderByCreatedAtDesc(userId, SavedItem.ItemType.STARTER)
                .stream().map(SavedItemResponse::from).toList();
        return new HistoryResponse(bios, starters);
    }

    @Override
    public void deleteItem(UUID id, String userId) {
        SavedItem item = savedItemRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        savedItemRepository.delete(item);
    }

    private SavedItemResponse save(String userId, String content, SavedItem.ItemType type) {
        SavedItem item = new SavedItem();
        item.setUserId(userId);
        item.setContent(content);
        item.setType(type);
        item.setCreatedAt(LocalDateTime.now());
        return SavedItemResponse.from(savedItemRepository.save(item));
    }
}
