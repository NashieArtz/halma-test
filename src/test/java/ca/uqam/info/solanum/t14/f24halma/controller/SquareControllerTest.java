package ca.uqam.info.solanum.t14.f24halma.controller;

import ca.uqam.info.solanum.inf2050.f24halma.controller.Controller;
import ca.uqam.info.solanum.t14.f24halma.model.ModelImpl;
import ca.uqam.info.solanum.inf2050.f24halma.model.Field;
import ca.uqam.info.solanum.inf2050.f24halma.model.Model;


/**
 * Classe abstraite étendant ControllerImplTest pour fournir une méthode
 * abstraite getSquareController(...) destinée aux sous-classes.
 */
public abstract class SquareControllerTest extends ControllerImplTest {

  // Méthode abstraite que les sous-classes doivent implémenter pour obtenir une instance spécifique
  public abstract Controller getSquareController(ModelImpl model);


}
