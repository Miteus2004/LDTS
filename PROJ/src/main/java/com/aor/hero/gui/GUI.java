package com.aor.hero.gui;

import com.aor.hero.model.Position;

import java.io.IOException;

public interface GUI {
    ACTION getNextAction() throws IOException;

    void drawHero(Position position);
    void drawWall(Position position);
    void drawMonster(Position position);
    void drawMushroom(Position position);
    void drawArrow(Position position);
    void drawCentipedeSegment(Position position);
    void drawCharacter(int x, int y, char c, String color);
    void drawText(Position position, String text, String color);

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    int getScreenWidth(); // Getter for screen width

    int getScreenHeight(); // Getter for screen height
    public enum Direction {
        LEFT,
        RIGHT,
        DOWN // Assuming the centipede only needs to move in these three directions
    }
    enum ACTION {UP, RIGHT, DOWN, LEFT, NONE, QUIT, SELECT, SHOOT}
}
