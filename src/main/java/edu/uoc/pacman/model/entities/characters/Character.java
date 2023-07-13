package edu.uoc.pacman.model.entities.characters;

import edu.uoc.pacman.model.Level;
import edu.uoc.pacman.model.entities.Entity;
import edu.uoc.pacman.model.utils.Direction;
import edu.uoc.pacman.model.utils.Position;
import edu.uoc.pacman.model.utils.Sprite;

public abstract class Character extends Entity implements Movable, Hitable {

    //attributes
    private boolean dead;
    private Direction direction;
    private int duration;
    private Level level;
    private Position startPosition;

    //constructor
    public Character(Position position, Direction direction, Sprite sprite, Level level) {
        super((position == null) ? new Position(0, 0) : position, true, sprite);
        this.startPosition = (position == null) ? new Position(0, 0) : position;
        this.direction = (direction == null) ? Direction.UP : direction;
        this.dead = false;
        this.level = level;
    }

    //methods
    public void reset() {
        setPosition(startPosition);
        setDead(false);
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = (direction == null) ? this.direction : direction;
    }

    protected int getDuration() {
        return duration;
    }

    protected void setDuration(int duration) {
        this.duration = duration;
    }

    private void setDead(boolean dead) {
        this.dead = dead;
    }

    public boolean isDead() {
        return dead;
    }

    private void setStartPosition(Position startPosition) {
        this.startPosition = startPosition;
    }

    protected Position getStartPosition() {
        return startPosition;
    }

    public void kill() {
        dead = true;
    }

    public void alive() {
        dead = false;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String toString() {
        return "" + getPosition().toString() + "," + getDirection().toString();
    }

}
