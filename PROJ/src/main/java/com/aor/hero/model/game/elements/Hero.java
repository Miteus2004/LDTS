package com.aor.hero.model.game.elements;

public class Hero extends Element {
    private int energy;

    public Hero(int x, int y) {
        super(x, y);
        this.energy = 3;
    }

    public void decreaseEnergy() {
        this.energy--;
    }

    public int getEnergy() {
        return energy;
    }

    public void increaseEnergy() {
        if(this.energy<4)this.energy ++;
    }

    public void setEnergy(int i) {
        this.energy=i;
    }
}