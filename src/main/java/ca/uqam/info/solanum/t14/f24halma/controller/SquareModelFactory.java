package ca.uqam.info.solanum.t14.f24halma.controller;

import ca.uqam.info.solanum.inf2050.f24halma.controller.ModelFactory;
import ca.uqam.info.solanum.inf2050.f24halma.model.*;
import ca.uqam.info.solanum.t14.f24halma.model.BoardImpl;
import ca.uqam.info.solanum.t14.f24halma.model.ModelImpl;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe qui implémente ModelFactory et qui creer un ModelFactory carre.
 */
public class SquareModelFactory implements ModelFactory {

    /**
     * methode pour creer un modele
     *
     * @param baseSize as the side-length along all player bases.
     * @param players  as the player names involved on the board. Must be an even number and
     *                 compatible to created layout. Note: the created model's board always contains
     *                 home-zones for the maximum allowed amount * of players for the requested
     *                 layout.
     * @return un nouveau modele implemente avec les donnees en parametre
     * @throws ModelInitializationException
     */


    @Override
    public ModelImpl createModel(int baseSize, String[] players) throws ModelInitializationException {

        if(baseSize > 33) {
            throw new ModelInitializationException("La baseSize ne peut être de plus de 33.");
        }

        if (players.length % 2 != 0 || players.length > 4) {
            throw new ModelInitializationException("Le nombre de joueurs doit être un nombre pair.");
        }

        // Créer le plateau de jeu Halma
        Set<Field> allFields = createFields(baseSize*3); // plateau
        Set<Field> homeFields = createHomeFields(baseSize, players.length); // zone de chaque joueur

        Board board = new BoardImpl(baseSize, players);
        return new ModelImpl(board, players) {
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
     * Crée les zones de depart de chaque joueur
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


