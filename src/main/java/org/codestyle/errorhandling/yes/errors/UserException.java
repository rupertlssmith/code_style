package org.codestyle.errorhandling.yes.errors;

/**
 * UserException is an exception that the user of your code needs to know about.
 *
 * <p/>When writing library code, the user will be other code, so the audience is the programmer.
 *
 * <p/>When writing application code, the user may be the end user of the application. For example, if I try to withdraw
 * more money from my account than I have, I may get an OutOfFundsException passed back. The audience is really the UI
 * programmer, who will find some way of displaying this error to the user. Form authentication is a typical use case
 * for this, where one or more entries in a form may be invalid, and an error condition must be passed back to the UI to
 * inform the user of the errors and which elements need to be edited.
 */
public class UserException extends Exception {
}
