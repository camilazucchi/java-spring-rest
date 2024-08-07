package br.com.zucchicamila.java_spring_rest.utilities;

public class MediaType {

    public static final String APPLICATION_JSON = "application/json";
    public static final String APPLICATION_XML = "application/xml";
    public static final String APPLICATION_YAML = "application/x-yaml";

    private MediaType() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

}