package br.com.zucchicamila.java_spring_rest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
/* A anotação "@Configuration" é usada para marcar uma classe como uma fonte de configurações do Spring. Podemos pensar
 * nela como uma forma de dizer ao Spring que a classe contém definições de como os objetos (beans) devem ser criados
 * e configurados.  */
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        // Via QUERY PARAM http://localhost:8080/person/v1?mediaType=xml
            configurer.favorParameter(true)
                    .parameterName("mediaType")
                    .ignoreAcceptHeader(true)
                    .useRegisteredExtensionsOnly(false)
                    .defaultContentType(MediaType.APPLICATION_JSON)
                    .mediaType("json", MediaType.APPLICATION_JSON)
                    .mediaType("xml", MediaType.APPLICATION_XML);
    }

}