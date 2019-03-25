package com.sendgrid.helpers.mail.objects;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class ContentVerifier {
    private static final List<Pattern> FORBIDDEN_PATTERNS = Collections.singletonList(
            Pattern.compile(".*SG\\.[a-zA-Z0-9(-|_)]*\\.[a-zA-Z0-9(-|_)]*.*")
    );

    static void verifyContent(String content) {
        for (Pattern pattern : FORBIDDEN_PATTERNS) {
            if (pattern.matcher(content).matches()) {
                throw new IllegalArgumentException("Found a Forbidden Pattern in the content of the email");
            }
        }
    }
}