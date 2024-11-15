package com.aor.hero.controller.game;

import com.aor.hero.Game;
import com.aor.hero.controller.Controller;
import com.aor.hero.gui.GUI;
import com.aor.hero.model.game.arena.Arena;
import com.aor.hero.model.menu.Menu;
import com.aor.hero.states.MenuState;

public class GameOverController extends Controller<Arena> {
    public GameOverController(Arena arena) {
        super(arena);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) {
        // Wait for a keypress to return to the main menu
        if (action == GUI.ACTION.SELECT) {
            game.setState(new MenuState(new Menu()));
        }
    }
}
