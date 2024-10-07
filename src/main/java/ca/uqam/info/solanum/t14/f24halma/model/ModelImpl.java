package ca.uqam.info.solanum.t14.f24halma.model;

import ca.uqam.info.solanum.inf2050.f24halma.model.Field;
import ca.uqam.info.solanum.inf2050.f24halma.model.FieldException;
import ca.uqam.info.solanum.inf2050.f24halma.model.Model;
import ca.uqam.info.solanum.inf2050.f24halma.model.Board;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Implementation du modele du jeu
 *
 * Cette classe gère les joueurs, le plateau de jeu et les interactions entre eux.
 */
public class ModelImpl implements Model {
    private final String[] playerNames; // Noms des joueurs
    private Map<Field, Integer> occupiedFields = Map.of(); // Champs occupés avec l'index du joueur
    private int playerIndex;
    private Board board;
    private int currentPlayerIndex; // Index du joueur courant


    /**
     * Constructeur pour le test DefaultConsoleLauncher et SqareModelFactoryTest
     * @param board
     * @param playerNames
     */
    public ModelImpl(Board board, String[] playerNames) {
        this.board = board; // Assurez-vous que 'board' n'est pas null ici
        this.playerNames = playerNames.clone();
        this.occupiedFields = new HashMap<>();
        this.currentPlayerIndex = 0;
    }

    /**
     * Constructeur de la classe ModelImpl.
     *
     * Ce constructeur initialise le modèle avec la taille de base spécifiée et les noms des joueurs.
     *
     * @param baseSize la taille de base du plateau.
     * @param players  tableau des noms des joueurs.
     */
    public ModelImpl(int baseSize, String[] players) {
        this.playerNames = players.clone();
        this.playerIndex = 0;  // Le joueur 0 commence par défaut
        int currentPlayer = 0;

        // Initialisation du plateau avec la taille de base et les noms des joueurs
        this.board = new BoardImpl(baseSize, players);  // Assigner à la variable d'instance
    }

    /**
     * Method to allow a player to occupy a field
     *
     * @param playerIndex index of the player who wants to occupy the specified field
     * @param field       the field instance to assign to the player.
     * @throws FieldException
     */
    @Override
    public void occupyField(int playerIndex, Field field) throws FieldException {
        // Vérifie si le champ est déjà occupé
        if (occupiedFields.containsKey(field)) {
            throw new FieldException("Ce champ est déjà occupé.");
        }
        // Assigne le champ au joueur
        occupiedFields.put(field, playerIndex);
    }

    /**
     * Method to clear the current field
     *
     * @param field as the target field to clear from any potential occupation.
     * @throws FieldException
     */
    @Override
    public void clearField(Field field) throws FieldException {
        // Vérifie si le champ est occupé
        if (!occupiedFields.containsKey(field)) {
            throw new FieldException("Ce champ n'est pas occupé.");
        }
        // Supprime l'occupation du champ
        occupiedFields.remove(field);
    }

    /**
     * Setter to point to the current player
     *
     * @param playerIndex Index of the specified player
     */
    @Override
    public void setCurrentPlayer(int playerIndex) {
        if (playerIndex < 0 || playerIndex >= playerNames.length) {
            throw new IllegalArgumentException("Index de joueur invalide.");
        }
        currentPlayerIndex = playerIndex;
    }

    /**
     * Getter for the game board
     * @return board on which the game is played
     */
    @Override
    public Board getBoard() {
        return board;
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

    /**
     * Getter for the Names of all the players
     *
     * @return copy of the names of all the players
     */
    @Override
    public String[] getPlayerNames() {
        return playerNames.clone(); // Retourne une copie pour éviter la modification externe
    }

    /**
     * Returns all fields currently occupied by the requested player, identified by player index.
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
        return 0;
    }

    /**
     * Getter for the current index of the player
     * @return current index of the player
     */
    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    /**
     * Methode pour verifier l'egalite des objets
     *
     * @param obj l'objet a verifier
     * @return vrai si l'egalite est verifiee.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ModelImpl)) return false;
        ModelImpl other = (ModelImpl) obj;
        return board.equals(other.board) && currentPlayerIndex == other.currentPlayerIndex;
    }
}


