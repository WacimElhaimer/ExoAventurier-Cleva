package com.adventurer.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AdventurerTest {
    private Adventurer adventurer;
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
        adventurer = new Adventurer(4, 0); // Position initiale dans un espace libre
    }

    @Test
    void constructor_shouldSetInitialPosition() {
        assertEquals(4, adventurer.getX(), "Initial X position should be set");
        assertEquals(0, adventurer.getY(), "Initial Y position should be set");
    }

    @Test
    void move_shouldMoveNorth() {
        // Setup: place adventurer at bottom of free space
        adventurer.setPosition(4, 2);
        
        // When: move north
        boolean result = adventurer.move('N', map);
        
        // Then
        assertTrue(result, "Movement should succeed");
        assertEquals(4, adventurer.getX(), "X should not change");
        assertEquals(1, adventurer.getY(), "Y should decrease");
    }

    @Test
    void move_shouldMoveSouth() {
        // Setup: place adventurer at top of free space
        adventurer.setPosition(4, 1);
        
        // When: move south
        boolean result = adventurer.move('S', map);
        
        // Then
        assertTrue(result, "Movement should succeed");
        assertEquals(4, adventurer.getX(), "X should not change");
        assertEquals(2, adventurer.getY(), "Y should increase");
    }

    @Test
    void move_shouldMoveEast() {
        // Setup: place adventurer in free space
        adventurer.setPosition(4, 1);
        
        // When: move east
        boolean result = adventurer.move('E', map);
        
        // Then
        assertTrue(result, "Movement should succeed");
        assertEquals(5, adventurer.getX(), "X should increase");
        assertEquals(1, adventurer.getY(), "Y should not change");
    }

    @Test
    void move_shouldMoveWest() {
        // Setup: place adventurer in free space
        adventurer.setPosition(5, 1);
        
        // When: move west
        boolean result = adventurer.move('O', map);
        
        // Then
        assertTrue(result, "Movement should succeed");
        assertEquals(4, adventurer.getX(), "X should decrease");
        assertEquals(1, adventurer.getY(), "Y should not change");
    }

    @Test
    void move_shouldReturnFalse_whenMovingIntoWall() {
        // Setup: place adventurer next to wall
        adventurer.setPosition(4, 0);
        
        // When: try to move into wall
        boolean result = adventurer.move('O', map);
        
        // Then
        assertFalse(result, "Movement should fail");
        assertEquals(4, adventurer.getX(), "Position should not change");
        assertEquals(0, adventurer.getY(), "Position should not change");
    }

    @Test
    void move_shouldReturnFalse_whenMovingOutOfBounds() {
        // Setup: place adventurer at edge
        adventurer.setPosition(0, 1);
        
        // When: try to move out of bounds
        boolean result = adventurer.move('O', map);
        
        // Then
        assertFalse(result, "Movement should fail");
        assertEquals(0, adventurer.getX(), "Position should not change");
        assertEquals(1, adventurer.getY(), "Position should not change");
    }

    @Test
    void move_shouldThrowException_forInvalidDirection() {
        assertThrows(IllegalArgumentException.class, () -> {
            adventurer.move('X', map);
        }, "Invalid direction should throw exception");
    }

    @Test
    void executeMovements_shouldExecuteValidSequence() {
        // Setup
        adventurer.setPosition(3, 0);
        String movements = "SSSSEEEEEENN";
        
        // When
        boolean result = adventurer.executeMovements(movements, map);
        
        // Then
        assertTrue(result, "Movement sequence should succeed");
        assertEquals(9, adventurer.getX(), "Final X position should be 9");
        assertEquals(2, adventurer.getY(), "Final Y position should be 2");
    }
} 