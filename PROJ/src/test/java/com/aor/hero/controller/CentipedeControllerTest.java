package com.aor.hero.controller;

import com.aor.hero.Game;
import com.aor.hero.controller.game.CentipedeController;
import com.aor.hero.gui.GUI;
import com.aor.hero.model.Position;
import com.aor.hero.model.game.arena.Arena;
import com.aor.hero.model.game.elements.CentipedeParts;
import com.aor.hero.model.game.elements.Hero;
import com.aor.hero.model.game.elements.Mushroom;
import com.aor.hero.model.game.elements.Wall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CentipedeControllerTest {

    private CentipedeController controller;
    private Hero hero;
    private Arena arena;
    private Game game;
    private CentipedeParts centipedePart;

    @BeforeEach
    void setUp() {
        arena = new Arena(30, 20);

        hero = new Hero(15, 18);
        arena.setHero(hero);

        centipedePart = new CentipedeParts(15, 2);
        arena.setCentipede(Arrays.asList(centipedePart));

        arena.setWalls(Arrays.asList());
        arena.setMushrooms(Arrays.asList());
        arena.setMonsters(Arrays.asList());

        controller = new CentipedeController(arena);

        game = Mockito.mock(Game.class);
    }

    @Test
    void testCentipedeMovement() throws IOException {
        controller.moveCentipede(centipedePart);
        assertEquals(new Position(16, 2), centipedePart.getPosition());

    }

    @Test
    void testCentipedeHitWallGoDown() throws IOException {
        arena.setMushrooms(Arrays.asList(new Mushroom(16, 2)));
        controller.moveCentipede(centipedePart);
        assertEquals(new Position(15, 3), centipedePart.getPosition());
        assertEquals(GUI.Direction.LEFT, centipedePart.getDirection());
    }
}

