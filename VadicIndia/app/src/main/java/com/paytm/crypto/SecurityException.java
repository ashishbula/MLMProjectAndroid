// 
// Decompiled by Procyon v0.5.36
// 

package com.paytm.crypto;

import java.io.PrintStream;

public class SecurityException extends Exception
{
    private static final long serialVersionUID = -3956900350777254445L;
    private String errorCode;
    private String errorMessage;
    private Exception exception;
    
    public SecurityException(final String errorCode, final String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    
    public SecurityException(final String errorMessage) {
        super(errorMessage);
    }
    
    public SecurityException(final Throwable cause) {
        super(cause);
    }
    
    public SecurityException(final String errorMessage, final Throwable cause) {
        super(errorMessage, cause);
    }
    
    public SecurityException(final String errorMessage, final Exception exception) {
        this.errorMessage = errorMessage;
        this.exception = exception;
    }
    
    public String getErrorCode() {
        return this.errorCode;
    }
    
    public void setErrorCode(final String errorCode) {
        this.errorCode = errorCode;
    }
    
    public String getErrorMessage() {
        return this.errorMessage;
    }
    
    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    @Override
    public void printStackTrace(final PrintStream stream) {
        if (this.exception != null) {
            this.exception.printStackTrace(stream);
        }
    }
}
