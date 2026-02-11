package com.groupf.dating.common;

import java.util.Set;

public class AppConstants {

    // Bio constraints
    public static final int BIO_MAX_LENGTH = 500;
    public static final int BIO_MIN_LENGTH = 10;
    public static final int BIO_REWRITE_COUNT = 3;

    // Photo constraints
    public static final int PHOTO_MIN_COUNT = 1;
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

    // Error messages
    public static final String ERROR_BIO_TOO_SHORT = "Bio must be at least " + BIO_MIN_LENGTH + " characters";
    public static final String ERROR_BIO_TOO_LONG = "Bio must not exceed " + BIO_MAX_LENGTH + " characters";
    public static final String ERROR_INVALID_IMAGE_TYPE = "Only JPEG and PNG images are supported";
    public static final String ERROR_IMAGE_TOO_LARGE = "Image size must not exceed " + PHOTO_MAX_SIZE_MB + "MB";
    public static final String ERROR_TOO_FEW_PHOTOS = "At least " + PHOTO_MIN_COUNT + " photo is required";
    public static final String ERROR_TOO_MANY_PHOTOS = "Maximum " + PHOTO_MAX_COUNT + " photos allowed";

    private AppConstants() {
        // Prevent instantiation
    }
}
