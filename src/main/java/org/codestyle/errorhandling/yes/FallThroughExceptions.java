package org.codestyle.errorhandling.yes;

import org.codestyle.errorhandling.yes.errors.UserException;

public abstract class FallThroughExceptions {
    public abstract void tryStuff() throws UserException;

    /**
     * If it is appropriate at the level of the API being presented by this layer of code to simply allow a lower level
     * exception to fall through, then that is ok to do.
     *
     * @throws UserException Lower level exception condition that is ok to fall through.
     */
    public void rethrowExceptions() throws UserException {
        tryStuff();
    }
}
