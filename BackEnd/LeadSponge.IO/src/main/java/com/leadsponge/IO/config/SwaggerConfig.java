package com.leadsponge.IO.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.leadsponge.IO.config.property.LeadSpongeApiProperty;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.OAuth;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
//@ComponentScan(basePackages = "com.leadsponge.IO")
public class SwaggerConfig {

	@Autowired
	private LeadSpongeApiProperty property;

	@Bean
	public Docket api() {
//		List<ResponseMessage> list = new ArrayList<>();
//		list.add(new ResponseMessageBuilder().code(500).message("500 message").responseModel(new ModelRef("Result")).build());
//		list.add(new ResponseMessageBuilder().code(401).message("Unauthorized").responseModel(new ModelRef("Result")).build());
//		list.add(new ResponseMessageBuilder().code(406).message("Not Acceptable").responseModel(new ModelRef("Result")).build());
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build()
				.securitySchemes(Collections.singletonList(securitySchema()))
				.securityContexts(Collections.singletonList(securityContext()))
				.useDefaultResponseMessages(false)
				.apiInfo(apiInfo())
//				.globalResponseMessage(RequestMethod.GET, list).globalResponseMessage(RequestMethod.POST, list)
		;
	}

	private OAuth securitySchema() {
		List<AuthorizationScope> authorizationScopeList = new ArrayList<>();
		authorizationScopeList.add(new AuthorizationScope("read", "read all"));
		authorizationScopeList.add(new AuthorizationScope("trust", "trust all"));
		authorizationScopeList.add(new AuthorizationScope("write", "access all"));
		List<GrantType> grantTypes = new ArrayList<>();
		GrantType creGrant = new ResourceOwnerPasswordCredentialsGrant(property.getOauth2().getAccessTokenUri());
		grantTypes.add(creGrant);
		return new OAuth("oauth2schema", authorizationScopeList, grantTypes);
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.any()).build();
	}

	private List<SecurityReference> defaultAuth() {
		final AuthorizationScope[] authorizationScopes = new AuthorizationScope[3];
		authorizationScopes[0] = new AuthorizationScope("read", "read all");
		authorizationScopes[1] = new AuthorizationScope("trust", "trust all");
		authorizationScopes[2] = new AuthorizationScope("write", "write all");
		return Collections.singletonList(new SecurityReference("oauth2schema", authorizationScopes));
	}

	@Bean
	public SecurityConfiguration security() {
		return SecurityConfigurationBuilder.builder().clientId(property.getOauth2().getClientId()).clientSecret(property.getOauth2().getClientSecret()).realm("").appName("Lead Spong").scopeSeparator(",")
				.useBasicAuthenticationWithAccessCodeGrant(false).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Lead Spong").description("CRM").termsOfServiceUrl("https://www.linkedin.com/in/danton-issler-rodrigues-8ba01a115/")
				.contact(new Contact("Danton Issler Rodrigues", "https://www.linkedin.com/in/danton-issler-rodrigues-8ba01a115/", "danton.issler18@gmail.com")).license("Todos os direitos reservados.")
				.licenseUrl("https://www.linkedin.com/in/danton-issler-rodrigues-8ba01a115/").version("1.0.0").build();
	}
}
