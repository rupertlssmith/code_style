package org.codestyle.errorhandling.yes;

public abstract class RethrowExceptions {
    public abstract void tryStuff() throws UserException;

    /**
     * When not recovering from an error indicated by an exception it can be re-thrown in some manner.
     *
     * <p/>An exception can be re-thrown as a runtime exception, IllegalStateException in this case. Runtime exceptions
     * are considered to represent bugs, so a known error condition is being made into a bug by doing this. This may be
     * done in situations where you cannot think of any alternative, and are prepared to re-visit the bug later. Not
     * ideal, but it happens, and at least the stack trace will never be lost.
     *
     * <p/>An exception may be re-thrown as a different checked exception that is more appropriate to the level of
     * abstraction that this layer of codes API represents. For example, an IOException on a TCP connection is an
     * IOException in the network code, but may be thrown as a SQLException in database code that fails to communicate
     * with the database as a result of it.
     *
     * @throws MoreAppropriateForThisLevelException A more appropriate exception for this layer of code.
     */
    public void rethrowExceptions() throws MoreAppropriateForThisLevelException {
        // Be careful, a known error condition is being made into a bug by doing this.
        try {
            tryStuff();
        } catch (UserException e) {
            throw new IllegalStateException(e);
        }

        try {
            tryStuff();
        } catch (UserException e) {
            throw new MoreAppropriateForThisLevelException(e);
        }
    }

    private class MoreAppropriateForThisLevelException extends Exception {
        public MoreAppropriateForThisLevelException(Throwable cause) {
            super(cause);
        }
    }
}
