package com.groupf.dating.service;

import com.groupf.dating.dto.ConversationStarterRequest;
import com.groupf.dating.dto.ConversationStarterResponse;
import reactor.core.publisher.Mono;

/**
 * Service interface for conversation starter generation functionality
 */
public interface ConversationStarterService {

    /**
     * Generates conversation starters based on a bio
     *
     * @param request the conversation starter request containing the bio and tone preference
     * @return Mono containing the conversation starter response with multiple starters
     */
    Mono<ConversationStarterResponse> generateStarters(ConversationStarterRequest request);
}
