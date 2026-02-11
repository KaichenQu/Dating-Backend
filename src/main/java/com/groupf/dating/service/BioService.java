package com.groupf.dating.service;

import com.groupf.dating.dto.BioRewriteRequest;
import com.groupf.dating.dto.BioRewriteResponse;
import reactor.core.publisher.Mono;

/**
 * Service interface for bio rewriting functionality
 */
public interface BioService {

    /**
     * Rewrites a bio into multiple versions based on the specified tone
     *
     * @param request the bio rewrite request containing the original bio and tone preference
     * @return Mono containing the bio rewrite response with multiple versions
     */
    Mono<BioRewriteResponse> rewriteBio(BioRewriteRequest request);
}
