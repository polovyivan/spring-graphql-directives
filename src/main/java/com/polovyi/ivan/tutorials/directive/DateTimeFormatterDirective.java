package com.polovyi.ivan.tutorials.directive;

import graphql.GraphQLError;
import graphql.Scalars;
import graphql.schema.GraphQLDirective;
import graphql.schema.GraphQLInputType;
import graphql.validation.constraints.AbstractDirectiveConstraint;
import graphql.validation.constraints.Documentation;
import graphql.validation.rules.ValidationEnvironment;
import org.apache.commons.validator.GenericValidator;

import java.util.Collections;
import java.util.List;

public class DateTimeFormatterDirective extends AbstractDirectiveConstraint {


    public DateTimeFormatterDirective() {
        super("DateTimeFormatter");
    }

    @Override
    protected List<GraphQLError> runConstraint(ValidationEnvironment validationEnvironment) {
        Object validatedValue = validationEnvironment.getValidatedValue();

        String date = String.valueOf(validatedValue);

        GraphQLDirective directive = validationEnvironment.getContextObject(GraphQLDirective.class,
                new Object[0]);

        String format = getStrArg(directive, "format");


        boolean isValid = GenericValidator.isDate(date, format, true);

        if (isValid) {
            return Collections.emptyList();
        }
        return mkError(validationEnvironment, new Object[0]);

    }

    public boolean appliesToType(GraphQLInputType inputType) {
        return isStringOrIDOrList(inputType);
    }

    @Override
    protected boolean appliesToListElements() {
        return true;
    }

    @Override
    public Documentation getDocumentation() {
        return Documentation.newDocumentation()
                .messageTemplate(getMessageTemplate())
                .description(
                        "The String must be formatted in a specified way.")
                .example("updateAccident( accidentDate : String @DateTimeFormatter) : DriverDetails")
                .applicableTypeNames(new String[] { Scalars.GraphQLString.getName(), Scalars.GraphQLID.getName(), "Lists"
                        }).directiveSDL(
                        "directive @DateTimeFormatter(message : String = \"%s\") on ARGUMENT_DEFINITION | INPUT_FIELD_DEFINITION",
                        new Object[] {
                                getMessageTemplate()
                        }).build();

    }
}

