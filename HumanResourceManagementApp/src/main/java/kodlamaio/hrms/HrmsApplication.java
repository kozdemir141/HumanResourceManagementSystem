package kodlamaio.hrms;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.entities.Role;
import kodlamaio.hrms.core.entities.User;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import springfox.documentation.builders.PathSelectors;

import springfox.documentation.service.*;
import springfox.documentation.spi.service.contexts.SecurityContext;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
@EnableSwagger2
@Configuration
public class HrmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrmsApplication.class, args);
	}

//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2).select()
//				.apis(RequestHandlerSelectors.basePackage("kodlamaio.hrms")).build();
//	}
	
	public static final String AUTHORIZATION_HEADER = "Authorization";

    private ApiKey apiKey(){
        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }

    private ApiInfo apiInfo(){
        return new ApiInfo(
                "Spring Boot Blog REST APIs",
                "Spring Boot Blog REST API Documentation",
                "1",
                "Terms of service",
                new Contact("Ramesh Fadatare", "www.javaguides.net", "ramesh@gmail.com"),
                "License of API",
                "API license URL",
                Collections.emptyList()
        );
    }

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("kodlamaio.hrms"))
                .paths(PathSelectors.any())
                .build();
    }

    private SecurityContext securityContext(){
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth(){
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return arg -> {
			userService.saveRole(new Role(1, "ROLE_USER"));
			userService.saveRole(new Role(2, "ROLE_MANAGER"));
			userService.saveRole(new Role(3, "ROLE_ADMIN"));
			userService.saveRole(new Role(4, "ROLE_SUPER_ADMIN"));

			userService.saveUser(new User(1, "kozdemir141@outlook.com", "55431921", new ArrayList<>()));
			userService.saveUser(new User(2, "bozdemir141@outlook.com", "55431921", new ArrayList<>()));
			userService.saveUser(new User(3, "eozdemir141@outlook.com", "55431921", new ArrayList<>()));
			userService.saveUser(new User(4, "gozdemir141@outlook.com", "55431921", new ArrayList<>()));

			userService.addRoleToUser("kozdemir141@outlook.com", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("kozdemir141@outlook.com", "ROLE_ADMIN");
			userService.addRoleToUser("kozdemir141@outlook.com", "ROLE_USER");

			userService.addRoleToUser("bozdemir141@outlook.com", "ROLE_ADMIN");
			userService.addRoleToUser("bozdemir141@outlook.com", "ROLE_USER");

			userService.addRoleToUser("eozdemir141@outlook.com", "ROLE_MANAGER");
			userService.addRoleToUser("eozdemir141@outlook.com", "ROLE_USER");
		};
	}
}
