package com.adventurer.model;

/**
 * Représente la carte du jeu avec les bois impénétrables et les espaces libres
 */
public class Map {
    private char[][] grid;
    private static final char WALL = '#';
    private static final char EMPTY = ' ';

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
        return y >= 0 && y < grid.length && 
               x >= 0 && x < grid[0].length;
    }

    /**
     * Vérifie si une position est libre (pas de bois)
     * @param x coordonnée x
     * @param y coordonnée y
     * @return true si la position est libre, false sinon
     */
    public boolean isFreeSpace(int x, int y) {
        if (!isValidPosition(x, y)) {
            return false;
        }
        return grid[y][x] == EMPTY;
    }

    /**
     * Retourne la hauteur de la carte
     */
    public int getHeight() {
        return grid.length;
    }

    /**
     * Retourne la largeur de la carte
     */
    public int getWidth() {
        return grid[0].length;
    }
} 