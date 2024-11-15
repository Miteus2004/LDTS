package com.aor.hero.controller.game;
import com.aor.hero.Game;
import com.aor.hero.gui.GUI;
import com.aor.hero.model.Position;
import com.aor.hero.model.game.arena.Arena;
import com.aor.hero.model.game.elements.CentipedeParts;

import java.io.IOException;

public class CentipedeController extends GameController {
    private static final long MOVEMENT_INTERVAL = 250;
    private long lastMoveTime;

    public CentipedeController(Arena arena) {
        super(arena);
        this.lastMoveTime = 0;
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        if (time - lastMoveTime > MOVEMENT_INTERVAL/game.getSpeedFactor()) {
            for (CentipedeParts centipedePart : getModel().getCentipede()) {
                moveCentipede(centipedePart);
                checkCollisionWithHero(centipedePart.getPosition()); // Check for collisions with hero
                if (isAtBottom(centipedePart.getPosition())) {
                    getModel().getHero().setEnergy(0);
                    return;
                }
            }
            this.lastMoveTime = time;
        }
    }

    public Position moveCentipede(CentipedeParts centipedePart) {
        Position currentPosition = centipedePart.getPosition();
        Position newPosition = determineBestMove(centipedePart, currentPosition);
        updateCentipedeDirection(centipedePart, currentPosition, newPosition);
        return newPosition;
    }

    private Position determineBestMove(CentipedeParts centipedePart, Position currentPosition) {
        Position nextPosition = new Position(currentPosition.getX(), currentPosition.getY());
        switch (centipedePart.getDirection()) {
            case RIGHT:
                nextPosition = new Position(currentPosition.getX() + 1, currentPosition.getY());
                break;
            case LEFT:
                nextPosition = new Position(currentPosition.getX() - 1, currentPosition.getY());
                break;
            case DOWN:
                nextPosition = new Position(currentPosition.getX(), currentPosition.getY() + 1);
                break;
        }
        if (isAtWall(nextPosition) || getModel().checkMushroom(nextPosition)) {
            nextPosition = new Position(currentPosition.getX(), currentPosition.getY() + 1);
            centipedePart.setDirection(centipedePart.getDirection() == GUI.Direction.RIGHT ? GUI.Direction.LEFT : GUI.Direction.RIGHT);
        }

        centipedePart.setPosition(nextPosition); 
        return nextPosition;
    }

    private void updateCentipedeDirection(CentipedeParts centipedePart, Position currentPosition, Position newPosition) {
        if (newPosition.getX() > currentPosition.getX()) {
            centipedePart.setDirection(GUI.Direction.RIGHT);
        } else if (newPosition.getX() < currentPosition.getX()) {
            centipedePart.setDirection(GUI.Direction.LEFT);
        }
    }

    private boolean isAtBottom(Position position) {
        return position.getY() >= getModel().getHeight() - 2;
    }

    private boolean isAtWall(Position position) {
        return position.getX() <= 0 || position.getX() >= getModel().getWidth() - 1;
    }

    private void checkCollisionWithHero(Position position) {
        if (getModel().getHero().getPosition().equals(position))
            getModel().getHero().decreaseEnergy();
    }
}
