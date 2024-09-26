package com.alex.eko.paragon.security;

public class UtilsSecurity {

    public  final static String[] PUBLIC_ROUTES = {
            "/api/v1/eko-paragon/security/login",
            "/api/v1/eko-paragon/auth/refresh",
            "/api/v1/eko-paragon/receipts/**",

            "api/v1/eko-paragon/auth/pw/recovery",
            "api/v1/eko-paragon/auth/pw/recovery/**",
            "/actuator/**",

            "/api/v1/eko-paragon/security/users/register",
            "/api/v1/auth/pw/forgot",
            "/api/v1/auth/pw/recovery/**",
            "/api/core/get",
            "/swagger-ui/**",
            "/v3/api-docs/**",
    };



    public static final String SERVER = "http://localhost:9090/";

    public static  final String ENDPOINT_RECOVERY = SERVER + "api/v1/eko-paragon/auth/pw/recovery/";
    public static  final String ENDPOINT_ACTIVATE =  SERVER + "api/v1/eko-paragon/auth/activate/";
}
