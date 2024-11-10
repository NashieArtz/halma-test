package ca.uqam.info.solanum.t14.f24halma.controller;

import ca.uqam.info.solanum.inf2050.f24halma.controller.AbstractSquareModelFactoryTest;
import ca.uqam.info.solanum.inf2050.f24halma.controller.ModelFactory;

/**
 * Classe qui étend AbstractSquareModelFactoryTest
 * Classe pour tester la creation de modeles carres.
 *
 */
public class SquareModelFactoryTest extends AbstractSquareModelFactoryTest {
    private ModelFactory modelFactory;  // instance

    /**
     * Constructeur de la classe SquareModelFactoryTest.
     *
     * Ce constructeur initialise une instance de SquareModelFactory
     */
    public SquareModelFactoryTest() {
        this.modelFactory = new SquareModelFactory();  // Instanciation d'une implémentation de ModelFactory
    }

    /**
     * Methode heritee
     *
     * Méthode getter pour le modelFactory
     *
     * @return le modelFactory vise
     */
    @Override
    public ModelFactory getModelFactory() {
        return modelFactory;
    }
}

