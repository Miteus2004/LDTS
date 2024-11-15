package com.aor.hero.model.game.elements;
import com.aor.hero.gui.GUI;


public class CentipedeParts extends Element {

    private GUI.Direction direction;

    public CentipedeParts(int x, int y) {
        super(x, y);
        this.direction = GUI.Direction.RIGHT; // Centipede starts moving to the right
    }

    public GUI.Direction getDirection() {
        return direction;
    }

    public void setDirection(GUI.Direction direction) {
        this.direction = direction;
    }
}


