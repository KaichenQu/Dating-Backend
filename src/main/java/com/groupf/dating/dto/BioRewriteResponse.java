package com.groupf.dating.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BioRewriteResponse {
    private String originalBio;
    private List<String> rewrittenBios;
    private String tone;
}
