package com.aor.hero.controller.game;

import com.aor.hero.Game;
import com.aor.hero.gui.GUI;
import com.aor.hero.model.Position;
import com.aor.hero.model.game.arena.Arena;
import com.aor.hero.model.game.elements.Hero;
import com.aor.hero.model.game.elements.Arrow;

public class HeroController extends GameController {
    private static final long SHOOTING_COOLDOWN = 333; // Cooldown time in milliseconds (1/3 of a second)
    private long lastShotTime = -SHOOTING_COOLDOWN;; // Time when the last shot was fired


    public HeroController(Arena arena) {
        super(arena);
    }

    public void moveHeroLeft() {
        moveHero(getModel().getHero().getPosition().getLeft());
    }

    public void moveHeroRight() {
        moveHero(getModel().getHero().getPosition().getRight());
    }

    public void moveHeroUp() {
        moveHero(getModel().getHero().getPosition().getUp());
    }

    public void moveHeroDown() {
        moveHero(getModel().getHero().getPosition().getDown());
    }

    private void moveHero(Position position) {
        if (getModel().isEmpty(position)) {
            getModel().getHero().setPosition(position);

            // Check if the hero collides with a monster
            if (getModel().isMonster(position)) {
                getModel().getHero().decreaseEnergy();
            }

            // Check if the hero collides with a centipede part
            if (getModel().isCentipede(position)) {
                getModel().getHero().decreaseEnergy();
            }
        }
    }


    @Override
    public void step(Game game, GUI.ACTION action, long time) {
        if (action == GUI.ACTION.UP) moveHeroUp();
        if (action == GUI.ACTION.RIGHT) moveHeroRight();
        if (action == GUI.ACTION.DOWN) moveHeroDown();
        if (action == GUI.ACTION.LEFT) moveHeroLeft();
        if (action == GUI.ACTION.SHOOT) {
            long currentTime = System.currentTimeMillis(); // Get current time
            if (currentTime - lastShotTime >= SHOOTING_COOLDOWN) {
                shootArrow();
                lastShotTime = currentTime; // Update last shot time
            }
        }
    }

    public void shootArrow() {
        Hero hero = getModel().getHero();
        Position arrowPosition = calculateArrowStartPosition(hero.getPosition());
        Arrow newArrow = new Arrow(arrowPosition);
        getModel().addArrow(newArrow);
    }

    private Position calculateArrowStartPosition(Position heroPosition) {
        // Assume shooting upwards; adjust based on hero's direction
        return new Position(heroPosition.getX(), heroPosition.getY() );
    }

}
