package ca.uqam.info.solanum.t14.f24halma.controller;

import ca.uqam.info.solanum.inf2050.f24halma.controller.ModelFactory;
import ca.uqam.info.solanum.inf2050.f24halma.model.Model;

public class SquareModelFactory implements ModelFactory {
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
    @Override
    public Model createModel(int baseSize, String[] players) {
        return null;
    }
}
