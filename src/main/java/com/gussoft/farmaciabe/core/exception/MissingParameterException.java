package com.gussoft.farmaciabe.core.exception;

@SuppressWarnings("serial")
public class MissingParameterException extends Exception {

    private String paramName;
    private String paramValue;

    public MissingParameterException(String paramName, String paramValue, String message) {
        super(message);
        this.paramName = paramName;
        this.paramValue = paramValue;
    }

}
