package br.com.blinkdev.leadsponge.config;

import br.com.blinkdev.leadsponge.config.property.LeadSpongeApiProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Autowired
    private LeadSpongeApiProperty leadSpongeApiProperty;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build()
                .securitySchemes(Collections.singletonList(securitySchema()))
                .securityContexts(Collections.singletonList(securityContext()))
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo());
    }

    private OAuth securitySchema() {
        List<AuthorizationScope> authorizationScopeList = new ArrayList<>();
        List<GrantType> grantTypes = new ArrayList<>();
        GrantType creGrant = new ResourceOwnerPasswordCredentialsGrant(leadSpongeApiProperty.getOauth2().getAccessTokenUri());
        grantTypes.add(creGrant);
        return new OAuth("oauth2schema", authorizationScopeList, grantTypes);
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.any()).build();
    }

    private List<SecurityReference> defaultAuth() {
        final AuthorizationScope[] authorizationScopes = new AuthorizationScope[0];
        return Collections.singletonList(new SecurityReference("oauth2schema", authorizationScopes));
    }

    @Bean
    public SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder().clientId(leadSpongeApiProperty.getOauth2().getClientId()).clientSecret(leadSpongeApiProperty.getOauth2().getClientSecretDecript()).realm("").appName("Lead Spong").scopeSeparator(",")
                .useBasicAuthenticationWithAccessCodeGrant(false).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Lead Spong").description("CRM").termsOfServiceUrl("https://www.linkedin.com/in/danton-issler-rodrigues-8ba01a115/")
                .contact(new Contact("Danton Issler Rodrigues", "https://www.linkedin.com/in/danton-issler-rodrigues-8ba01a115/", "danton.issler18@gmail.com")).license("Todos os direitos reservados.")
                .licenseUrl("https://www.linkedin.com/in/danton-issler-rodrigues-8ba01a115/").version("1.0.0").build();
    }
}
