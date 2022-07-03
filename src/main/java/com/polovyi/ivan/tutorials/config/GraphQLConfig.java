package com.polovyi.ivan.tutorials.config;

import com.polovyi.ivan.tutorials.directive.DateTimeFormatterDirective;
import graphql.validation.rules.OnValidationErrorStrategy;
import graphql.validation.rules.ValidationRules;
import graphql.validation.schemawiring.ValidationSchemaWiring;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
@RequiredArgsConstructor
public class GraphQLConfig {

    private final CustomMessageInterpolator customMessageInterpolator;

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {

        ValidationRules validationRules = ValidationRules.newValidationRules()
                .onValidationErrorStrategy(OnValidationErrorStrategy.RETURN_NULL)
                .messageInterpolator(customMessageInterpolator)
                .addRule(new DateTimeFormatterDirective())
                .build();

        ValidationSchemaWiring schemaWiring = new ValidationSchemaWiring(validationRules);

        return builder -> builder.directiveWiring(schemaWiring).build();
    }

}
