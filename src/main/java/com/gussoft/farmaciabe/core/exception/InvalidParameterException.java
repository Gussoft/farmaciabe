package com.gussoft.farmaciabe.core.exception;

import lombok.Getter;

@Getter
public class InvalidParameterException extends RuntimeException {

    private static final String EXCEPTION_ID = "SVC1001";
    private String paramName;
    private String paramValue;

    public InvalidParameterException(String paramName, Object paramValue, String message) {
        super(message);
        this.paramName = paramName;
        this.paramValue = paramValue.toString();
    }

    public String getExceptionId() {
        return EXCEPTION_ID;
    }
}
