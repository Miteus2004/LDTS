package com.aor.hero;

import com.aor.hero.gui.LanternaGUI;
import com.aor.hero.model.menu.Menu;
import com.aor.hero.states.GameOverState;
import com.aor.hero.states.GameState;
import com.aor.hero.states.MenuState;
import com.aor.hero.states.State;
import com.aor.hero.model.game.arena.Arena;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    private final LanternaGUI gui;
    private double speedFactor = 1.0;

    private static final double INCREASE = 1.1;
    private State state;

    public Game() throws FontFormatException, IOException, URISyntaxException {
        this.gui = new LanternaGUI(30, 20);
        this.state = new MenuState(new Menu());
    }

    public static void main(String[] args) throws IOException, FontFormatException, URISyntaxException {
        new Game().start();
    }

    public void setState(State state) {
        this.state = state;
    }

    private void start() throws IOException {
        int FPS = 40;
        int frameTime = 1000 / FPS;

        while (this.state != null) {
            long startTime = System.currentTimeMillis();

            state.step(this, gui, startTime);

            checkGameOverCondition();

            if (this.state instanceof GameState) {
                GameState gameState = (GameState) state;
                if (gameState.getModel().areAllMonstersAndCentipedesDefeated()) {
                    respawn(gameState.getModel());
                    speedFactor *= INCREASE; // Increase speed by 50%
                }
            }

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                // Properly handle the InterruptedException
                Thread.currentThread().interrupt();
            }
        }

        gui.close();
    }

    private void checkGameOverCondition() {
        if (state instanceof GameState) {
            GameState gameState = (GameState) state;
            if (gameState.getModel().getHero().getEnergy() <= 0) {
                int finalScore = gameState.getModel().getScore();
                setState(new GameOverState(gameState.getModel()));
            }
        }
    }
    public void respawn(Arena model) {
        if (state instanceof GameState) {
            GameState gameState = (GameState) state;
            Arena arena = gameState.getModel();

            if (arena.areAllMonstersAndCentipedesDefeated()) {
                arena.respawnMonstersAndCentipedes();
                speedFactor *= INCREASE; // Increase speed for the new level
                arena.getHero().increaseEnergy(); // Increase hero's energy
            }
        }
    }

    public double getSpeedFactor() {
        return speedFactor;
    }
}
