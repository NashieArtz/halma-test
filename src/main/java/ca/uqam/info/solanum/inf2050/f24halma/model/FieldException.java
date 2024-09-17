package ca.uqam.info.solanum.inf2050.f24halma.model;

/**
 * Custom exception to be thrown whenever access on the board's fields leads to inconsistencies,
 * i.e. access on a field that is not part of the board, or attempting to occupy a field that is
 * already occupied, clearing a field that is not occupied, etc. FieldExceptions indicate a
 * programming mistake and should not be caught, they are therefore implemented as unchecked
 * RuntimeException derivatives.
 *
 * @author Maximilian Schiedermeier
 */
public class FieldException extends RuntimeException {

  /**
   * Custom exception constructor.
   *
   * @param errorMessage as the String message to assign to the exception object.
   */
  public FieldException(String errorMessage) {
    super(errorMessage);
  }
}
