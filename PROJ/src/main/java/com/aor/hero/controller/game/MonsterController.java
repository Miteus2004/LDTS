package com.aor.hero.controller.game;

import com.aor.hero.Game;
import com.aor.hero.gui.GUI;
import com.aor.hero.model.Position;
import com.aor.hero.model.game.arena.Arena;
import com.aor.hero.model.game.elements.Monster;
import java.io.IOException;
import java.util.List;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class MonsterController extends GameController {
    private long lastMovement;
    private Map<Monster, Position> lastDirections;

    public MonsterController(Arena arena) {
        super(arena);
        this.lastMovement = 0;
        this.lastDirections = new HashMap<>();
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        if (time - lastMovement > 250/game.getSpeedFactor()) {
            for (Monster monster : getModel().getMonsters()) {
                moveMonster(monster);
                checkCollisionWithHero(monster.getPosition());
            }
            this.lastMovement = time;
        }
    }

    public Position moveMonster(Monster monster) {
        Position lastDirection = lastDirections.get(monster);
        Position newPosition;

        // Change direction to diagonal
        newPosition = getNewDiagonalDirection(monster, lastDirection);

        lastDirections.put(monster, newPosition.subtract(monster.getPosition()));
        monster.setPosition(newPosition);
        return newPosition;
    }

    private Position getNewDiagonalDirection(Monster monster, Position lastDirection) {
        Position currentPosition = monster.getPosition();
        List<Position> possiblePositions = currentPosition.getDiagonalNeighbours();

        // Filter out positions that are not in the bottom half of the arena
        possiblePositions.removeIf(pos -> pos.getY() < getModel().getHeight() / 2);

        // Try to maintain the same diagonal direction if possible
        if (lastDirection != null && possiblePositions.contains(lastDirection) && Math.random() < 0.99) {
            return currentPosition.translate(lastDirection);
        }

        // Shuffle and choose a new diagonal direction
        Collections.shuffle(possiblePositions);
        for (Position position : possiblePositions) {
            if (getModel().isEmpty(position)) {
                return position;
            }
        }

        // Default to the first option if no empty position is found
        return !possiblePositions.isEmpty() ? possiblePositions.get(0) : currentPosition;
    }

    private void checkCollisionWithHero(Position position) {
        if (getModel().getHero().getPosition().equals(position))
            getModel().getHero().decreaseEnergy();
    }
}
