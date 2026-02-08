package com.huuphuoc.webBH.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration



public class ConfigOpenAPI {


    @Bean
    public OpenAPI customOpenAPI() {
        String schemeName = "bearerAuth";
        String bearerFormat = "JWT";
        String scheme = "bearer";
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(schemeName);


        return new OpenAPI()
                .addSecurityItem(securityRequirement)
                .components(new Components()
                        .addSecuritySchemes(schemeName, new SecurityScheme()
                                .name(schemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme(scheme)
                                .bearerFormat(bearerFormat)
                        )
                )


                .components(new Components()
                .addSecuritySchemes(schemeName, new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP).scheme(scheme).bearerFormat(bearerFormat))).info(new Info()
                .title("Design by Phuoc Vo")
                .description("Create Swagger for Education Purpose").contact(new Contact()
                        .name("Phuoc Vo")
                        .email("vohuuphuocHCM@gmail.com")

                ).license(new License().name("NO LICENSE").url(""))
        ).externalDocs(new ExternalDocumentation().url(""));
    }
}
