package com.aor.hero.viewer.game;

import com.aor.hero.gui.GUI;
import com.aor.hero.model.game.arena.Arena;
import com.aor.hero.viewer.Viewer;

public class GameOverViewer extends Viewer<Arena> {

    private static final String TEXT_COLOR = "#7B68EE";

    public GameOverViewer(Arena model) {
        super(model);
    }

    @Override
    protected void drawElements(GUI gui) {
        String gameOverText = "Game Over";
        int score = getModel().getScore();
        String scoreText = "Score: " + score;

        int centerX = gui.getScreenWidth() / 2;
        int centerY = gui.getScreenHeight() / 2;

        // Calculate positions for centered text
        int gameOverTextX = centerX - gameOverText.length() / 2;
        int scoreTextX = centerX - scoreText.length() / 2;

        // Draw "Game Over" text
        drawString(gui, gameOverTextX, centerY - 1, gameOverText, "#9932CC");
        // Draw score text
        drawString(gui, scoreTextX, centerY, scoreText, TEXT_COLOR);
    }

    private void drawString(GUI gui, int x, int y, String text, String foregroundColor) {
        for (int i = 0; i < text.length(); i++) {
            gui.drawCharacter(x + i, y - 1, text.charAt(i), foregroundColor);
        }
    }
}
