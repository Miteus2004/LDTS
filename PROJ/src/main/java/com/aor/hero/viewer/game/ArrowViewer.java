package com.aor.hero.viewer.game;

import com.aor.hero.gui.GUI;
import com.aor.hero.model.game.elements.Arrow;

public class ArrowViewer implements ElementViewer<Arrow> {
    @Override
    public void draw(Arrow arrow, GUI gui) {
        gui.drawArrow(arrow.getPosition());
    }
}

