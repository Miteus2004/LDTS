package com.aor.hero.model;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position getLeft() {
        return new Position(x - 1, y);
    }

    public Position getRight() {
        return new Position(x + 1, y);
    }

    public Position getUp() {
        return new Position(x, y - 1);
    }

    public Position getDown() {
        return new Position(x, y + 1);
    }

    public List<Position> getNeighboursIncludingDiagonal() {
        List<Position> neighbours = new ArrayList<>();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue; // Skip the current position
                neighbours.add(new Position(x + dx, y + dy));
            }
        }
        return neighbours;
    }

    public List<Position> getDiagonalNeighbours() {
        List<Position> diagonalNeighbours = new ArrayList<>();

        // Top-left diagonal
        diagonalNeighbours.add(new Position(x - 1, y - 1));

        // Top-right diagonal
        diagonalNeighbours.add(new Position(x + 1, y - 1));

        // Bottom-left diagonal
        diagonalNeighbours.add(new Position(x - 1, y + 1));

        // Bottom-right diagonal
        diagonalNeighbours.add(new Position(x + 1, y + 1));

        return diagonalNeighbours;
    }

    public Position translate(Position delta) {
        return new Position(this.x + delta.getX(), this.y + delta.getY());
    }

    public Position subtract(Position other) {
        return new Position(this.x - other.getX(), this.y - other.getY());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int newY) {
        this.y = newY;
    }

    public void setX(int newX) {
        this.x = newX;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }


}
