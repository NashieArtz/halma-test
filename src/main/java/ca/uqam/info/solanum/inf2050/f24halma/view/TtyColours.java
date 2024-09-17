package ca.uqam.info.solanum.inf2050.f24halma.view;

/**
 * Enum for TTY colours. Allows convenient wrapping of input strings by TTY commands that tint text
 * in palette of TTY colours.
 *
 * @author Maximilian Schiedermeier
 */
public enum TtyColours {

  /**
   * Red TTY colour definition.
   */
  RED("\033[00;31m"),

  /**
   * Green TTY colour definition.
   */
  GREEN("\033[00;32m"),


  /**
   * Yellow TTY colour definition.
   */
  YELLOW("\033[00;33m"),

  /**
   * Blue TTY colour definition.
   */
  BLUE("\033[00;34m"),

  /**
   * Magenta TTY colour definition.
   */
  MAGENTA("\033[00;35m"),

  /**
   * Orange TTY colour definition.
   */
  CYAN("\033[00;36m"),

  /**
   * White TTY colour definition.
   */
  GREY("\033[00;37m");


  private final String prefix;
  private final String suffix = "\033[00;39m";

  /**
   * Constructor, every TTY colour contains a prefix to tint follow characters and a suffix to reset
   * to default.
   *
   * @param prefix as the TTY colour code to switch to the desired colour.
   */
  TtyColours(String prefix) {
    this.prefix = prefix;
  }

  /**
   * Wraps provided string in current TTY colours prefix and suffix string to allow printing text in
   * colour to console.
   *
   * @param original as the string to wrap for colour print.
   * @return TTY colour wrapped string.
   */
  public String wrap(String original) {
    return prefix + original + suffix;
  }
}