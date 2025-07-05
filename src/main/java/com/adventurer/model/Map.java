package com.adventurer.model;

/**
 * Représente la carte du jeu avec les bois impénétrables et les espaces libres
 */
public class Map {
    private char[][] grid;

    public Map(char[][] grid) {
        this.grid = grid;
    }

    /**
     * Vérifie si une position est valide sur la carte
     * @param x coordonnée x
     * @param y coordonnée y
     * @return true si la position est valide, false sinon
     */
    public boolean isValidPosition(int x, int y) {
        // TODO: Implémenter la validation de position
        return false;
    }

    /**
     * Vérifie si une position est libre (pas de bois)
     * @param x coordonnée x
     * @param y coordonnée y
     * @return true si la position est libre, false sinon
     */
    public boolean isFreeSpace(int x, int y) {
        // TODO: Implémenter la vérification d'espace libre
        return false;
    }
} 