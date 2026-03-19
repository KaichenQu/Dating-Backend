package com.groupf.dating.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SaveContentRequest {
    @NotBlank
    private String content;
}
