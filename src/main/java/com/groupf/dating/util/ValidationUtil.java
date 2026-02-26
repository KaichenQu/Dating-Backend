package com.groupf.dating.util;

import com.groupf.dating.common.AppConstants;

public class ValidationUtil {

    private ValidationUtil() {
        // Prevent instantiation
    }

    /**
     * Validates photo count
     */
    public static boolean isValidPhotoCount(int count) {
        return count >= AppConstants.PHOTO_MIN_COUNT && count <= AppConstants.PHOTO_MAX_COUNT;
    }

    /**
     * Gets validation error message for photo count
     */
    public static String getPhotoCountValidationError(int count) {
        if (count < AppConstants.PHOTO_MIN_COUNT) {
            return AppConstants.ERROR_TOO_FEW_PHOTOS;
        }
        if (count > AppConstants.PHOTO_MAX_COUNT) {
            return AppConstants.ERROR_TOO_MANY_PHOTOS;
        }
        return null;
    }
}
