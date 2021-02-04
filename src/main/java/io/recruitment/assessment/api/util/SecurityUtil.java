package io.recruitment.assessment.api.util;

import io.recruitment.assessment.api.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
;

public class SecurityUtil {

    public static String getCurrentUserId() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .filter(details -> details instanceof CustomUserDetails)
                .map(details -> (CustomUserDetails) details)
                .map(CustomUserDetails::getUserId)
                .orElseThrow(() -> new IllegalStateException("Security context not properly populated!"));
    }

}
