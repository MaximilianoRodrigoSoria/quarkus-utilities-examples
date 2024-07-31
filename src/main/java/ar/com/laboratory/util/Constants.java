package ar.com.laboratory.util;

public class Constants {

    public static final String ARRAY_RESPONSE_PERSONADTO = "[{ \"nombre\": \"Juan\", \"apellido\": \"Perez\", \"edad\": 30 }, { \"nombre\": \"Maria\", \"apellido\": \"Gomez\", \"edad\": 25 }]";

    public static final String RESPONSE_PERSONADTO = "{ \"nombre\": \"Juan\", \"apellido\": \"Perez\", \"edad\": 30 }";
    public static final String ERROR_DESCRIPTION_500 = "Invalid request";

    public static final String ERROR_CODE_500 = "500";

    public static final String ERROR_RESPONSE_500 =  "{ \"status\": \"error\", \"code\": \"500\", \"message\": \"Internal Server Error\" }";

    public static final String ERROR_RESPONSE_404 =  "{ \"status\": \"error\", \"code\": \"404\", \"message\": \"Not Found\" }";

    public static final String ERROR_RESPONSE_409 =  "{ \"status\": \"error\", \"code\": \"409\", \"message\": \"Conflict\" }";

    public static final String ERROR_RESPONSE_400 =  "{ \"status\": \"error\", \"code\": \"400\", \"message\": \"Bad Request\" }";

    public static final String ERROR_RESPONSE_401 =  "{ \"status\": \"error\", \"code\": \"401\", \"message\": \"Unauthorized\" }";

    public static final String ERROR_RESPONSE_403 =  "{ \"status\": \"error\", \"code\": \"403\", \"message\": \"Forbidden\" }";

    public static final String APPLICATION_JSON =  "application/json";
}
