package com.aor.hero.viewer;

import com.aor.hero.gui.GUI;
import com.aor.hero.model.Position;
import com.aor.hero.model.game.arena.Arena;
import com.aor.hero.model.game.elements.*;
import com.aor.hero.viewer.game.GameViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;

class ArenaViewerTest {
    private GUI gui;
    private GameViewer viewer;
    private Arena arena;

    @BeforeEach
    void setUp() {
        arena = new Arena(30, 20);
        gui = Mockito.mock(GUI.class);
        viewer = new GameViewer(arena);

        arena.setWalls(Arrays.asList(new Wall(1, 2), new Wall(2, 3), new Wall(3, 4)));
        arena.setMushrooms(Arrays.asList(new Mushroom(2, 2), new Mushroom(3, 3), new Mushroom(4, 4)));
        arena.setMonsters(Arrays.asList(new Monster(14, 15), new Monster(16, 16)));
        arena.setHero(new Hero(15, 17));
        arena.setCentipede(Arrays.asList(new CentipedeParts(15, 2), new CentipedeParts(16, 2)));
        arena.setArrows(Arrays.asList(new Arrow(14, 6), new Arrow(14, 5), new Arrow(14, 4)));
    }


    @Test
    void drawWalls() throws IOException {
        viewer.draw(gui);
        Mockito.verify(gui, Mockito.times(1)).drawWall(new Position(1, 2));
        Mockito.verify(gui, Mockito.times(1)).drawWall(new Position(2, 3));
        Mockito.verify(gui, Mockito.times(1)).drawWall(new Position(3, 4));
        Mockito.verify(gui, Mockito.times(3)).drawWall(Mockito.any(Position.class));
    }

    @Test
    void drawMushrooms() throws IOException {
        viewer.draw(gui);
        Mockito.verify(gui, Mockito.times(1)).drawMushroom(new Position(2, 2));
        Mockito.verify(gui, Mockito.times(1)).drawMushroom(new Position(3, 3));
        Mockito.verify(gui, Mockito.times(1)).drawMushroom(new Position(4, 4));
        Mockito.verify(gui, Mockito.times(3)).drawMushroom(Mockito.any(Position.class));
    }

    @Test
    void drawMonsters() throws IOException {
        viewer.draw(gui);

        Mockito.verify(gui, Mockito.times(1)).drawMonster(new Position(14, 15));
        Mockito.verify(gui, Mockito.times(1)).drawMonster(new Position(16, 16));
    }

    @Test
    void drawHero() throws IOException {
        viewer.draw(gui);

        Mockito.verify(gui, Mockito.times(1)).drawHero(new Position(15, 17));
        Mockito.verify(gui, Mockito.times(1)).drawHero(Mockito.any(Position.class));
    }

    @Test
    void drawCentipedeSegments() throws IOException {
        viewer.draw(gui);

        Mockito.verify(gui, Mockito.times(1)).drawCentipedeSegment(new Position(15, 2));
        Mockito.verify(gui, Mockito.times(1)).drawCentipedeSegment(new Position(16, 2));
    }

    @Test
    void drawArrows() throws IOException {
        viewer.draw(gui);
        Mockito.verify(gui, Mockito.times(1)).drawArrow(new Position(14, 6));
        Mockito.verify(gui, Mockito.times(1)).drawArrow(new Position(14, 5));
        Mockito.verify(gui, Mockito.times(1)).drawArrow(new Position(14, 4));
        Mockito.verify(gui, Mockito.times(3)).drawArrow(Mockito.any(Position.class));
    }

    @Test
    void refreshAndClear() throws IOException {
        viewer.draw(gui);

        Mockito.verify(gui, Mockito.times(1)).clear();
        Mockito.verify(gui, Mockito.times(1)).refresh();
    }
}