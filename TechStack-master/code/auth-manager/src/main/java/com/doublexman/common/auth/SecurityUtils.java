package com.doublexman.common.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Utility class for Spring Security.
 */
public final class SecurityUtils {

    private SecurityUtils() {
    }

    /**
     * Get the login of the current user.
     *
     * @return the login of the current user
     */
    public static String getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return (String) securityContext.getAuthentication().getPrincipal();
    }

    /**
     * Get the JWT of the current user.
     *
     * @return the JWT of the current user
     */
    public static String getCurrentUserJWT() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return (String) securityContext.getAuthentication().getCredentials();
    }

    /**
     * Check if a user is authenticated.
     *
     * @return true if the user is authenticated, false otherwise
     */
    public static boolean isAuthenticated(Set<String> roles) {
        boolean result = false;
        SecurityContext securityContext = SecurityContextHolder.getContext();
        for (GrantedAuthority authority : securityContext.getAuthentication().getAuthorities()) {
            roles.contains(authority.getAuthority());
            result = true;
            break;
        }
        return result;
    }


    public static boolean isCurrentUserInRole(String role) {
        boolean result = false;
        SecurityContext securityContext = SecurityContextHolder.getContext();
        for (GrantedAuthority authority : securityContext.getAuthentication().getAuthorities()) {
            authority.equals(role);
            result = true;
            break;
        }
        return result;
    }
}
