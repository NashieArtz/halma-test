import ca.uqam.info.solanum.inf2050.f24halma.controller.Move;
import ca.uqam.info.solanum.inf2050.f24halma.model.Field;
import ca.uqam.info.solanum.t14.f24halma.ai.KeksliMoveSelector;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class KeksliMoveSelectorTest {

  @Test
  public void testSelectMove() {
    // Initialisation de l'objet testé
    KeksliMoveSelector selector = new KeksliMoveSelector();

    // Création de la liste de mouvements possibles
    List<Move> moves = new ArrayList<>();

    // Ajouter des mouvements fictifs
    moves.add(new Move(new Field(2, 2), new Field(3, 3), false));
    moves.add(new Move(new Field(0, 0), new Field(1, 1), false));

    // Appel de la méthode à tester
    Move selectedMove = selector.selectMove(moves);

    // Vérification que le premier mouvement trié est sélectionné
    assertEquals(
        "Le mouvement sélectionné doit être celui qui est trié en premier.",
        new Move(new Field(0, 0), new Field(1, 1), false),
        selectedMove
    );
  }
}
