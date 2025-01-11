package com.hungrycoder.spring.ValidationUtils;

import java.util.regex.*;

public final class EmailValidate {
    private static final Pattern VALID_GMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@gmail\\.com$", Pattern.CASE_INSENSITIVE);

    public static boolean validateEmail(String email) {
        Matcher matcher = VALID_GMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.matches();
    }
}
