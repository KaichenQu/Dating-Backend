package com.groupf.dating.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversationStarterResponse {
    private String bio;
    private List<String> starters;
    private String tone;
}
