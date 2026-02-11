package com.groupf.dating.service;

import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Service interface for Claude API integration
 */
public interface ClaudeApiService {

    /**
     * Calls Claude API with text-only messages
     *
     * @param systemPrompt the system prompt to set the context
     * @param userPrompt the user prompt with the actual request
     * @return Mono containing the API response text
     */
    Mono<String> callClaudeApi(String systemPrompt, String userPrompt);

    /**
     * Calls Claude API with vision (images + text)
     *
     * @param systemPrompt the system prompt to set the context
     * @param userPrompt the user prompt with the actual request
     * @param images list of images to analyze
     * @return Mono containing the API response text
     */
    Mono<String> callClaudeApiWithVision(String systemPrompt, String userPrompt, List<ImageContent> images);

    /**
     * Image content holder for vision API
     */
    class ImageContent {
        private final String base64Data;
        private final String mediaType;

        public ImageContent(String base64Data, String mediaType) {
            this.base64Data = base64Data;
            this.mediaType = mediaType;
        }

        public String getBase64Data() {
            return base64Data;
        }

        public String getMediaType() {
            return mediaType;
        }
    }
}
