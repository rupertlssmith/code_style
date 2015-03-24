package org.codestyle.errorhandling.yes.errors;

/**
 * CheckedSystemException is useful when you want to be absolutely sure of where errors can occur in your code. By
 * making them checked, they must appear in method signatures, so you always know when they can happen.
 */
public class CheckedSystemException extends Exception implements SystemError {
    private final String errorCode;

    public CheckedSystemException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
