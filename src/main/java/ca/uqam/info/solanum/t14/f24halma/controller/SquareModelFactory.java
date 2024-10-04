package ca.uqam.info.solanum.t14.f24halma.controller;

import ca.uqam.info.solanum.inf2050.f24halma.controller.ModelFactory;
import ca.uqam.info.solanum.inf2050.f24halma.model.*;
import ca.uqam.info.solanum.t14.f24halma.model.BoardImpl;
import ca.uqam.info.solanum.t14.f24halma.model.ModelImpl;
import java.util.HashSet;
import java.util.Set;

public class SquareModelFactory implements ModelFactory {

    @Override
    public ModelImpl createModel(int baseSize, String[] players) throws ModelInitializationException {
        if (players.length % 2 != 0) {
            throw new ModelInitializationException("Le nombre de joueurs doit être un nombre pair.");
        }

        // Créer le plateau de jeu Halma
        Set<Field> allFields = createFields(baseSize*baseSize); // plateau
        Set<Field> homeFields = createHomeFields(baseSize, players.length); // zone de chaque joueur

        Board board = new BoardImpl();
        return new ModelImpl() {
            @Override
            public Set<Field> getPlayerFields(int playerIndex) {
                return null;
            }
            
            @Override
            public int getCurrentPlayer() {
                return players.length;
            }

            @Override
            public boolean isClear(Field field) throws FieldException {
                return false;
            }
        };
    }

    private Set<Field> createFields(int boardSize) {
        Set<Field> fields = new HashSet<>();
        // Logique pour créer tous les champs basés sur la taille de base
        // Exemple : ajouter des objets Field à l'ensemble fields
        return fields; // Retourner les champs créés
    }

    private Set<Field> createHomeFields(int baseSize, int numberOfPlayers) {
        Set<Field> homeFields = new HashSet<>();
        // Logique pour créer les zones de départ pour le nombre de joueurs
        // Exemple : ajouter des objets Field représentant les zones de départ
        return homeFields; // Retourner les zones de départ créées
    }
}

