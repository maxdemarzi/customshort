package com.maxdemarzi;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class Exceptions extends WebApplicationException {
    public Exceptions(int code, String error)  {
        super(new Throwable(error), Response.status(code)
                .entity("{\"error\":\"" + error + "\"}")
                .type(MediaType.APPLICATION_JSON)
                .build());

    }
    public static Exceptions invalidInput = new Exceptions(400, "Invalid Input");

    public static Exceptions missingFromParameter = new Exceptions(400, "Missing from Parameter.");
    public static Exceptions missingToParameter = new Exceptions(400, "Missing to Parameter.");
    public static Exceptions missingTypesParameter = new Exceptions(400, "Missing types Parameter.");
    public static Exceptions emptyFromParameter = new Exceptions(400, "Empty from Parameter.");
    public static Exceptions emptyToParameter = new Exceptions(400, "Empty to Parameter.");
    public static Exceptions emptyTypesParameter = new Exceptions(400, "Empty types Parameter.");
    public static Exceptions invalidStatementsParameter = new Exceptions(400, "Invalid statements Parameter.");
}
