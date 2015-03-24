package org.codestyle.errorhandling.no;

import org.codestyle.errorhandling.yes.errors.UserException;

public abstract class RethrowExceptionsWithoutCause {
    public abstract void tryStuff() throws UserException;

    /**
     * When re-throwing an exception by wrapping it in another exception, the original exception must be set as the
     * cause, otherwise its stack trace will be lost.
     *
     * @throws MoreAppropriateForThisLevelException A more appropriate exception for this layer of code.
     */
    public void rethrowExceptions() throws MoreAppropriateForThisLevelException {
        try {
            tryStuff();
        } catch (UserException e) {
            throw new IllegalStateException();
        }

        try {
            tryStuff();
        } catch (UserException e) {
            throw new MoreAppropriateForThisLevelException();
        }
    }

    private class MoreAppropriateForThisLevelException extends Exception {
        public MoreAppropriateForThisLevelException() {
        }
    }
}
