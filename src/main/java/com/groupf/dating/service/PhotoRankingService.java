package com.groupf.dating.service;

import com.groupf.dating.dto.PhotoRankResponse;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

/**
 * Service interface for photo ranking functionality
 */
public interface PhotoRankingService {

    /**
     * Ranks photos for dating profile based on AI analysis
     *
     * @param photos array of photos to rank
     * @return Mono containing the photo rank response with rankings and reasoning
     */
    Mono<PhotoRankResponse> rankPhotos(MultipartFile[] photos);
}
