package com.aor.hero.viewer;

import com.aor.hero.gui.GUI;
import com.aor.hero.model.game.elements.Hero;
import com.aor.hero.model.game.elements.Mushroom;
import com.aor.hero.viewer.game.HeroViewer;
import com.aor.hero.viewer.game.MushroomViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MushroomViewerTest {
    private Mushroom mushroom;
    private MushroomViewer viewer;
    private GUI gui;

    @BeforeEach
    void setUp() {
        mushroom = new Mushroom(4, 7);
        viewer = new MushroomViewer();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    void drawElement() {
        viewer.draw(mushroom, gui);
        Mockito.verify(gui, Mockito.times(1)).drawMushroom(mushroom.getPosition());
    }
}
