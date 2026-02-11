package com.groupf.dating.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversationStarterRequest {

    @NotBlank(message = "Bio cannot be empty")
    @Size(max = 500, message = "Bio must be 500 characters or less")
    private String bio;

    private String tone; // bold, polite, concise
}
