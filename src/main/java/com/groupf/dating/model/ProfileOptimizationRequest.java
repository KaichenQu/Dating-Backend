package com.groupf.dating.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "profile_requests")
public class ProfileOptimizationRequest {

    @Id
    private String id;

    private String userId;
    private String originalBio;
    private List<String> photoUrls;
    private String tonePreference; // casual, professional, bold

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Results
    private List<String> rewrittenBios;
    private List<PhotoRanking> rankedPhotos;
    private List<String> conversationStarters;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PhotoRanking {
        private String photoUrl;
        private int rank;
        private double score;
        private String reasoning;
    }
}
