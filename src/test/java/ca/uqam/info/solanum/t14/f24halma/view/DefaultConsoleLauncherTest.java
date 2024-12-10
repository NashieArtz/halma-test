package ca.uqam.info.solanum.t14.f24halma.view;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * Tests unitaires pour la méthode runTp03 de la classe DefaultConsoleLauncher.
 */
public class DefaultConsoleLauncherTest {

  @Before
  public void setUp() {
    // Réinitialisation des variables statiques avant chaque test
    DefaultConsoleLauncher.baseSize = 0;
    DefaultConsoleLauncher.players = null;
  }

  @Test
  public void testRunTp03() {
    String[] args = {"4", "Player1", "Player2"};

    try {
      DefaultConsoleLauncher.runTp03(args);
    } catch (Exception e) {
      fail("L'exécution de runTp03 ne doit pas lever d'exception : " + e.getMessage());
    }
  }
}
