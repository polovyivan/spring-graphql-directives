package com.polovyi.ivan.tutorials.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;

@Controller
public class DirectivesController {

    @QueryMapping
    public String directivesTutorial(@Argument String stringArgument, @Argument String patternArgument,
            @Argument String dateArgument) {

        return "stringArgument is << " + stringArgument + " >>, " +
                "patternArgument is << " + Integer.valueOf(patternArgument) + " >>, " +
                "dateArgument is << " + LocalDate.parse(dateArgument)+ " >>";
    }

}
