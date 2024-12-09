package ca.uqam.info.solanum.t14.f24halma.ai;

import ca.uqam.info.solanum.inf2050.f24halma.controller.Move;
import ca.uqam.info.solanum.inf2050.f24halma.view.MoveSelector;
import java.util.Collections;
import java.util.List;

/**
 * Implémentation de {@link MoveSelector} qui sélectionne un mouvement basé sur une liste triée.
 * Cette implémentation trie les mouvements possibles et retourne le premier mouvement
 * dans l'ordre trié.
 */
public class KeksliMoveSelector implements MoveSelector {

  /**
   * Constructeur par défaut de la classe {@code KeksliMoveSelector}.
   * Cette classe implémente une stratégie simple pour sélectionner un mouvement
   * parmi une liste triée de mouvements possibles.
   */
  public KeksliMoveSelector() {
    // Constructeur par défaut sans logique additionnelle
  }

  /**
   * Sélectionne un mouvement parmi une liste de mouvements possibles.
   *
   * <p>Si la liste est vide ou nulle, cette méthode retourne {@code null}.
   * Sinon, elle trie les mouvements et retourne le premier dans l'ordre trié.</p>
   *
   * @param allPossibleMoves la liste des mouvements possibles, peut être vide ou {@code null}
   * @return le mouvement sélectionné, ou {@code null} si aucun mouvement n'est possible
   */
  @Override
  public Move selectMove(List<Move> allPossibleMoves) {
    if (allPossibleMoves == null || allPossibleMoves.isEmpty()) {
      return null; // Aucun mouvement possible
    }

    // Trier la liste des mouvements possibles
    Collections.sort(allPossibleMoves);

    // Retourner le premier mouvement de la liste triée
    return allPossibleMoves.get(0);
  }
}
