package org.gesart.gesart.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfig {
    /**
     * Bean de configuration d'Open API.
     *
     * @return une instance OpenAPI
     */
    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .description("Server key to authenticate.")
                                .scheme("bearer").bearerFormat("JWT"))
                )
               // .addSecurityItem(new SecurityRequirement().addList("basic"))
                .info(new Info()
                        .title("E.RECRUTEMENT API")
                        .description("Documentation des APIs Restful.")
                        .version("1.0.0"));
    }
}
