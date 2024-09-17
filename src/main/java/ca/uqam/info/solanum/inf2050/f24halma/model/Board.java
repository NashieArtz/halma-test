package ca.uqam.info.solanum.inf2050.f24halma.model;

import java.util.Set;

/**
 * Represents only the board layout. Implementations of this class should have no knowledge about
 * pieces, or other information associated with a specific player. The board always specifies home
 * zones for the maximum mount of players possible on a given game layout. Boards are by design
 * unmodifiable, that is, no content of a board object must ever change. Figures are not part of the
 * board object.
 *
 * @author Maximilian Schiedermeier
 */
public interface Board {

  /**
   * Getter for all fields contained in the board instance.
   *
   * @return unmodifiable set of all board fields.
   */
  Set<Field> getAllFields();

  /**
   * Getter for all home fields on the given board instance, for a given player specified by index.
   *
   * @param playerIndex as the player for whom we want to look up the home fields. Consecutive pairs
   *                    of an even then an odd player indexes always have opposing home zones.
   * @return unmodifiable set of all the player's home fields.
   */
  Set<Field> getHomeFieldsForPlayer(int playerIndex);


  /**
   * Getter for all home fields (or target fields) on the board, regardless of player affiliation.
   *
   * @return unmodifiable set of all home fields
   */
  Set<Field> getAllHomeFields();

  /**
   * Getter for all target fields on the given board instance, for a given player specified by
   * index.
   *
   * @param playerIndex as the player for whom we want to look up the home fields. Consecutive pairs
   *                    of an even then an odd player indexes always have opposing home zones.
   * @return unmodifiable set of all the player's target fields.
   */
  Set<Field> getTargetFieldsForPlayer(int playerIndex);


  /**
   * Getter for neighboured fields, that is, fields that are directly adjacent to the given field
   * object.
   *
   * @param field as entity on the board for which a set of neighbours must be determined.
   * @return unmodifiable set of all adjacent field objects.
   */
  Set<Field> getNeighbours(Field field);

  /**
   * Getter for extended neighbours, that is all neighbours of neighbours that lie on a straight
   * line, starting from the given field.
   *
   * @param origin    as field on the board for which a potential extended neighbour is determined.
   * @param neighbour as a direct neighbour of the origin field, defining direction of the extended
   *                  neighbour.
   * @return Field defining extended neighbour of origin. May be null.
   */
  Field getExtendedNeighbour(Field origin, Field neighbour);
}
