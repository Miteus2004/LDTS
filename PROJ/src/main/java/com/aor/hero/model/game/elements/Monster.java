package com.aor.hero.model.game.elements;


import com.aor.hero.model.Position;

public class Monster extends Element {

    private Position position;
    private int energy;
    public Monster(int x, int y) {
        super(x, y);
        this.energy = 1;
    }



}
