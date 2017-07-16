package com.aurora.sample.configuration;

import com.fasterxml.classmate.TypeResolver;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import java.time.*;
import static springfox.documentation.schema.AlternateTypeRules.newRule;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * <p>
 * <p>
 * <p>
 * <p>
 * @author cjrequena
 * @version 1.0
 * @since JDK1.8
 * @see
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    /** The type resolver. */
    @Autowired
    private TypeResolver typeResolver;

    /**
     * Docket factory.
     *
     * @return the docket
     */
    @Bean
    public Docket docketFactory() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(metadata())
            .directModelSubstitute(LocalDate.class, String.class)
            .directModelSubstitute(LocalDateTime.class, String.class)
            .directModelSubstitute(LocalTime.class, String.class)
            .directModelSubstitute(OffsetDateTime.class, String.class)
            .directModelSubstitute(OffsetTime.class, String.class)
            .genericModelSubstitutes(ResponseEntity.class)
            .alternateTypeRules(
                newRule(typeResolver.resolve(DeferredResult.class,
                    typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
                    typeResolver.resolve(WildcardType.class)))
            .useDefaultResponseMessages(false)
            .select()
            .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
            .paths(PathSelectors.any())
            .build();
    }

    /**
     * Metadata.
     *
     * @return the api info
     */
    @Bean
    public ApiInfo metadata() {
        return new ApiInfoBuilder()
            .title("PROJECT NAME")
            .description("PROJECT DESCRIPTION")
            .version("1.0")
            .build();
    }

    /**
     * Swagger ui config.
     *
     * @return the ui configuration
     */
    @Bean
    public UiConfiguration swaggerUiConfig() {
        return new UiConfiguration(null);
    }

}
