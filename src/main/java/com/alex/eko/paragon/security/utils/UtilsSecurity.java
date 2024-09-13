package com.alex.eko.paragon.security.utils;

public class UtilsSecurity {

    public final static String[] PUBLIC_ROUTES = {


            "/api/v1/security/login",
            "/api/v1/security/user/register",
            "/api/v1/security/refresh",
            "/api/v1/security/pw/forgot",
            "/api/v1/security/pw/recovery/**",
            "/api/v1/security/activate/**",


            "/api/core/get",
            "/swagger-ui/**",
            "/v3/api-docs/**",
    };


    public static final String SERVER = "http://localhost:9098/";


    public static final String ENDPOINT_RECOVERY = SERVER + "api/v1/security/pw/recovery/";
    public static final String ENDPOINT_ACTIVATE = SERVER + "api/v1/security/activate/";
}

