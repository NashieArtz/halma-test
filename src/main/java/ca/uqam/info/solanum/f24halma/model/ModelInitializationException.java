package ca.uqam.info.solanum.inf2050.f24halma.model;

/**
 * Custom exception to be thrown whenever initialization of a Model is not possible, e.g. when the
 * amount of requested players is incompatible with the requested board layout.
 *
 * @author Maximilian Schiedermeier
 */
public class ModelInitializationException extends RuntimeException {

  /**
   * Custom exception constructor.
   *
   * @param errorMessage as the String message to assign to the exception object.
   */
  public ModelInitializationException(String errorMessage) {
    super(errorMessage);
  }
}
