package com.aor.hero.model.game.arena;

import com.aor.hero.model.Position;
import com.aor.hero.model.game.elements.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Arena {
    private final int width;
    private final int height;

    private Hero hero;
    private List<Wall> walls;
    private List<Monster> monsters;
    private List<Mushroom> mushrooms;
    private List<CentipedeParts> centipede;
    private List<Arrow> arrows;

    private int score;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        this.score=0;
    }

    // Existing getters and setters

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {this.hero =hero;}

    public List<Arrow> getArrows() {
        return arrows;
    }

    public void setArrows(List<Arrow> arrows) {
        this.arrows = arrows;
    }

    public List<Mushroom> getMushrooms() {
        return mushrooms;
    }

    public void setMushrooms(List<Mushroom> mushrooms) {
        this.mushrooms = mushrooms;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(List<Monster> monsters) {this.monsters = monsters;}

    public List<Wall> getWalls() {
        return walls;
    }

    public void setWalls(List<Wall> walls) {this.walls = walls;}

    public List<CentipedeParts> getCentipede() {return (List<CentipedeParts>) centipede;}

    public void setCentipede(List<CentipedeParts>centipede) { this.centipede=centipede;}

    public void increaseScore(int points) {
        this.score += points;
    }

    public int getScore() {
        return score;
    }

    public void updateScore(int newScore) {
        this.score = newScore;
    }

    public boolean isEmpty(Position position) {
        for (Wall wall : walls)
            if (wall.getPosition().equals(position))
                return false;
        for (Mushroom mushroom : mushrooms)
            if (mushroom.getPosition().equals(position))
                return false;
        for (Monster monster : monsters){
            if (monster.getPosition().equals(position))
                return true;
        }
        for (CentipedeParts centipede : centipede) {
            if (centipede.getPosition().equals(position)) {
                return true;
            }
        }
        return canHeroMove(position);
    }

    public boolean canHeroMove(Position position){
        // Verifica se a posição está dentro dos limites da arena
        if (position.getX() < 0 || position.getX() >= width || position.getY() < 0 || position.getY() >= height) {
            return false;
        }

        int minY = height - 6; // substitua pelo valor mínimo desejado
        return position.getY() >= minY;
    }

    public boolean isMonster(Position position) {
        for (Monster monster : monsters)
            if (monster.getPosition().equals(position))
                return true;
        return false;
    }

    public void addArrow(Arrow arrow) {
        arrows.add(arrow);
    }

    public void removeArrow(Arrow arrow) {
        arrows.remove(arrow);
    }

    public boolean hitWall(Position position) {
        for (Wall wall : walls) {
            if (wall.getPosition().equals(position)) {
                return true; // Indicates a wall was hit
            }
        }
        return false; // No wall was hit
    }

    public boolean hitMonster(Position position) {
        Iterator<Monster> iterator = monsters.iterator();
        while (iterator.hasNext()) {
            Monster monster = iterator.next();
            if (monster.getPosition().equals(position)) {
                iterator.remove();  // Use iterator's remove method
                increaseScore(10);  // Assuming each monster is worth 10 points
                return true;
            }
        }
        return false;
    }


    public boolean hitCentipede(Position position) {
        Iterator<CentipedeParts> iterator = centipede.iterator();
        while (iterator.hasNext()) {
            CentipedeParts part = iterator.next();
            if (part.getPosition().equals(position)) {
                iterator.remove();  // Use iterator's remove method
                increaseScore(5);   // Assuming each centipede part is worth 5 points
                return true;
            }
        }
        return false;
    }


    public boolean hitMushroom(Position position) {
        for (Mushroom mushroom : mushrooms) {
            if (mushroom.getPosition().equals(position)) {
                mushroom.decreaseEnergy();
                if (mushroom.isDestroyed()) {
                    mushrooms.remove(mushroom); // Remove the mushroom if its energy reaches 0
                }
                return true; // Indicates a mushroom was hit
            }
        }
        return false; // No mushroom was hit
    }

    public boolean checkMushroom(Position position) {
        for (Mushroom mushroom : mushrooms) {
            if (mushroom.getPosition().equals(position)) {
                return true; // Indicates a mushroom was hit
            }
        }
        return false; // No mushroom was hit
    }

    public void addMushroom(Position position) {
        Mushroom newMushroom = new Mushroom(position.getX(), position.getY());
        mushrooms.add(newMushroom);
    }

    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }

    public boolean isCentipede(Position position) {
        for (CentipedeParts part : centipede) {
            if (part.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    public boolean areAllMonstersAndCentipedesDefeated() {
        return monsters.isEmpty() && centipede.isEmpty();
    }

    public void respawnMonstersAndCentipedes() {
        int numberOfMonsters=1, numberOfMushrooms=30, numberOfCentipedes=7;
        monsters = new RandomArenaBuilder(width, height, numberOfMonsters, numberOfMushrooms, numberOfCentipedes).createMonsters();
        centipede = new RandomArenaBuilder(width, height, numberOfMonsters, numberOfMushrooms, numberOfCentipedes).createCentipede();
    }

}