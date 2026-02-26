package com.groupf.dating.common;

public class ApiConstants {

    // Claude API
    public static final String CLAUDE_API_ENDPOINT = "https://api.anthropic.com/v1/messages";
    public static final String CLAUDE_MODEL = "claude-sonnet-4-5-20250929";
    public static final String CLAUDE_API_VERSION = "2023-06-01";
    public static final int MAX_TOKENS = 1024;
    public static final double TEMPERATURE = 0.7;

    // Timeouts
    public static final int API_TIMEOUT_SECONDS = 30;
    public static final int CONNECTION_TIMEOUT_SECONDS = 10;

    // Headers
    public static final String HEADER_API_KEY = "x-api-key";
    public static final String HEADER_API_VERSION = "anthropic-version";
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String MEDIA_TYPE_JSON = "application/json";

    private ApiConstants() {
        // Prevent instantiation
    }
}