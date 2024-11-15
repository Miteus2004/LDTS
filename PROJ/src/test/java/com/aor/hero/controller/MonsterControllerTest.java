package com.aor.hero.controller;

import com.aor.hero.Game;
import com.aor.hero.controller.game.MonsterController;
import com.aor.hero.gui.GUI;
import com.aor.hero.model.Position;
import com.aor.hero.model.game.arena.Arena;
import com.aor.hero.model.game.elements.Hero;
import com.aor.hero.model.game.elements.Monster;
import com.aor.hero.model.game.elements.Mushroom;
import com.aor.hero.model.game.elements.Wall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class MonsterControllerTest {
    private MonsterController controller;
    private Hero hero;
    private Arena arena;
    private Game game;
    private Monster monster;
    @BeforeEach
    void setUp() {
        arena = new Arena(30, 20);

        hero = new Hero(15, 18);
        arena.setHero(hero);

        monster = new Monster(15, 15);
        arena.setMonsters(Arrays.asList(monster));

        arena.setWalls(Arrays.asList());
        arena.setMushrooms(Arrays.asList());
        arena.setCentipede(Arrays.asList());

        controller = new MonsterController(arena);

        game = Mockito.mock(Game.class);
    }

    @Test
    void moveMonsters() throws IOException {
        controller.moveMonster(monster);
        assertNotEquals(new Position(15, 15), monster.getPosition());
    }

    @Test
    void moveMonstersClosed() throws IOException {
        arena.setMushrooms(Arrays.asList(
                new Mushroom(14, 14),
                new Mushroom(16, 14),
                new Mushroom(14, 16),
                new Mushroom(16, 16)
        ));
        controller.moveMonster(monster);
        assertNotEquals(new Position(15, 15), monster.getPosition());
    }

    @Test
    void moveMonstersGap() throws IOException {
        arena.setMushrooms(Arrays.asList(
                new Mushroom(14, 16),
                new Mushroom(16, 14),
                new Mushroom(14, 14)
        ));

        controller.moveMonster(monster);
        assertEquals(new Position(16, 16), monster.getPosition());
    }
}