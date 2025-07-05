package com.adventurer.engine;

import com.adventurer.model.Adventurer;
import com.adventurer.model.Map;

/**
 * Moteur du jeu qui gère l'interaction entre la carte et l'aventurier
 */
public class GameEngine {
    private final Map map;
    private final Adventurer adventurer;

    /**
     * Crée un nouveau moteur de jeu
     * @param map la carte du jeu
     * @param adventurer l'aventurier
     * @throws IllegalArgumentException si la position initiale de l'aventurier est invalide
     */
    public GameEngine(Map map, Adventurer adventurer) {
        this.map = map;
        this.adventurer = adventurer;
        validateInitialPosition();
    }

    /**
     * Exécute une séquence de mouvements
     * @param movements la chaîne de caractères représentant les mouvements
     * @return true si tous les mouvements ont été exécutés avec succès
     * @throws IllegalArgumentException si un mouvement est invalide
     */
    public boolean executeMovements(String movements) {
        if (movements == null || movements.isEmpty()) {
            return true;
        }

        // Valide que la séquence ne contient que des mouvements valides
        if (!movements.matches("[NSEO]+")) {
            throw new IllegalArgumentException("Invalid movement sequence. Only N,S,E,O are allowed");
        }

        return adventurer.executeMovements(movements, map);
    }

    /**
     * Retourne la position actuelle de l'aventurier
     * @return un tableau contenant les coordonnées [x,y]
     */
    public int[] getCurrentPosition() {
        return new int[]{adventurer.getX(), adventurer.getY()};
    }

    /**
     * Vérifie si la position initiale de l'aventurier est valide
     * @throws IllegalArgumentException si la position est invalide
     */
    private void validateInitialPosition() {
        if (!map.isValidPosition(adventurer.getX(), adventurer.getY())) {
            throw new IllegalArgumentException(
                String.format("Invalid initial position (%d,%d): out of bounds", 
                    adventurer.getX(), adventurer.getY())
            );
        }
        if (!map.isFreeSpace(adventurer.getX(), adventurer.getY())) {
            throw new IllegalArgumentException(
                String.format("Invalid initial position (%d,%d): occupied by wall", 
                    adventurer.getX(), adventurer.getY())
            );
        }
    }

    @Override
    public String toString() {
        return String.format("Game state: %s", adventurer.toString());
    }
} 