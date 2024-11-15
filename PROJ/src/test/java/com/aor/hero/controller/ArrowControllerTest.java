package com.aor.hero.controller;

import com.aor.hero.Game;
import com.aor.hero.controller.game.ArrowController;
import com.aor.hero.gui.GUI;
import com.aor.hero.model.Position;
import com.aor.hero.model.game.arena.Arena;
import com.aor.hero.model.game.elements.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ArrowControllerTest {
    private ArrowController controller;
    private Hero hero;
    private Arena arena;
    private Game game;
    private Arrow arrow;

    @BeforeEach
    void setUp() {
        // Use Mockito to mock Arena
        arena = Mockito.mock(Arena.class);

        hero = new Hero(15, 17);
        arrow = new Arrow(hero.getPosition());

        // Setup mocked behavior for arena
        when(arena.getHero()).thenReturn(hero);
        when(arena.getArrows()).thenReturn(new ArrayList<>(Arrays.asList(arrow)));
        when(arena.hitWall(any(Position.class))).thenReturn(false);
        when(arena.hitMonster(any(Position.class))).thenReturn(false);
        when(arena.hitMushroom(any(Position.class))).thenReturn(false);
        when(arena.hitCentipede(any(Position.class))).thenReturn(false);

        controller = new ArrowController(arena);
    }

    @Test
    public void testArrowMovement() throws IOException {
        controller.step(game, GUI.ACTION.NONE, 100);
        assertEquals(new Position(15, 16), arrow.getPosition());
    }

    @Test
    public void testArrowCollisionWithWall() throws IOException {
        when(arena.hitWall(any(Position.class))).thenReturn(true);
        controller.step(game, GUI.ACTION.NONE, 1000);
        assertTrue(arena.getArrows().isEmpty());
    }

    @Test
    public void testArrowCollisionWithMonster() throws IOException {
        when(arena.hitMonster(any(Position.class))).thenReturn(true);
        controller.step(game, GUI.ACTION.NONE, 100);
        assertTrue(arena.getArrows().isEmpty());
    }

    @Test
    public void testArrowCollisionWithMushroom() throws IOException {
        when(arena.hitMushroom(any(Position.class))).thenReturn(true);
        controller.step(game, GUI.ACTION.NONE, 100);
        assertTrue(arena.getArrows().isEmpty());
    }

    @Test
    public void testArrowCollisionWithCentipede() throws IOException {
        // Arrange
        CentipedeParts centipedePart = new CentipedeParts(15, 16);
        Mushroom mushroom = new Mushroom(15, 16);

        // Set up the centipede and mushrooms in the arena
        when(arena.getCentipede()).thenReturn(new ArrayList<>(Arrays.asList(centipedePart)));
        when(arena.getMushrooms()).thenReturn(new ArrayList<>(Arrays.asList(mushroom)));

        // Simulate collision with centipede
        when(arena.hitCentipede(any(Position.class))).thenAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            Position position = (Position) args[0];
            return position.equals(centipedePart.getPosition());
        });

        // Act
        controller.step(game, GUI.ACTION.NONE, 100);

        // Assert
        assertTrue(arena.getArrows().isEmpty(), "Arrow should be removed after collision");
        assertTrue(arena.getMushrooms().contains(mushroom), "Mushroom should be present after collision");
    }

}
