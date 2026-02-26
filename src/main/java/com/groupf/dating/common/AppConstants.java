package com.groupf.dating.common;

import java.util.Set;

public class AppConstants {

    // Bio constraints
    public static final int BIO_MAX_LENGTH = 500;
    public static final int BIO_MIN_LENGTH = 10;
    public static final int BIO_REWRITE_COUNT = 3;

    // Photo constraints
    public static final int PHOTO_MIN_COUNT = 2;
    public static final int PHOTO_MAX_COUNT = 5;
    public static final long PHOTO_MAX_SIZE_MB = 10;
    public static final long PHOTO_MAX_SIZE_BYTES = PHOTO_MAX_SIZE_MB * 1024 * 1024;
    public static final Set<String> ALLOWED_IMAGE_TYPES = Set.of(
        "image/jpeg",
        "image/jpg",
        "image/png"
    );
    public static final Set<String> ALLOWED_IMAGE_EXTENSIONS = Set.of(
        "jpg", "jpeg", "png"
    );

    // Conversation starters
    public static final int CONVERSATION_STARTER_MIN_COUNT = 3;
    public static final int CONVERSATION_STARTER_MAX_COUNT = 5;

    private AppConstants() {
        throw new UnsupportedOperationException("This cannot be instantiated");
    }
}
