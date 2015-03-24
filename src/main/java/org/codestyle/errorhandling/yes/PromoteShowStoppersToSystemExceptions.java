package org.codestyle.errorhandling.yes;

import org.codestyle.errorhandling.yes.errors.CheckedSystemException;
import org.codestyle.errorhandling.yes.errors.RuntimeSystemException;
import org.codestyle.errorhandling.yes.errors.UserException;

public abstract class PromoteShowStoppersToSystemExceptions {
    public abstract void tryStuff() throws UserException;

    /** Show stoppers are usually runtimes since they cannot be recovered from. */
    public void promoteShowStopperToRuntime() {
        try {
            tryStuff();
        } catch (UserException e) {
            throw new RuntimeSystemException("Could not connect to database.", e, "SYS_00001");
        }
    }

    /**
     * A show stopper may be checked. This can be useful when you want to know exactly where system exceptions can
     * occur. This is sometimes done as an intermediate step when removing a system exception that is not really needed;
     * first make it checked, then figure out how to recover from it, then remove it all together.
     *
     * <p/>Runtime system exceptions are preferred.
     *
     * @throws org.codestyle.errorhandling.yes.errors.CheckedSystemException A checked system exception.
     */
    public void promoteShowStopperToChecked() throws CheckedSystemException {
        try {
            tryStuff();
        } catch (UserException e) {
            throw new CheckedSystemException("Could not connect to database.", e, "SYS_00002");
        }
    }
}
