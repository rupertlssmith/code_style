package org.codestyle.errorhandling.no;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.codestyle.errorhandling.yes.UserException;

public abstract class SwallowingExceptions {
    /** Used for debugging purposes. */
    private static final Logger LOG = Logger.getLogger(SwallowingExceptions.class.getName());
    public static final String USER_EXCEPTION_MESSAGE = "Got a UserException.";

    public abstract void tryStuff() throws UserException;

    /**
     * Swallowing an exception is a bad idea. After an exception the program should not just continue on as is nothing
     * has happened. This is because the exception indicates that the program is now in an error state, and that it is
     * not safe to continue without addressing the error in some way.
     */
    public void swallow() {
        try {
            tryStuff();
        } catch (UserException e) {
        }
    }

    /**
     * From the point of view of program logic, a log statement is a no-op. It does not perform useful logic, it exists
     * merely to inform either the programmer or an admin about the state of the program. Since it is a no-op, it is the
     * same as simply swallowing the exception. Something more must be done to address the error.
     */
    public void logAndSwallow() {
        try {
            tryStuff();
        } catch (UserException e) {
            e.printStackTrace();
        }

        try {
            tryStuff();
        } catch (UserException e) {
            LOG.log(Level.SEVERE, USER_EXCEPTION_MESSAGE, e);
        }

        try {
            tryStuff();
        } catch (UserException e) {
            LOG.log(Level.FINE, USER_EXCEPTION_MESSAGE, e);
        }
    }
}
