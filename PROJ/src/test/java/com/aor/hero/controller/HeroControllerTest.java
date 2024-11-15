package com.aor.hero.controller;

import com.aor.hero.controller.game.HeroController;
import com.aor.hero.model.Position;
import com.aor.hero.model.game.arena.Arena;
import com.aor.hero.model.game.elements.Hero;
import com.aor.hero.model.game.elements.Monster;
import com.aor.hero.model.game.elements.CentipedeParts;
import com.aor.hero.model.game.elements.Wall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HeroControllerTest {
    private HeroController controller;
    private Hero hero;
    private Arena arena;

    @BeforeEach
    void setUp() {
        arena = new Arena(30, 20);

        hero = new Hero(15, 17);
        arena.setHero(hero);

        arena.setWalls(Arrays.asList());
        arena.setMushrooms(Arrays.asList());
        arena.setCentipede(Arrays.asList());
        arena.setMonsters(Arrays.asList());
        controller = new HeroController(arena);
    }

    @Test
    void moveHeroRightEmpty() {
        controller.moveHeroRight();
        assertEquals(new Position(16, 17), hero.getPosition());
    }

    @Test
    void moveHeroRightNotEmpty() {
        arena.setWalls(Arrays.asList(new Wall(16, 17)));
        controller.moveHeroRight();
        assertEquals(new Position(15, 17), hero.getPosition());
    }

    @Test
    void moveHeroLeftEmpty() {
        controller.moveHeroLeft();
        assertEquals(new Position(14, 17), hero.getPosition());
    }

    @Test
    void moveHeroLeftNotEmpty() {
        arena.setWalls(Arrays.asList(new Wall(14, 17)));
        controller.moveHeroLeft();
        assertEquals(new Position(15, 17), hero.getPosition());
    }

    @Test
    void moveHeroUpEmpty() {
        controller.moveHeroUp();
        assertEquals(new Position(15, 16), hero.getPosition());
    }

    @Test
    void moveHeroUpNotEmpty() {
        arena.setWalls(Arrays.asList(new Wall(15, 16)));
        controller.moveHeroUp();
        assertEquals(new Position(15, 17), hero.getPosition());
    }

    @Test
    void moveHeroDownEmpty() {
        controller.moveHeroDown();
        assertEquals(new Position(15, 18), hero.getPosition());
    }

    @Test
    void moveHeroDownNotEmpty() {
        arena.setWalls(Arrays.asList(new Wall(15, 18)));
        controller.moveHeroDown();
        assertEquals(new Position(15, 17), hero.getPosition());
    }

    @Test
    void moveHeroIntoMonster() {
        arena.setMonsters(Arrays.asList(new Monster(16, 17)));
        controller.moveHeroRight();
        assertEquals(2, hero.getEnergy()); // Verify that hero's energy decreases when colliding with a monster
    }

    @Test
    void moveHeroIntoCentipedePart() {
        arena.setCentipede(Arrays.asList(new CentipedeParts(16, 17))); // Assuming you have a CentipedePart class
        controller.moveHeroRight();
        assertEquals(2, hero.getEnergy()); // Verify that hero's energy decreases when colliding with a centipede part
    }
}