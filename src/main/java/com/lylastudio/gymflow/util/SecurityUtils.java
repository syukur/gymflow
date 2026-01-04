package com.lylastudio.gymflow.util;

import com.lylastudio.gymflow.security.AppUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static AppUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof AppUser) {
            return (AppUser) authentication.getPrincipal();
        }
        throw new RuntimeException("No authenticated user found");
    }

    public static String getCurrentCompanyId() {
        AppUser user = getCurrentUser();
        return user.getCompanyId();
    }
}