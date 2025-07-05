package com.adventurer.engine;

import com.adventurer.model.Adventurer;
import com.adventurer.model.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameEngineTest {
    private GameEngine gameEngine;
    private Map map;
    private Adventurer adventurer;

    private static final char[][] TEST_GRID = {
        "###    ######    ###".toCharArray(),
        "###      ##      ###".toCharArray(),
        "##     ##  ##     ##".toCharArray(),
        "#      ##  ##      #".toCharArray()
    };

    @BeforeEach
    void setUp() {
        map = new Map(TEST_GRID);
        adventurer = new Adventurer(4, 0);
        gameEngine = new GameEngine(map, adventurer);
    }

    @Test
    void constructor_shouldInitializeCorrectly() {
        assertNotNull(gameEngine, "GameEngine should be initialized");
        assertEquals(4, adventurer.getX(), "Adventurer X position should be set");
        assertEquals(0, adventurer.getY(), "Adventurer Y position should be set");
    }

    @Test
    void executeMovements_shouldSucceedForValidPath() {
        // Test du premier scénario fourni
        adventurer = new Adventurer(3, 0);
        gameEngine = new GameEngine(map, adventurer);
        
        boolean result = gameEngine.executeMovements("SSSSEEEEEENN");
        
        assertTrue(result, "Movement sequence should succeed");
        assertEquals(9, adventurer.getX(), "Final X position should be 9");
        assertEquals(2, adventurer.getY(), "Final Y position should be 2");
    }

    @Test
    void executeMovements_shouldHandleEmptySequence() {
        int initialX = adventurer.getX();
        int initialY = adventurer.getY();
        
        boolean result = gameEngine.executeMovements("");
        
        assertTrue(result, "Empty movement sequence should succeed");
        assertEquals(initialX, adventurer.getX(), "X position should not change");
        assertEquals(initialY, adventurer.getY(), "Y position should not change");
    }

    @Test
    void executeMovements_shouldFailForInvalidDirection() {
        int initialX = adventurer.getX();
        int initialY = adventurer.getY();
        
        assertThrows(IllegalArgumentException.class, () -> {
            gameEngine.executeMovements("NSXEO");
        }, "Invalid direction should throw exception");
        
        assertEquals(initialX, adventurer.getX(), "X position should not change after failure");
        assertEquals(initialY, adventurer.getY(), "Y position should not change after failure");
    }

    @Test
    void executeMovements_shouldStopAtObstacle() {
        adventurer.setPosition(4, 0);
        
        boolean result = gameEngine.executeMovements("EEEEE");
        
        assertFalse(result, "Movement sequence should fail at obstacle");
        assertTrue(adventurer.getX() < 8, "Adventurer should stop before wall");
        assertEquals(0, adventurer.getY(), "Y position should not change");
    }

    @Test
    void executeMovements_shouldHandleComplexPath() {
        // Test du deuxième scénario fourni
        adventurer = new Adventurer(6, 9);
        gameEngine = new GameEngine(map, adventurer);
        
        boolean result = gameEngine.executeMovements("OONOOOSSO");
        
        assertTrue(result, "Complex movement sequence should succeed");
        assertEquals(7, adventurer.getX(), "Final X position should be 7");
        assertEquals(5, adventurer.getY(), "Final Y position should be 5");
    }

    @Test
    void executeMovements_shouldHandleBoundaryMovements() {
        // Place l'aventurier près du bord
        adventurer.setPosition(1, 1);
        
        boolean result = gameEngine.executeMovements("OOOOO");
        
        assertFalse(result, "Movement sequence should fail at boundary");
        assertEquals(1, adventurer.getX(), "Adventurer should not move past boundary");
        assertEquals(1, adventurer.getY(), "Y position should not change");
    }

    @Test
    void getCurrentPosition_shouldReturnCorrectPosition() {
        adventurer.setPosition(5, 2);
        
        int[] position = gameEngine.getCurrentPosition();
        
        assertArrayEquals(new int[]{5, 2}, position, "Should return correct position");
    }
} 