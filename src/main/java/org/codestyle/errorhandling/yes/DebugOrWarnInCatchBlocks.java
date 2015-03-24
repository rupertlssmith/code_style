package org.codestyle.errorhandling.yes;

import org.codestyle.errorhandling.yes.errors.UserException;

import java.util.logging.Logger;

public abstract class DebugOrWarnInCatchBlocks {
    /** Used for debugging purposes. */
    private static final Logger LOG = Logger.getLogger(DebugOrWarnInCatchBlocks.class.getName());
    public static final String SOME_DEBUG_MESSAGE = "Some debug message.";
    public static final String WARNING_MESSAGE = "Client disconnected unexpectedly.";

    public abstract void tryStuff() throws UserException;

    /**
     * You can put debug statements wherever you like in the code. They are for the programmer to help with debugging.
     *
     * <p/>In some cases when re-throwing an exception you may want to issue a warning to inform the sys admin that
     * something out of the ordinary has happened, but not a serious error. This is not used terribly often, but does
     * have its use cases. For example, you may want to warn that a client unexpectedly dropped the connection, and
     * things of that nature.
     *
     * <p/>Don't log the stack trace, the exception is being re-thrown, higher error handlers will deal with it or log
     * it as appropriate.
     *
     * @throws MoreAppropriateForThisLevelException A more appropriate exception for this layer of code.
     */
    public void rethrowExceptions() throws MoreAppropriateForThisLevelException {
        // Wherever you like...
        LOG.fine(SOME_DEBUG_MESSAGE);

        try {
            tryStuff();
        } catch (UserException e) {
            // Wherever you like...
            LOG.fine(SOME_DEBUG_MESSAGE);

            // Unusual, let the sys admin know, may be indicative of some problem.
            LOG.warning(WARNING_MESSAGE);

            throw new IllegalStateException(e);
        }

        try {
            tryStuff();
        } catch (UserException e) {
            // Unusual, let the sys admin know, may be indicative of some problem.
            LOG.warning(WARNING_MESSAGE);

            throw new MoreAppropriateForThisLevelException(e);
        }
    }

    private class MoreAppropriateForThisLevelException extends Exception {
        public MoreAppropriateForThisLevelException(Throwable cause) {
            super(cause);
        }
    }
}
