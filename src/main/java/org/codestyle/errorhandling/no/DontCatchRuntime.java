package org.codestyle.errorhandling.no;

public abstract class DontCatchRuntime {
    /**
     * Application code may throw runtime exceptions, in cases where an error is unlikely to be able to be recovered
     * from.
     */
    public abstract void causesRuntime();

    /**
     * Application code should not catch Runtime exception explicitly, as RuntimeException is a super-class for all
     * runtime exceptions. It may be possible to catch specific runtime exception in certain cases, for example
     * NumberFormatException when trying to parse a number from a String. The general catch all for runtimes should be
     * avoided; let them fall through to the top-level error handler.
     */
    public void catchRuntime() {
        try {
            causesRuntime();
        } catch (RuntimeException e) {
            throw new IllegalStateException(e);
        }
    }
}
