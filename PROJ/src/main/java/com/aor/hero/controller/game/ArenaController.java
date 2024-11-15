package com.aor.hero.controller.game;

import com.aor.hero.Game;
import com.aor.hero.gui.GUI;
import com.aor.hero.model.game.arena.Arena;
import com.aor.hero.model.menu.Menu;
import com.aor.hero.states.MenuState;
import java.io.IOException;

public class ArenaController extends GameController {
    private final HeroController heroController;
    private final MonsterController monsterController;
    private final ArrowController arrowController;
    private final CentipedeController centipedeController;

    public ArenaController(Arena arena) {
        super(arena);

        this.heroController = new HeroController(arena);
        this.monsterController = new MonsterController(arena);
        this.arrowController = new ArrowController(arena);
        this.centipedeController = new CentipedeController(arena);
    }



    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        if (action == GUI.ACTION.QUIT || getModel().getHero().getEnergy() == 0) {
            game.setState(new MenuState(new Menu()));
        } else {
            heroController.step(game, action, time);
            monsterController.step(game, action, time);
            arrowController.step(game, action, time);
            centipedeController.step(game, action, time);
        }
    }
}
