package ca.uqam.info.solanum.inf2050.f24halma.view;

import ca.uqam.info.solanum.inf2050.f24halma.controller.Move;
import ca.uqam.info.solanum.inf2050.f24halma.model.Field;
import ca.uqam.info.solanum.inf2050.f24halma.model.ModelReadOnly;
import java.util.List;
import java.util.Set;

/**
 * UI class that illustrates model state in ASCII that can be printed to screen.
 */
public class TextualVisualizer {

  /**
   * Template variable for the stub to print for missing fields.
   */
  public static final String BLANK_FIELD = "   ";
  /**
   * Template variable for the string to use between two individual field representations.
   */
  public static final String FIELD_SEPARATOR = " ";
  private final boolean useTtyColours;

  /**
   * Constructor for the textual visualizer.
   *
   * @param useTtyColours set to true to integrate TTY strings that toggle coloured representation
   *                      on unix systems.
   */
  public TextualVisualizer(boolean useTtyColours) {
    this.useTtyColours = useTtyColours;
  }

  /**
   * Method to turn a given state of a halma game instance into a textual visualization, that can be
   * printed to screen.
   *
   * @param model as the halma instance to visualize.
   * @return printable string representation of halma model's state.
   */
  public String stringifyModel(ModelReadOnly model) {

    // determine required space to visualize model
    Set<Field> fields = model.getBoard().getAllFields();
    int maxX = 0;
    int maxY = 0;
    for (Field field : fields) {
      maxX = Math.max(field.x(), maxX);
      maxY = Math.max(field.y(), maxY);
    }

    // Print Horizontal coordinate system line
    String originInit = "y\\x | ";
    StringBuilder sb =
        new StringBuilder(useTtyColours ? TtyColours.GREY.wrap(originInit) : originInit);
    String horizontalInit = "----+";
    StringBuilder dashBuilder =
        new StringBuilder(useTtyColours ? TtyColours.GREY.wrap(horizontalInit) : horizontalInit);

    // Construct X axis and horizontal dash separators
    for (int i = 0; i <= maxX; i++) {
      sb.append(String.format(" %02d ", i));
      dashBuilder.append(useTtyColours ? TtyColours.GREY.wrap("----") : "----");
    }
    sb.append("\n" + dashBuilder + "\n");

    // Iterate over all positions, line by line and visualize every matching field
    for (int y = 0; y <= maxY; y++) {

      // Construct y line numerator and vertical dash separator
      sb.append(String.format("%02d ", y));
      sb.append(useTtyColours ? TtyColours.GREY.wrap(" | ") : " | ");

      // Construct line of board
      for (int x = 0; x <= maxX; x++) {
        // If a corresponding field exists: visualize it
        Field field = new Field(x, y);
        if (model.getBoard().getAllFields().contains(field)) {
          sb.append(printField(field, model));
        } else {
          // Otherwise, print some whitespaces
          sb.append(BLANK_FIELD);
        }
        sb.append(FIELD_SEPARATOR);
      }
      if (y < maxY) {
        sb.append("\n");
      }
    }
    return sb.toString();
  }

  private String printField(Field field, ModelReadOnly model) {

    // if a home zone field, use "[ ]" notation, add number of player or empty space for each
    // field

    if (model.getBoard().getAllHomeFields().contains(field)) {
      return "[" + getFieldPlayerAffiliationString(field, model) + "]";
    } else {
      // else use "( )" notation
      return "(" + getFieldPlayerAffiliationString(field, model) + ")";
    }
  }

  /**
   * Looks up player index for a given field. Returns either player index as char or whitespace (if
   * no affiliation).
   *
   * @param field as the field for which textual.
   * @param model as the halma session for which we look up affiliation.
   * @return String representing player index or whitespace.
   */
  private String getFieldPlayerAffiliationString(Field field, ModelReadOnly model) {

    // check for each player, if field contained
    int amountOfPlayers = model.getPlayerNames().length;
    for (int i = 0; i < amountOfPlayers; i++) {
      // Check if current player possesses given field
      if (model.getPlayerFields(i).contains(field)) {
        if (!useTtyColours) {
          // Return player index as string, without colour wrap
          return Integer.toString(i);
        } else {
          // Wrap string for colour of matching player
          return TtyColours.values()[i].wrap(Integer.toString(i));
        }
      }
    }
    // If field not affiliated with any player, return whitespace
    return " ";
  }

  /**
   * Constructs an (optionally coloured) string announcing the current player.
   *
   * @param model as the model, indicating player names and current player.
   * @return announcement string for current player.
   */
  public String getCurrentPlayerAnnouncement(ModelReadOnly model) {
    int currentPlayerIndex = model.getCurrentPlayer();
    String announcement = "It's " + model.getPlayerNames()[currentPlayerIndex] + "'s turn. "
        + model.getPlayerNames()[currentPlayerIndex] + ", your options are: ";
    if (useTtyColours) {
      return TtyColours.values()[currentPlayerIndex].wrap(announcement);
    }
    return announcement;
  }

  /**
   * Returns a move announcement string, containing coloured move tinting if TTY colours enabled.
   *
   * @param move        as the move to be announced.
   * @param playerIndex as the index of the player having selected the move.
   * @return colour adjusted move announcement string.
   */
  public String getChoseMoveAnnouncement(Move move, int playerIndex) {
    String moveAnnouncement = "Chosen move: ";
    if (useTtyColours) {
      return moveAnnouncement + TtyColours.values()[playerIndex].wrap(move.toString());
    } else {
      return moveAnnouncement + move.toString();
    }

  }

  /**
   * Provides a string that pretty prints all possible moves.
   *
   * @param playerMoves as the list of all available moves.
   * @return Pretty formatted String with all moves and their IDs.
   */
  public String announcePossibleMoves(List<Move> playerMoves) {

    int movesPerColumns = 3;
    int idIterator = 0;
    StringBuilder announcement = new StringBuilder("Available moves:\n");

    for (Move move : playerMoves) {
      String idIndicator = String.format("\t%02d:", idIterator);
      announcement.append(useTtyColours ? TtyColours.GREY.wrap(idIndicator) : idIndicator);
      announcement.append(String.format("\t%12s", move));
      idIterator++;

      if (idIterator % movesPerColumns == 0) {
        announcement.append("\n");
      }
    }
    return announcement.toString();

  }


  /**
   * Sends TTY command to clear all terminal content. Only works on UNIX terminals and not in IDEs.
   */
  public void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}
