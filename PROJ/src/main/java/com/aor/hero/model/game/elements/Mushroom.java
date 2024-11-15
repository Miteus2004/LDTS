package com.aor.hero.model.game.elements;

import com.aor.hero.model.Position;

public class Mushroom extends Element{
    private int energy;
    public Mushroom(int x, int y) {
        super(x,y);
        this.energy = 3;
    }


    public void decreaseEnergy() {
        energy--;
    }

    public boolean isDestroyed() {
        return energy <= 0;
    }
}
