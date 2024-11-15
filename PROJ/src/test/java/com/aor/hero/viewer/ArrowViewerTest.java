package com.aor.hero.viewer;

import com.aor.hero.gui.GUI;
import com.aor.hero.model.game.elements.Arrow;
import com.aor.hero.model.game.elements.Hero;
import com.aor.hero.viewer.game.ArrowViewer;
import com.aor.hero.viewer.game.HeroViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
public class ArrowViewerTest {
    private Arrow arrow;
    private ArrowViewer viewer;
    private GUI gui;

    @BeforeEach
    void setUp() {
        arrow = new Arrow(15, 17);
        viewer = new ArrowViewer();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    void drawElement() {
        viewer.draw(arrow, gui);
        Mockito.verify(gui, Mockito.times(1)).drawArrow(arrow.getPosition());
    }
}
