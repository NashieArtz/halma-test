package ca.uqam.info.solanum.inf2050.f24halma.controller;

import ca.uqam.info.solanum.inf2050.f24halma.model.Model;

/**
 * Factory for the creation of new Halma boards. Different factories for different layouts may
 * exist. No assumptions can be made on the layout of the Halma board layout.
 *
 * @author Maximilian Schiedermeier
 */
public interface ModelFactory {

  /**
   * One and only model instantiation method that must be implemented by whatever model factory
   * implementing class.
   *
   * @param baseSize as the side-length along all player bases.
   * @param players  as the player names involved on the board. Must be an even number and
   *                 compatible to created layout. Note: the created model's board always contains
   *                 home-zones for the maximum allowed amount * of players for the requested
   *                 layout.
   * @return a Model conforming object, representing the characteristics of the given factory.
   */
  public Model createModel(int baseSize, String[] players);
}
