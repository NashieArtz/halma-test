package ca.uqam.info.solanum.inf2050.f24halma.controller;

/**
 * Custom exception to be thrown whenever an action is provided that violates the game rules.
 *
 * @author Maximilian Schiedermeier
 */
public class IllegalMoveException extends RuntimeException {

  /**
   * Custom exception constructor.
   *
   * @param errorMessage as the String message to assign to the exception object.
   */
  public IllegalMoveException(String errorMessage) {
    super(errorMessage);
  }
}
