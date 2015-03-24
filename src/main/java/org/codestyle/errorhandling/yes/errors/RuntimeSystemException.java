package org.codestyle.errorhandling.yes.errors;

/**
 * RuntimeSystemException can be useful where the system cannot continue processing. Since there is no recovery
 * possible, the error may as well be a runtime, and will get logged by the top level error handler. For example, cannot
 * connect to database may be a show stopper for your application, there is no alternative.
 */
public class RuntimeSystemException extends RuntimeException implements SystemError {
    private final String errorCode;

    public RuntimeSystemException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
