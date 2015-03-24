package org.codestyle.errorhandling.yes;

import java.util.logging.Logger;

/**
 * InterruptedException is thrown when a thread has its interrupt flag set, and is performing a blocking operation when
 * this happens. A blocking operation is either waiting for IO to complete, or waiting for a lock to be released. This
 * mechanism exists in Java, so that threads that are blocked can be interrupted, in order to tell them to prematurely
 * complete their unit of work. This can be used in a variety of ways, but is most commonly used to force threads to
 * terminate when an application is shutting down.
 *
 * <p/>The correct compensating action is to set the interrupt flag on the thread (it is cleared when this exception is
 * caught) and terminate the current unit of work.
 */
public class DealWithInterruptedException {
    /** Used for debugging purposes. */
    private static final Logger LOG = Logger.getLogger(DealWithInterruptedException.class.getName());

    public void dealWithInterrupedException() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // The correct compensating action to set the interrupt flag on the thread (it is cleared when this exception is
            // caught) and terminate the current unit of work.
            // COMPENSATING_ACTION
            e = null;

            Thread.currentThread().interrupt();

            return;
        }
    }
}
