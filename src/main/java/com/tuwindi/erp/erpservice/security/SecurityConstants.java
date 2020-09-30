package com.tuwindi.erp.erpservice.security;

public class SecurityConstants {
    public static final String AUTH_LOGIN_URL = "/api/auth/login";
    public static final String AUTH_LOGOUT_URL = "/api/auth/logout";
    public static final String USER_SAVE_URL = "/api/user/save";
    public static final String DOWNLOAD_URL = "/api/budget-lines/download/{id}";
    public static final String ACCESS_DENIED = "/acces-denied";

    // Signing key for HS512 algorithm
    // You can use the page https://mkjwk.org/ to generate all kinds of keys
    public static final String JWT_SECRET = "tuwindiong";

    // JWT token defaults
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "secure-api";
    public static final String TOKEN_AUDIENCE = "secure-app";

    private SecurityConstants() {
        throw new IllegalStateException("Impossible de créer une instance de la classe util statique");
    }
}
