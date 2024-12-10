package ca.uqam.info.solanum.t14.f24halma.controller;

import ca.uqam.info.solanum.inf2050.f24halma.controller.Controller;
import ca.uqam.info.solanum.inf2050.f24halma.controller.ModelFactory;
import ca.uqam.info.solanum.inf2050.f24halma.controller.Move;
import ca.uqam.info.solanum.inf2050.f24halma.model.Field;
import ca.uqam.info.solanum.inf2050.f24halma.model.FieldException;
import ca.uqam.info.solanum.inf2050.f24halma.model.ModelReadOnly;
import ca.uqam.info.solanum.t14.f24halma.model.ModelImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Implémentation du contrôleur pour le jeu Halma.
 * <p>
 * Cette classe gère les interactions entre l'interface utilisateur et le modèle.
 * Elle permet de consulter l'état actuel du modèle et de modifier cet état
 * via des mouvements de joueurs.
 * </p>
 */
public class ControllerImpl implements Controller {

  /**
   * Instance du modèle utilisée par le contrôleur pour gérer l'état du jeu.
   */
  private final ModelImpl model;

  /**
   * Constructeur qui initialise le contrôleur avec un modèle existant.
   *
   * @param model le modèle à utiliser pour gérer l'état du jeu.
   * @throws IllegalArgumentException si le modèle fourni est nul.
   */
  public ControllerImpl(ModelImpl model) {
    if (model == null) {
      throw new IllegalArgumentException("Le modèle fourni ne doit pas être nul.");
    }
    this.model = model;
  }

  /**
   * Constructeur qui initialise le contrôleur en utilisant une fabrique de modèles.
   *
   * @param modelFactory la fabrique de modèles utilisée pour créer un modèle.
   * @param baseSize     la taille de base du plateau (ex. 4 pour un plateau 16x16).
   * @param playerNames  les noms des joueurs participant au jeu.
   * @throws IllegalArgumentException si la fabrique ou les paramètres sont invalides.
   */
  public ControllerImpl(ModelFactory modelFactory, int baseSize, String[] playerNames) {
    if (modelFactory == null) {
      throw new IllegalArgumentException("La fabrique de modèle ne peut pas être nulle.");
    }
    if (baseSize <= 0) {
      throw new IllegalArgumentException("La taille de base doit être positive.");
    }
    if (playerNames == null || playerNames.length < 2) {
      throw new IllegalArgumentException("Au moins deux joueurs doivent être fournis.");
    }

    this.model = (ModelImpl) modelFactory.createModel(baseSize, playerNames);
  }

  /**
   * Renvoie une version en lecture seule du modèle.
   * <p>
   * Cette méthode permet, par exemple, à l'interface utilisateur de visualiser
   * l'état du jeu sans modifier directement le modèle.
   * </p>
   *
   * @return une vue immuable du modèle actuel.
   */
  @Override
  public ModelReadOnly getModel() {
    return this.model;
  }

  /**
   * Génère une liste de mouvements valides pour le joueur actuel.
   * <p>
   * Cette méthode prend en compte les règles du jeu pour déterminer quels
   * mouvements sont possibles depuis les champs occupés par le joueur actuel.
   * </p>
   *
   * @return une liste de mouvements valides.
   */
  @Override
  public List<Move> getPlayerMoves() {
    int currentPlayerIndex = model.getCurrentPlayer();
    Set<Field> playerFields = model.getPlayerFields(currentPlayerIndex);
    List<Move> possibleMoves = new ArrayList<>();

    for (Field field : playerFields) {
      Set<Field> neighbours = model.getBoard().getNeighbours(field);
      for (Field neighbour : neighbours) {
        if (model.isClear(neighbour)) {
          possibleMoves.add(new Move(field, neighbour, false));
        }
      }
    }

    return possibleMoves;
  }

  /**
   * Exécute un mouvement spécifié par le joueur actuel.
   * <p>
   * Cette méthode modifie l'état du modèle en déplaçant une pièce d'un champ
   * d'origine vers un champ cible.
   * </p>
   *
   * @param move le mouvement à effectuer.
   * @throws IllegalStateException si le mouvement est invalide ou impossible à effectuer.
   */
  @Override
  public void performMove(Move move) {
    try {
      model.occupyField(model.getCurrentPlayer(), move.target());
      model.clearField(move.origin());
    } catch (FieldException e) {
      throw new IllegalStateException("Impossible d'effectuer le mouvement : " + e.getMessage(), e);
    }
  }

  /**
   * Indique si la partie est terminée.
   * <p>
   * Une partie est terminée lorsqu'un joueur a déplacé toutes ses pièces
   * sur les champs cibles correspondants.
   * </p>
   *
   * @return {@code true} si la partie est terminée, {@code false} sinon.
   */
  @Override
  public boolean isGameOver() {
    for (int playerIndex = 0; playerIndex < model.getPlayerNames().length; playerIndex++) {
      Set<Field> targetFields = model.getBoard().getTargetFieldsForPlayer(playerIndex);
      Set<Field> playerFields = model.getPlayerFields(playerIndex);

      if (targetFields.containsAll(playerFields)) {
        return true;
      }
    }
    return false;
  }
}
