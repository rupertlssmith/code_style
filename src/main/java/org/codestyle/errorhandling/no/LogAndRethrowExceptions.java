package org.codestyle.errorhandling.no;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.codestyle.errorhandling.yes.UserException;

public abstract class LogAndRethrowExceptions {
    /** Used for debugging purposes. */
    private static final Logger LOG = Logger.getLogger(LogAndRethrowExceptions.class.getName());
    public static final String RETHROWING_MESSAGE = "Rethrowing this";

    public abstract void tryStuff() throws UserException;

    /**
     * When re-throwing an exception, it will be dealt with at a higher level, possibly in the top-level error handler.
     * There is no need to log the exception as an error when doing this. If an exception is logged as an error in a
     * catch block, then also logged in a higher up error handler, the exception stack trace will appear more than once
     * in the logs. It is confusing an undesireable to have more than one error logged for a single error condition. Do
     * the logging in the top-level error handler, that way it will be done consistently, with less code, and will never
     * fail to miss an exception.
     *
     * @throws MoreAppropriateForThisLevelException A more appropriate exception for this layer of code.
     */
    public void rethrowExceptions() throws MoreAppropriateForThisLevelException {
        try {
            tryStuff();
        } catch (UserException e) {
            LOG.log(Level.SEVERE, RETHROWING_MESSAGE, e);

            throw new IllegalStateException(e);
        }

        try {
            tryStuff();
        } catch (UserException e) {
            LOG.log(Level.SEVERE, RETHROWING_MESSAGE, e);

            throw new MoreAppropriateForThisLevelException(e);
        }

        // Log at warn level, confusing though since the stack trace is included, so will appear more than once.
        try {
            tryStuff();
        } catch (UserException e) {
            LOG.log(Level.WARNING, RETHROWING_MESSAGE, e);

            throw new IllegalStateException(e);
        }
    }

    private class MoreAppropriateForThisLevelException extends Exception {
        public MoreAppropriateForThisLevelException(Throwable cause) {
            super(cause);
        }
    }
}
