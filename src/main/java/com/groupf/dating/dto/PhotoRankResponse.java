package com.groupf.dating.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhotoRankResponse {
    private List<RankedPhoto> rankedPhotos;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RankedPhoto {
        private String photoName;
        private int rank;
        private double score;
        private String reasoning;
        private String base64Image; // Optional: include for display
    }
}
