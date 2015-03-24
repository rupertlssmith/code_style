package org.codestyle.errorhandling.yes;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * WebMethodTopLevelErrorHandler provides consistent top-level error handling, for unhandled exceptions. It is used to
 * wrap HTTP request handlers.
 *
 * <p/>This is the only place in the application that 'log.error' should be called.
 *
 * <p/>This error handler is a less strict one; unhandled runtime errors are simply logged. Errors are re-thrown to
 * allow the web framework to handle them in its own way.
 */
public class WebMethodTopLevelErrorHandler implements TopLevelErrorHandler {
    private static final Logger LOG = Logger.getLogger(WebMethodTopLevelErrorHandler.class.getName());

    public void handleThrowable(Throwable t) {
        if (t instanceof Error) {
            printStackTraceAndExitError((Error) t);
        } else if (t instanceof RuntimeException) {
            logAndExitRuntime((RuntimeException) t);
        } else {
            logException((Exception) t);
        }
    }

    private static void printStackTraceAndExitError(Error e) {
        throw e;
    }

    private static void logAndExitRuntime(RuntimeException e) {
        LOG.log(Level.SEVERE, "", e);
    }

    private static void logException(Exception e) {
        LOG.log(Level.SEVERE, "", e);
    }
}
