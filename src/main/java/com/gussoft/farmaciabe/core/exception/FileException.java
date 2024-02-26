package com.gussoft.farmaciabe.core.exception;

@SuppressWarnings("serial")
public class FileException extends Exception {

    private static final String EXCEPTION_ID = "400";

    public FileException(String msg) {
        super(msg);
    }

    public String getExceptionId() {
        return EXCEPTION_ID;
    }

}
