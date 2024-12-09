import ca.uqam.info.solanum.inf2050.f24halma.controller.Move;
import ca.uqam.info.solanum.inf2050.f24halma.model.Field;
import ca.uqam.info.solanum.t14.f24halma.ai.MadMaxMoveSelector;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class MadMaxMoveSelectorTest {

  @Test
  public void testSelectMove() {
    // Initialisation de l'objet testé
    MadMaxMoveSelector selector = new MadMaxMoveSelector();

    // Création de la liste de mouvements possibles
    List<Move> moves = new ArrayList<>();

    // Ajouter des mouvements fictifs
    moves.add(new Move(new Field(0, 0), new Field(1, 1), false));
    moves.add(new Move(new Field(2, 2), new Field(3, 3), false));

    // Appel de la méthode à tester
    Move selectedMove = selector.selectMove(moves);

    // Vérification que le mouvement sélectionné n'est pas nul
    assertNotNull("Le mouvement sélectionné ne doit pas être nul.", selectedMove);
  }
}
