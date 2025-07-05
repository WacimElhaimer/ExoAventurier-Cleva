package com.adventurer.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MapTest {
    private Map map;
    private static final char[][] TEST_GRID = {
        "###    ######    ###".toCharArray(),
        "###      ##      ###".toCharArray(),
        "##     ##  ##     ##".toCharArray(),
        "#      ##  ##      #".toCharArray()
    };

    @BeforeEach
    void setUp() {
        map = new Map(TEST_GRID);
    }

    @Test
    void isValidPosition_shouldReturnTrue_forValidPositions() {
        // Test des positions valides aux bords
        assertTrue(map.isValidPosition(0, 0), "Position (0,0) should be valid");
        assertTrue(map.isValidPosition(TEST_GRID[0].length - 1, 0), "Last column should be valid");
        assertTrue(map.isValidPosition(0, TEST_GRID.length - 1), "Last row should be valid");
        
        // Test des positions valides à l'intérieur
        assertTrue(map.isValidPosition(5, 2), "Interior position should be valid");
    }

    @Test
    void isValidPosition_shouldReturnFalse_forInvalidPositions() {
        // Test des positions invalides
        assertFalse(map.isValidPosition(-1, 0), "Negative X should be invalid");
        assertFalse(map.isValidPosition(0, -1), "Negative Y should be invalid");
        assertFalse(map.isValidPosition(TEST_GRID[0].length, 0), "Position after last column should be invalid");
        assertFalse(map.isValidPosition(0, TEST_GRID.length), "Position after last row should be invalid");
    }

    @Test
    void isFreeSpace_shouldReturnTrue_forEmptySpaces() {
        // Test des espaces vides
        assertTrue(map.isFreeSpace(4, 0), "Space at (4,0) should be free");
        assertTrue(map.isFreeSpace(6, 1), "Space at (6,1) should be free");
    }

    @Test
    void isFreeSpace_shouldReturnFalse_forWalls() {
        // Test des murs
        assertFalse(map.isFreeSpace(0, 0), "Wall at (0,0) should not be free");
        assertFalse(map.isFreeSpace(1, 0), "Wall at (1,0) should not be free");
    }

    @Test
    void isFreeSpace_shouldReturnFalse_forInvalidPositions() {
        // Test des positions invalides
        assertFalse(map.isFreeSpace(-1, 0), "Invalid position should not be free");
        assertFalse(map.isFreeSpace(0, -1), "Invalid position should not be free");
        assertFalse(map.isFreeSpace(TEST_GRID[0].length, 0), "Invalid position should not be free");
    }

    @Test
    void getDimensions_shouldReturnCorrectValues() {
        assertEquals(TEST_GRID[0].length, map.getWidth(), "Width should match test grid");
        assertEquals(TEST_GRID.length, map.getHeight(), "Height should match test grid");
    }

    @Test
    void toString_shouldReturnReadableRepresentation() {
        String mapString = map.toString();
        assertNotNull(mapString, "toString should not return null");
        assertTrue(mapString.contains("#"), "Map string should contain walls");
        assertTrue(mapString.contains(" "), "Map string should contain empty spaces");
        
        // Vérifie que chaque ligne a la même longueur
        String[] lines = mapString.split("\n");
        int expectedLength = lines[0].length();
        for (String line : lines) {
            assertEquals(expectedLength, line.length(), "All lines should have the same length");
        }
    }
} 