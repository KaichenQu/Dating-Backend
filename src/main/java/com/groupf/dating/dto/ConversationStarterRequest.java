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
    @Size(min = 10, max = 500, message = "Bio must be between 10 and 500 characters")
    private String bio;

    private String tone; // bold, polite, concise
}
