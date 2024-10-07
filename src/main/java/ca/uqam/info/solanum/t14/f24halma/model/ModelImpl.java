package ca.uqam.info.solanum.t14.f24halma.model;

import ca.uqam.info.solanum.inf2050.f24halma.model.Board;
import ca.uqam.info.solanum.inf2050.f24halma.model.Field;
import ca.uqam.info.solanum.inf2050.f24halma.model.FieldException;
import ca.uqam.info.solanum.inf2050.f24halma.model.Model;

import java.util.Arrays;
import java.util.Set;

/**
 * Implémentation du modèle de jeu.
 *
 * Cette classe gère les joueurs, le plateau de jeu et les interactions entre eux.
 */
public class ModelImpl implements Model {
    private int playerIndex;          // L'index du joueur actuel
    private final String[] playerNames; // Noms des joueurs
    private final Board board;        // Plateau de jeu

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
     * Occupe le champ cible par le joueur
     *
     * @param playerIndex l'index du joueur qui va occuper le champ specifie
     * @param field le champ vise par le joueur
     * @throws FieldException en cas de champ déjà occupé.
     */
    @Override
    public void occupyField(int playerIndex, Field field) throws FieldException {
    }

    /**
     * Vide le champ donné en parametre.
     *
     * @param field le champ cible à vider de toute occupation potentielle.
     * @throws FieldException en cas de champ actuellement non occupé.
     */
    @Override
    public void clearField(Field field) throws FieldException {
    }

    /**
     * Setter vers le joueur specifie en parametre
     *
     * @param playerIndex l'index du joueur cible par le setter
     */
    @Override
    public void setCurrentPlayer(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    /**
     * Retourne les noms des joueurs sous forme de tableau de chaînes dans l'ordre.
     *
     * @return une copie des noms des joueurs dans l'ordre.
     */
    @Override
    public String[] getPlayerNames() {
        return playerNames.clone();
    }

    /**
     * Getter des zones occupees par le joueur specifie en parametre
     *
     * @param playerIndex l'index du joueur pour lequel on verifie les zones occupees
     * @return un ensemble de tous les champs actuellement occupés par un joueur.
     */
    @Override
    public Set<Field> getPlayerFields(int playerIndex) {
        return Set.of(); // Logique à implémenter
    }

    /**
     * Getter pour l'index du joueur actuel.
     *
     * @return un int représentant l'index du joueur actuel.
     */
    @Override
    public int getCurrentPlayer() {
        return playerIndex;
    }

    /**
     * Getter pour le plateau sur lequel on joue
     *
     * @return l'instance du plateau de ce modèle.
     */
    @Override
    public Board getBoard() {
        return board;  // Retourne le plateau initialisé
    }

    /**
     * Verifie si le field en parametre est libre
     *
     * @param field le champ à vérifier. Doit être un champ valide sur le plateau.
     * @return vrai si le champ fourni existe et est inoccupé.
     * @throws FieldException si le champ fourni n'est pas une position valide sur le plateau.
     */
    @Override
    public boolean isClear(Field field) throws FieldException {
        return false; // Logique à implémenter
    }

    /**
     * Methode pour verifier l'egalite des objets
     *
     * @param obj l'objet a verifier
     * @return vrai si l'egalite est verifiee.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Vérifie si c'est le même objet
        if (!(obj instanceof ModelImpl other)) return false; // Vérifie si l'objet est de type ModelImpl

        // Vérifie les joueurs
        return Arrays.equals(this.playerNames, other.playerNames) &&
                this.playerIndex == other.playerIndex &&
                this.board.equals(other.board); // verification des egalites
    }

    /**
     * Methode de hashage pour le code
     *
     * @return le resultat du hashage du code
     */
    @Override
    public int hashCode() {
        int result = Arrays.hashCode(playerNames);
        result = 31 * result + playerIndex; // Multiplier par un nombre premier pour réduire les collisions
        result = 31 * result + (board != null ? board.hashCode() : 0); // Ajoute le hashcode du board
        return result;
    }
}

