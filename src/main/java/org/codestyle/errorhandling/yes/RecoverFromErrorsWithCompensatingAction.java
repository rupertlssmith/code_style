package org.codestyle.errorhandling.yes;

import org.codestyle.errorhandling.yes.errors.UserException;

import java.util.logging.Logger;

public abstract class RecoverFromErrorsWithCompensatingAction {
    /** Used for debugging purposes. */
    private static final Logger LOG = Logger.getLogger(RecoverFromErrorsWithCompensatingAction.class.getName());

    public static final String USER_EXCEPTION_MESSAGE = "Got a UserException.";

    public abstract void tryStuff() throws UserException;

    public abstract void doAlternativeStuff();

    /**
     * It is sometimes possible to completely recover from an exception in code, so that processing can safely continue.
     * For example, a config file cannot be loaded; use the defaults instead. Compensating actions do not re-throw the
     * exception or allow it to fall through. It is worth documenting compensating actions carefully. It is also
     * convenient to have some kind of marker in the code such as "e = null;" or "// COMPENSATING_ACTION" which can be
     * used to indicate that slightly different rules to normal are being followed.
     */
    public void recoverFromError() {
        try {
            tryStuff();
        } catch (UserException e) {
            // tryStuff didn't work but no problem, we can do alternative stuff instead and everything is fine.
            // No need to log this as an error, it has been recovered from.
            // I've set e to null to indicate that it is being deliberately ignored.
            // COMPENSATING_ACTION
            e = null;

            doAlternativeStuff();
        }
    }

    /**
     * Occasionally an error can be ignored; the correct compensating action is to do nothing. This is ok, as long as it
     * is documented and marked as a compensating action.
     */
    public void recoverFromErrorByDoingNothing() {
        try {
            tryStuff();
        } catch (UserException e) {
            // tryStuff didn't work but no problem, we can do alternative stuff instead and everything is fine.
            // No need to log this as an error, it has been recovered from.
            // I've set e to null to indicate that it is being deliberately ignored.
            // COMPENSATING_ACTION
            e = null;
        }
    }
}
