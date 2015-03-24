package org.codestyle.errorhandling.no;

public abstract class DontCatchErrorOrThrowable {
    /**
     * Application code should not throw errors generally. Error is used by the JVM platform to signal critical errors
     * in the JVM runtime.
     */
    public abstract void causesError() throws Error;

    /**
     * Application code should not throw throwables generally. Throwable is a generic superclass for all exceptions in
     * Java, and that includes Error too.
     */
    public abstract void causesThrowable() throws Throwable;

    /**
     * Application code should not catch Error or Throwable generally. They are both runtime exceptions and should be
     * allowed to fall through to the top-level error handler.
     */
    public void catchThrowablesAndErrors() {
        try {
            causesError();
        } catch (Error error) {
            throw new IllegalStateException(error);
        }

        try {
            causesThrowable();
        } catch (Throwable throwable) {
            throw new IllegalStateException(throwable);
        }
    }
}
