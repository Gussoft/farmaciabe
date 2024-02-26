package com.gussoft.farmaciabe.core.exception;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends Exception {

    private final String statusCode = "404";

    private String identifier;

    public ResourceNotFoundException(String msg) {
        super(msg);
    }

    public ResourceNotFoundException(Object identifier, String msg) {
        super(msg);
        this.identifier = identifier.toString();
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getIdentifier() {
        return this.identifier;
    }

}
