package com.aor.hero.model.game.arena;
import com.aor.hero.model.game.elements.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomArenaBuilder extends ArenaBuilder {
    private final Random rng;

    private final int width;
    private final int height;
    private final int numberOfMonsters;

    private final int numberOfMushrooms;

    private final int numberOfCentipedes;

    public RandomArenaBuilder(int width, int height, int numberOfMonsters, int numberOfMushrooms, int numberOfCentipedes) {
        this.rng = new Random();

        this.width = width;
        this.height = height;
        this.numberOfMonsters = numberOfMonsters;
        this.numberOfMushrooms = numberOfMushrooms;
        this.numberOfCentipedes = numberOfCentipedes;
    }

    @Override
    protected int getWidth() {
        return width;
    }

    @Override
    protected int getHeight() {
        return height;
    }

    @Override
    protected List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int x = 0; x < width; x++) {
            walls.add(new Wall(x, 0));
            walls.add(new Wall(x, height - 1));
        }

        for (int y = 1; y < height - 1; y++) {
            walls.add(new Wall(0, y));
            walls.add(new Wall(width - 1, y));
        }

        return walls;
    }

    @Override
    protected List<Mushroom> createMushrooms() {
        List<Mushroom> mushrooms = new ArrayList<>();
        for (int i = 0; i < numberOfMushrooms; i++) {
            int x = rng.nextInt(width - 2) + 1;

            // Start from y = 2 to avoid the top row just after the walls
            int y = rng.nextInt(height - 8) + 2; // Adjust the range and starting point

            mushrooms.add(new Mushroom(x, y));
        }
        return mushrooms;
    }
    @Override
    protected List<CentipedeParts> createCentipede() {
        List<CentipedeParts> centepide = new ArrayList<>();
        int count = 0;
        while (centepide.size() < numberOfCentipedes) {
            centepide.add(new CentipedeParts(3 + count, 1));
            count++;
        }
        return centepide;
    }




    @Override
    protected List<Monster> createMonsters() {
        List<Monster> monsters = new ArrayList<>();

        int minY = height / 2; // The minimum y-coordinate for spawning monsters, set to halfway down the arena
        int maxY = height - 2; // The maximum y-coordinate, adjusted to avoid spawning monsters on the bottom-most row

        while (monsters.size() < numberOfMonsters) {
            int x = rng.nextInt(width - 2) + 1;
            int y = rng.nextInt(maxY - minY + 1) + minY; // Random y-coordinate in the bottom half of the arena

            monsters.add(new Monster(x, y));
        }

        return monsters;
    }


    @Override
    protected Hero createHero() {
        return new Hero(width / 2, height-2);
    }
}