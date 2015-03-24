package org.codestyle.errorhandling.yes;

/**
 * Java is a safe language, which means that it is not possible for runtime error conditions to go unnoticed. However,
 * there are still many faults in code that may go unnoticed, or lead to unexpected errors later on. It is good practice
 * to try and prevent this from happening by promoting known bugs into runtime exceptions, thereby making use of the
 * safety quality of the language to ensure these bugs can never go undetected.
 *
 * <p/>This is a very important step in making code highly reliable.
 */
public abstract class PromoteKnownBugsToRuntime {
    /**
     * Expected to return a non-negative value.
     *
     * @return A non-negative value.
     */
    public abstract int supplySomeOtherValue();

    /**
     * The caller must supply a value that is non-negative, and less than 4.
     *
     * @param someValue A non-negative value that is less than 4.
     */
    public void promoteBug(int someValue) {
        // Check arguments.
        if (someValue < 0) {
            throw new IllegalArgumentException("'someValue' must be non-negative.");
        }

        // Check return values.
        int result = supplySomeOtherValue();

        if (result < 0) {
            throw new IllegalStateException("The result of 'supplySomeOtherValue' must be non-negative.");
        }

        // Don't let switch statements silently fail.
        switch (someValue) {
        case 1:
            break;

        case 2:
            break;

        case 3:
            break;

        default:
            throw new IllegalArgumentException("'someValue' must be less than 4");
        }

        // And so on...
    }
}
