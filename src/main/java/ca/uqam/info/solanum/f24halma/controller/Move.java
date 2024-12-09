package ca.uqam.info.solanum.inf2050.f24halma.controller;

import ca.uqam.info.solanum.inf2050.f24halma.model.Field;

/**
 * Represents a move from one field to another. The existence of an action tells nothing about its
 * validity. Origin being identical to target serves as "confirm" to end a series of jumps while
 * further jumps would be allowed. Implements comparable interface to ensure reliable order of
 * moves, which is useful for development of automated / AI players.
 *
 * @param origin as the field on which the player figure stands before the move is performed.
 * @param target as the empty field to which the player figure is moved by performing the move.
 * @param jump   to indicate whether the move jumps an occupied field to transport a figure to an
 *               extended neighbour.
 * @author Maximilian Schiedermeier
 */
public record Move(Field origin, Field target, boolean jump) implements Comparable<Move> {

  @Override
  public String toString() {
    return origin + getTransitionSymbol() + target;
  }

  private String getTransitionSymbol() {
    if (origin.equals(target)) {
      return " == ";
    }
    if (jump) {
      return " => ";
    } else {
      return " -> ";
    }
  }

  @Override
  public int compareTo(Move o) {
    // If same origin, sort by target field
    if (o.origin().equals(this.origin())) {
      return this.target.compareTo(o.target);
    }
    // otherwise sort by origin
    return this.origin.compareTo(o.origin);
  }
}

