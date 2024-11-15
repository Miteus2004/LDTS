package com.aor.hero.states;

import com.aor.hero.controller.Controller;
import com.aor.hero.controller.game.GameOverController;
import com.aor.hero.gui.GUI;
import com.aor.hero.model.game.arena.Arena;
import com.aor.hero.viewer.Viewer;
import com.aor.hero.viewer.game.GameOverViewer;

public class GameOverState extends State<Arena> {
    public GameOverState(Arena arena) {
        super(arena);
    }

    @Override
    protected Viewer<Arena> getViewer() {
        return new GameOverViewer(getModel());
    }


    @Override
    protected Controller<Arena> getController() {
        // You might want a simple controller that just waits for a keypress to return to the menu
        return new GameOverController(getModel());
    }
}
