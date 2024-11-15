package com.aor.hero.viewer;

import com.aor.hero.gui.GUI;
import com.aor.hero.model.game.elements.Hero;
import com.aor.hero.model.game.elements.Monster;
import com.aor.hero.viewer.game.HeroViewer;
import com.aor.hero.viewer.game.MonsterViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MonsterViewerTest {
    private Monster monster;
    private MonsterViewer viewer;
    private GUI gui;

    @BeforeEach
    void setUp() {
        monster = new Monster(15, 15);
        viewer = new MonsterViewer();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    void drawElement() {
        viewer.draw(monster, gui);
        Mockito.verify(gui, Mockito.times(1)).drawMonster(monster.getPosition());
    }
}
