package com.aor.hero.viewer.game;

import com.aor.hero.gui.GUI;
import com.aor.hero.model.game.elements.Mushroom;

public class MushroomViewer implements ElementViewer<Mushroom> {
    @Override
    public void draw(Mushroom mushroom, GUI gui) {
        gui.drawMushroom(mushroom.getPosition());
    }
}
