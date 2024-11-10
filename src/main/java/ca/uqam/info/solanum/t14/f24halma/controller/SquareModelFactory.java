package ca.uqam.info.solanum.t14.f24halma.controller;

import ca.uqam.info.solanum.inf2050.f24halma.controller.ModelFactory;
import ca.uqam.info.solanum.inf2050.f24halma.model.Board;
import ca.uqam.info.solanum.inf2050.f24halma.model.Field;
import ca.uqam.info.solanum.inf2050.f24halma.model.FieldException;
import ca.uqam.info.solanum.inf2050.f24halma.model.Model;
import ca.uqam.info.solanum.inf2050.f24halma.model.ModelInitializationException;
import ca.uqam.info.solanum.inf2050.f24halma.model.ModelReadOnly;
import ca.uqam.info.solanum.t14.f24halma.model.BoardImpl;
import ca.uqam.info.solanum.t14.f24halma.model.ModelImpl;
import java.util.HashSet;
import java.util.Set;

/**
 * Fabrique de modèles de jeu pour le jeu de Halma, qui permet de créer des instances de modèles
 * de type carré.
 */
public class SquareModelFactory implements ModelFactory {

  /**
   * Constructeur par défaut de la classe {@code SquareModelFactory}.
   * Initialise une fabrique pour créer des modèles de jeu pour le plateau de type carré
   * dans le jeu de Halma.
   */
  public SquareModelFactory() {
    // Constructeur par défaut
  }
  /**
   * Crée une instance du modèle de jeu avec les configurations spécifiées pour la taille
   * du plateau et les joueurs.
   * Cette méthode initialise le modèle de jeu en créant un plateau de jeu de taille
   * spécifiée et en attribuant les
   * joueurs. Elle vérifie également les contraintes suivantes :
   * - La taille de la base ne peut pas être supérieure à 33.
   * - Le nombre de joueurs doit être pair et ne peut pas excéder 4.
   * Si l'une de ces contraintes n'est pas respectée, une exception
   * {@link ModelInitializationException} sera lancée.
   *
   * @param baseSize La taille de base du plateau de jeu. Elle détermine la taille du plateau.
   * @param players Un tableau de chaînes représentant les noms des joueurs. Le nombre
   *                de joueurs doit être pair et ne pas dépasser 4.
   * @return Une instance de {@link ModelImpl} représentant le modèle de jeu.
   * @throws ModelInitializationException Si la taille de base est supérieure à 33
   */

  @Override
  public ModelImpl createModel(int baseSize, String[] players) throws ModelInitializationException {

    if (baseSize > 33) {
      throw new ModelInitializationException("La baseSize ne peut être de plus de 33.");
    }
    if (players.length % 2 != 0 || players.length > 4) {
      throw new ModelInitializationException("Le nombre de joueurs doit être un nombre pair.");
    }

    // Créer le plateau de jeu Halma
    Set<Field> allFields = createFields(baseSize * 3); // plateau
    Set<Field> homeFields = createHomeFields(baseSize, players.length); // zone de chaque joueur

    Board board = new BoardImpl(baseSize, players);
    return new ModelImpl(board, players) {
      /**
       * Returns all fields currently occupied by the requested player,
       * identified by player index.
       *
       * @param playerIndex as the player for whom the occupied fields are to be identified.
       * @return unmodifiable set of all fields currently occupied by a player.
       */
      @Override
  public Set<Field> getPlayerFields(int playerIndex) {
        return Set.of();
      }

      /**
       * Getter for the index of the current player.
       *
       * @return int representing the index of the current player.
       */
      @Override
  public int getCurrentPlayer() {

        return currentPlayerIndex;
      }

      /**
       * Returns true if the specified field is unoccupied.
       *
       * @param field the field to look up. Must be a valid field on the board.
       * @return true if the provided field exists and is unoccupied.
       * @throws FieldException if the provided field is not a valid board position
       */
      @Override
   public boolean isClear(Field field) throws FieldException {
        return false;
      }
    };
  }

  /**
   * Cree tous les champs du plateau de la taille specifiee en parametre.
   *
   * @param boardSize la taille totale du plateau.
   * @return le champ cree
   */
  private Set<Field> createFields(int boardSize) {
    Set<Field> fields = new HashSet<>();

    // Créer des cases pour chaque position sur le plateau
    for (int x = 0; x < boardSize; x++) {
      for (int y = 0; y < boardSize; y++) {
        // On crée chaque champ avec une position (x, y)
        fields.add(new Field(x, y));
      }
    }

    return fields;
  }

  /**
   * Crée les zones de depart de chaque joueur.
   *
   * @param baseSize la taille de base de chaque joueur
   * @param numberOfPlayers le nombre total de joueurs.
   * @return un ensemble de champs qui representent toutes les zones de depart
   */
  private Set<Field> createHomeFields(int baseSize, int numberOfPlayers) {
    Set<Field> homeFields = new HashSet<>();
    // Logique pour créer les zones de départ pour le nombre de joueurs
    // Exemple : ajouter des objets Field représentant les zones de départ
    return homeFields; // Retourner les zones de départ créées
  }
}


