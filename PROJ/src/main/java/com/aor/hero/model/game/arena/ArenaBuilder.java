package com.aor.hero.model.game.arena;

import com.aor.hero.model.game.elements.*;

import java.util.ArrayList;
import java.util.List;

public abstract class ArenaBuilder {
    public Arena createArena() {
        Arena arena = new Arena(getWidth(), getHeight());

        arena.setHero(createHero());
        arena.setMonsters(createMonsters());
        arena.setWalls(createWalls());
        arena.setMushrooms(createMushrooms());
        arena.setArrows(new ArrayList<Arrow>());
        arena.setCentipede(createCentipede());

        return arena;
    }

    protected abstract int getWidth();

    protected abstract int getHeight();

    protected abstract List<Wall> createWalls();

    protected abstract List<Monster> createMonsters();

    protected abstract Hero createHero();

    protected abstract List<Mushroom> createMushrooms();

    protected abstract List<CentipedeParts> createCentipede();
}
