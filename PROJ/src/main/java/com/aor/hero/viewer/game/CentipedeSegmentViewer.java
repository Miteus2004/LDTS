package com.aor.hero.viewer.game;

import com.aor.hero.gui.GUI;
import com.aor.hero.model.game.elements.CentipedeParts;

public class CentipedeSegmentViewer implements ElementViewer<CentipedeParts> {
    @Override
    public void draw(CentipedeParts centipedeSegment, GUI gui) {
        gui.drawCentipedeSegment(centipedeSegment.getPosition());
    }
}
