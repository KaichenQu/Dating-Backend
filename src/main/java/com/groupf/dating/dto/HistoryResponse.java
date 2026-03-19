package com.groupf.dating.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class HistoryResponse {
    private List<SavedItemResponse> savedBios;
    private List<SavedItemResponse> savedStarters;
}
