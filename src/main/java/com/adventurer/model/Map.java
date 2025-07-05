package com.adventurer.model;

/**
 * Représente la carte du jeu avec les bois impénétrables et les espaces libres
 */
public class Map {
    private final char[][] grid;
    private static final char WALL = '#';
    private static final char EMPTY = ' ';

    /**
     * Crée une nouvelle carte à partir d'une grille de caractères
     * @param grid la grille représentant la carte
     * @throws IllegalArgumentException si la grille est invalide
     */
    public Map(char[][] grid) {
        validateGrid(grid);
        this.grid = deepCopyGrid(grid);
    }

    /**
     * Vérifie si une position est valide sur la carte
     * @param x coordonnée x
     * @param y coordonnée y
     * @return true si la position est valide, false sinon
     */
    public boolean isValidPosition(int x, int y) {
        return y >= 0 && y < grid.length && x >= 0 && x < grid[0].length;
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

    /**
     * Retourne une représentation textuelle de la carte
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < getHeight(); y++) {
            if (y > 0) {
                sb.append("\n");
            }
            sb.append(new String(grid[y]));
        }
        return sb.toString();
    }

    /**
     * Valide la grille fournie
     * @param grid la grille à valider
     * @throws IllegalArgumentException si la grille est invalide
     */
    private void validateGrid(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            throw new IllegalArgumentException("Grid cannot be null or empty");
        }

        int width = grid[0].length;
        for (int y = 0; y < grid.length; y++) {
            if (grid[y] == null || grid[y].length != width) {
                throw new IllegalArgumentException("All rows must have the same width");
            }
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] != WALL && grid[y][x] != EMPTY) {
                    throw new IllegalArgumentException(
                        String.format("Invalid character '%c' at position (%d,%d)", grid[y][x], x, y)
                    );
                }
            }
        }
    }

    /**
     * Crée une copie profonde de la grille
     * @param original la grille à copier
     * @return une nouvelle grille avec les mêmes valeurs
     */
    private char[][] deepCopyGrid(char[][] original) {
        char[][] copy = new char[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i].clone();
        }
        return copy;
    }
} 