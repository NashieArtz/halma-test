package ca.uqam.info.solanum.t14.f24halma.ai;

import ca.uqam.info.solanum.inf2050.f24halma.controller.Move;
import ca.uqam.info.solanum.inf2050.f24halma.view.MoveSelector;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Classe {@code MadMaxMoveSelector} implémentant l'interface {@code MoveSelector}.
 *
 * <p>Cette classe représente un joueur IA qui sélectionne un mouvement aléatoire parmi
 * une liste de mouvements possibles après les avoir triés dans un ordre déterministe.
 * Elle utilise un générateur de nombres pseudo-aléatoires pour garantir un comportement
 * reproductible lors des tests.</p>
 *
 * <p>Cette IA est adaptée aux scénarios nécessitant une prise de décision rapide mais
 * non optimisée, où l'aléatoire est suffisant pour simuler un comportement basique.</p>
 *
 * @author [Votre Nom]
 */
public class MadMaxMoveSelector implements MoveSelector {
  private final Random random;

  /**
   * Constructeur de la classe {@code MadMaxMoveSelector}.
   *
   * <p>Initialise un générateur de nombres pseudo-aléatoires avec une graine fixe
   * pour garantir que les résultats sont reproductibles lors de l'exécution ou des tests.</p>
   */
  public MadMaxMoveSelector() {
    // Utilisation d'une graine fixe pour garantir un comportement reproductible
    this.random = new Random(42);
  }

  /**
   * Sélectionne un mouvement parmi tous les mouvements possibles pour un joueur.
   *
   * <p>Cette méthode trie d'abord les mouvements possibles dans un ordre déterministe,
   * puis sélectionne un mouvement aléatoire parmi les mouvements triés.</p>
   *
   * @param allPossibleMoves La liste des mouvements possibles pour le joueur actuel.
   * @return Un mouvement sélectionné aléatoirement ou {@code null} si aucun mouvement
   *         n'est possible.
   */
  @Override
  public Move selectMove(List<Move> allPossibleMoves) {
    if (allPossibleMoves == null || allPossibleMoves.isEmpty()) {
      return null; // Aucun mouvement possible
    }

    // Trier les mouvements dans un ordre déterministe
    Collections.sort(allPossibleMoves);

    // Sélectionner un mouvement aléatoire parmi la liste triée
    int index = random.nextInt(allPossibleMoves.size());
    return allPossibleMoves.get(index);
  }
}
