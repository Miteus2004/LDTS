package com.aor.hero.viewer;

import com.aor.hero.gui.GUI;
import com.aor.hero.model.game.elements.CentipedeParts;
import com.aor.hero.model.game.elements.Hero;
import com.aor.hero.viewer.game.CentipedeSegmentViewer;
import com.aor.hero.viewer.game.HeroViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
public class CentipedeSegmentViewerTest {
    private CentipedeParts centipedeParts;
    private CentipedeSegmentViewer viewer;
    private GUI gui;

    @BeforeEach
    void setUp() {
        centipedeParts = new CentipedeParts(15, 17);
        viewer = new CentipedeSegmentViewer();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    void drawElement() {
        viewer.draw(centipedeParts, gui);
        Mockito.verify(gui, Mockito.times(1)).drawCentipedeSegment(centipedeParts.getPosition());
    }
}
