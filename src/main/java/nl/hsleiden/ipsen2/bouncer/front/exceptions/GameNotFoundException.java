package nl.hsleiden.ipsen2.bouncer.front.exceptions;

public class GameNotFoundException extends Exception {
  public GameNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}
