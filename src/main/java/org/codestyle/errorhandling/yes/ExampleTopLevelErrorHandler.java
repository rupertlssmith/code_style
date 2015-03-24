package org.codestyle.errorhandling.yes;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ExampleTopLevelErrorHandler provides consistent top-level error handling, for unhandled exceptions. It is often used
 * to wrap the top level of a thread, so that any unhandled exceptions causing the termination of a thread are handled
 * in a consistent manner.
 *
 * <p/>This is the only place in the application that 'log.error' should be called.
 *
 * <p/>This error handler is a very strict one; any unhandled runtime error results in System.exit(-1) being called.
 * This may be appropriate in some situations, and is certainly a good thing to do when developing highly resilient
 * software. When developing web applications, the top-level handler may instead go around each HTTP request handler,
 * and would only log the errors not call System.exit(-1).
 */
public class ExampleTopLevelErrorHandler implements TopLevelErrorHandler {
    private static final Logger LOG = Logger.getLogger(ExampleTopLevelErrorHandler.class.getName());

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
        e.printStackTrace();
        System.exit(-1);
    }

    private static void logAndExitRuntime(RuntimeException e) {
        LOG.log(Level.SEVERE, "", e);
        System.exit(-1);
    }

    private static void logException(Exception e) {
        LOG.log(Level.SEVERE, "", e);
    }
}
