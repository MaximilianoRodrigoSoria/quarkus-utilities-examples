package ar.com.laboratory.util;

public class Constants {

    public static final String ARRAY_RESPONSE_PERSONADTO = "[{ \"nombre\": \"Juan\", \"apellido\": \"Perez\", \"edad\": 30 }, { \"nombre\": \"Maria\", \"apellido\": \"Gomez\", \"edad\": 25 }]";

    public static final String RESPONSE_PERSONADTO = "{ \"nombre\": \"Juan\", \"apellido\": \"Perez\", \"edad\": 30 }";
    public static final String ERROR_DESCRIPTION_500 = "Invalid request";

    public static final String ERROR_DESCRIPTION_404 = "Not Found";

    public static final String ERROR_DESCRIPTION_409 = "Conflict";

    public static final String ERROR_DESCRIPTION_400 = "Bad Request";

    public static final String ERROR_DESCRIPTION_401 = "Unauthorized";

    public static final String ERROR_DESCRIPTION_403 = "Forbidden";

    public static final String ERROR_CODE_500 = "500";

    public static final String ERROR_CODE_404 = "404";

    public static final String ERROR_CODE_409 = "409";

    public static final String ERROR_CODE_400 = "400";
    public static final String ERROR_RESPONSE_500 =  "{ \"status\": \"error\", \"code\": \"500\", \"message\": \"Internal Server Error\" }";

    public static final String ERROR_RESPONSE_404 =  "{ \"status\": \"error\", \"code\": \"404\", \"message\": \"Not Found\" }";

    public static final String ERROR_RESPONSE_409 =  "{ \"status\": \"error\", \"code\": \"409\", \"message\": \"Conflict\" }";

    public static final String ERROR_RESPONSE_400 =  "{\n" +
            "  \"classViolations\": [],\n" +
            "  \"parameterViolations\": [\n" +
            "    {\n" +
            "      \"constraintType\": \"PARAMETER\",\n" +
            "      \"message\": \"El nombre no puede estar vacío\",\n" +
            "      \"path\": \"create.personaDTO.nombre\",\n" +
            "      \"value\": \"\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"constraintType\": \"PARAMETER\",\n" +
            "      \"message\": \"La edad no puede ser nula\",\n" +
            "      \"path\": \"create.personaDTO.edad\",\n" +
            "      \"value\": \"\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"propertyViolations\": [],\n" +
            "  \"returnValueViolations\": []\n" +
            "}";

    public static final String ERROR_RESPONSE_401 =  "{ \"status\": \"error\", \"code\": \"401\", \"message\": \"Unauthorized\" }";

    public static final String ERROR_RESPONSE_403 =  "{ \"status\": \"error\", \"code\": \"403\", \"message\": \"Forbidden\" }";

    public static final String APPLICATION_JSON =  "application/json";
}
