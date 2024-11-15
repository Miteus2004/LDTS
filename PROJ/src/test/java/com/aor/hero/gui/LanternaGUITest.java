package com.aor.hero.gui;

import com.aor.hero.model.Position;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class LanternaGUITest {
    private Screen screen;
    private LanternaGUI gui;
    private TextGraphics tg;

    @BeforeEach
    void setUp() {
        screen = Mockito.mock(Screen.class);
        tg = Mockito.mock(TextGraphics.class);

        Mockito.when(screen.newTextGraphics()).thenReturn(tg);

        gui = new LanternaGUI(screen);
    }


    @Test
    void drawHero() {
        gui.drawHero(new Position(15, 17));

        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(new TextColor.RGB(128, 0, 128));
        Mockito.verify(tg, Mockito.times(1)).putString(15, 18, "#");
    }

    @Test
    void drawWall() {
        gui.drawWall(new Position(1, 1));

        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(new TextColor.RGB(51, 51, 255));
        Mockito.verify(tg, Mockito.times(1)).putString(1, 2, " ");
    }

    @Test
    void drawMonster() {
        gui.drawMonster(new Position(15, 17));

        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(new TextColor.RGB(90, 0, 90));
        Mockito.verify(tg, Mockito.times(1)).putString(15, 18, "@");
    }

    @Test
    void drawMushroom() {
        gui.drawMushroom(new Position(15, 17));

        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(new TextColor.RGB(218, 112, 214));
        Mockito.verify(tg, Mockito.times(1)).putString(15, 18, "*");
    }

    @Test
    void drawArrow() {
        gui.drawArrow(new Position(15, 17));

        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(new TextColor.RGB(0, 191, 255));
        Mockito.verify(tg, Mockito.times(1)).putString(15, 18, "^");
    }

    @Test
    void drawCentipedeSegment() {
        gui.drawCentipedeSegment(new Position(15, 17));

        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(new TextColor.RGB(147, 112, 219));
        Mockito.verify(tg, Mockito.times(1)).putString(15, 18, "$");
    }

    @Test
    void drawText() {
        gui.drawText(new Position(1, 1), "Hello World", "#336699");

        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(new TextColor.RGB(51, 102, 153));
        Mockito.verify(tg, Mockito.times(1)).putString(1, 1, "Hello World");
    }
}