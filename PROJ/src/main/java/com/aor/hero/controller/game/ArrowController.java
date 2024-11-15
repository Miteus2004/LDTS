package com.aor.hero.controller.game;

import com.aor.hero.Game;
import com.aor.hero.gui.GUI;
import com.aor.hero.model.Position;
import com.aor.hero.model.game.arena.Arena;
import com.aor.hero.model.game.elements.Arrow;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class ArrowController extends GameController {
    public ArrowController(Arena model) {
        super(model);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        List<Arrow> arrows = getModel().getArrows();
        Iterator<Arrow> arrowIterator = arrows.iterator();

        while (arrowIterator.hasNext()) {
            Arrow arrow = arrowIterator.next();
            move(arrow);
            if (checkCollisionAndDespawn(arrow, arrowIterator)) {
                continue;
            }
        }
    }

    private void move(Arrow arrow) {
        // Assuming arrows move upwards. Update this logic as needed.
        Position newPosition = new Position(arrow.getPosition().getX(), arrow.getPosition().getY() - 1);
        arrow.setPosition(newPosition);
    }

    private boolean checkCollisionAndDespawn(Arrow arrow, Iterator<Arrow> arrowIterator) {
        Position arrowPosition = arrow.getPosition();

        // Check collision with walls, monsters, or mushrooms
        if (getModel().hitWall(arrowPosition) ||
                getModel().hitMonster(arrowPosition) ||
                getModel().hitMushroom(arrowPosition)) {
            arrowIterator.remove(); // Remove the arrow on collision
            return true; // Indicates a collision occurred
        } else if (getModel().hitCentipede(arrowPosition)){
            arrowIterator.remove();
            getModel().addMushroom(arrowPosition);
        }
        return false;
    }

}
