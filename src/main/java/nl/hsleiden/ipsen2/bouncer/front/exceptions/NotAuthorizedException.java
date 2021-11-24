package nl.hsleiden.ipsen2.bouncer.front.exceptions;

public class NotAuthorizedException extends Exception {
    public NotAuthorizedException() {
        super("JWT has not been defined");
    }
}
