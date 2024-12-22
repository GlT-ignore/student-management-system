package com.studentmanager.util;

import com.studentmanager.exception.ValidationException;

public class StringValidator {
    private StringValidator() {
        // Private constructor to prevent instantiation
    }

    public static void validateEmail(String email) throws ValidationException {
        if (email == null || !email.contains("@")) {
            throw new ValidationException("Invalid email format");
        }
    }

    public static String formatName(String name) {
        return name.trim().substring(0, 1).toUpperCase() + 
               name.trim().substring(1).toLowerCase();
    }
} 