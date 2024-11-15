package com.aor.hero.viewer.game;

import com.aor.hero.gui.GUI;
import com.aor.hero.model.game.arena.Arena;
import com.aor.hero.model.game.elements.Element;
import com.aor.hero.viewer.Viewer;

import java.util.List;

public class GameViewer extends Viewer<Arena> {
    private static final String TEXT_COLOR = "#7B68EE"; // The text color to be used

    public GameViewer(Arena arena) {
        super(arena);
    }

    @Override
    public void drawElements(GUI gui) {
        drawElements(gui, getModel().getWalls(), new WallViewer());
        drawElements(gui, getModel().getMonsters(), new MonsterViewer());
        drawElements(gui, getModel().getMushrooms(), new MushroomViewer());
        drawElements(gui, getModel().getCentipede(), new CentipedeSegmentViewer());
        drawElements(gui, getModel().getArrows(), new ArrowViewer());
        drawElement(gui, getModel().getHero(), new HeroViewer());

        drawLifeDisplay(gui);
        drawScoreDisplay(gui);
    }

    private void drawLifeDisplay(GUI gui) {
        int heroEnergy = getModel().getHero().getEnergy();
        String lifeLabel = "LIFE: ";
        for (int i = 0; i < lifeLabel.length(); i++) {
            gui.drawCharacter(i, -1, lifeLabel.charAt(i), TEXT_COLOR); // Adjusted y position
        }

        int xPosition = lifeLabel.length(); // Position for life points
        for (int i = 0; i < heroEnergy; i++) {
            gui.drawCharacter(xPosition + i, -1, 'O', TEXT_COLOR); // Adjusted y position
        }
    }

    private void drawScoreDisplay(GUI gui) {
        String scoreText = "Score: " + getModel().getScore();
        int screenWidth = gui.getScreenWidth();
        int scoreXPosition = screenWidth - scoreText.length();

        for (int i = 0; i < scoreText.length(); i++) {
            gui.drawCharacter(scoreXPosition + i, -1, scoreText.charAt(i), TEXT_COLOR); // Adjusted y position
        }
    }

    private <T extends Element> void drawElements(GUI gui, List<T> elements, ElementViewer<T> viewer) {
        if (elements != null) {
            for (T element : elements) {
                drawElement(gui, element, viewer);
            }
        }
    }


    private <T extends Element> void drawElement(GUI gui, T element, ElementViewer<T> viewer) {
        viewer.draw(element, gui);
    }
}
