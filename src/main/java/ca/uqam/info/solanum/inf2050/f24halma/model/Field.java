package ca.uqam.info.solanum.inf2050.f24halma.model;

/**
 * Implements a generic Field of the Halma board. This class is completely immutable and carries
 * only primitives, therefore it is implemented as a record. Field implements comparable, so a
 * unique order can be conveniently established.
 *
 * @param x as the visual guide where this field should be represented upon visualization in x
 *          distance from the top left origin.
 * @param y the visual guide where this field should be represented upon visualization in y distance
 *          from the top left origin.
 * @author Maximilian Schiedermeier
 */
public record Field(int x, int y) implements Comparable<Field> {

  /**
   * Default constructor for record.
   */
  public Field {
  }

  @Override
  public String toString() {
    return String.format("(%02d,%02d)", x, y);
  }


  @Override
  public int compareTo(Field o) {
    if (o.y != this.y) {
      return this.y - o.y();
    }

    return this.x() - o.x();
  }
}
