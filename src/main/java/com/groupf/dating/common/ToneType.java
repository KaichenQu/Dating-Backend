package com.groupf.dating.common;

import lombok.Getter;

@Getter
public enum ToneType {
    CASUAL("casual", "friendly, relaxed, and conversational"),
    PROFESSIONAL("professional", "polished, mature, and sophisticated"),
    BOLD("bold", "confident, direct, and attention-grabbing"),
    POLITE("polite", "respectful, courteous, and warm"),
    CONCISE("concise", "brief, clear, and to the point");

    private final String value;
    private final String description;

    ToneType(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public static ToneType fromString(String value) {
        if (value == null || value.isEmpty()) {
            return CASUAL; // Default
        }
        for (ToneType tone : ToneType.values()) {
            if (tone.value.equalsIgnoreCase(value.trim())) {
                return tone;
            }
        }
        throw new IllegalArgumentException(value + " is not a valid ToneType, Acceptable values are: casual, professional, bold, polite, concise");
    }
}