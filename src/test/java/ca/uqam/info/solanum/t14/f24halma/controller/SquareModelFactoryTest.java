package ca.uqam.info.solanum.t14.f24halma.controller;

import ca.uqam.info.solanum.inf2050.f24halma.controller.AbstractSquareModelFactoryTest;
import ca.uqam.info.solanum.inf2050.f24halma.controller.ModelFactory;

public class SquareModelFactoryTest extends AbstractSquareModelFactoryTest {
    /**
     * Helper method to obtain a ModelFactory instance without knowledge of the concrete field class
     * implementation.
     *
     * @return a factory abject capable of initializing halma models.
     */
    @Override
    public ModelFactory getModelFactory() {
        return null;
    }
}
