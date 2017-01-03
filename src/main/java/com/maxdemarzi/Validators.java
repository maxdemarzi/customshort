package com.maxdemarzi;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Validators {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static HashMap getValidInput(String body) throws IOException {
        HashMap input;

        if ( body == null) {
            throw Exceptions.invalidInput;
        }

        // Parse the input
        try {
            input = objectMapper.readValue(body, HashMap.class);
        } catch (Exceptions e) {
            throw Exceptions.invalidInput;
        }

        if (!input.containsKey("from")) {
            throw Exceptions.missingFromParameter;
        } else {
            if(input.get("from").toString().isEmpty()) {
                throw Exceptions.emptyFromParameter;
            }
        }

        if (!input.containsKey("to")) {
            throw Exceptions.missingToParameter;
        } else {
            if(input.get("to").toString().isEmpty()) {
                throw Exceptions.emptyToParameter;
            }
        }

        if (!input.containsKey("types")) {
            throw Exceptions.missingTypesParameter;
        } else {
            Object statements = input.get("types");
            if (statements instanceof List<?>) {
                if (((List) statements).isEmpty()) {
                    throw Exceptions.emptyTypesParameter;
                }
            } else {
                throw Exceptions.invalidStatementsParameter;
            }
        }
        return input;
    }
}
