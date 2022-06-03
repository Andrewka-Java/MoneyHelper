/*
 *   Developed by Andrei Muryn© 2022
 */

package com.moneyhelper.util;

public final class Constant {

    public static final String ACCESS_TOKEN_HEADER = "Access-token";
    public static final String REFRESH_TOKEN_HEADER = "Refresh-token";
    public static final String ACCESS_TOKEN_FUNCTION = "accessTokenFunction";
    public static final String REFRESH_TOKEN_FUNCTION = "refreshTokenFunction";
    public static final String REFRESH_TOKEN_CONTEXT_PATH = "/refresh-token";
    public static final String LOGIN_CONTEXT_PATH = "/login";
    public static final String CLAIMS_ATTRIBUTE = "claims";
    public static final String ROLE_PREFIX = "ROLE_";
    public static final int BCRYPT_POWER = 5;


    private Constant() {
    }

}
