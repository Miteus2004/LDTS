package com.aor.hero.model.game.elements;

import com.aor.hero.model.Position;

public class Element {
    private Position position;

    public Element(int x, int y) {
        this.position = new Position(x, y);
    }
    public Element(Position position) {
        this.position = new Position(position.getX(), position.getY());
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Element() {
        // Default constructor
        this.position = new Position(0, 0);
    }

}
