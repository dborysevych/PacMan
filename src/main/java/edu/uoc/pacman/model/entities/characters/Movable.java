package edu.uoc.pacman.model.entities.characters;

import edu.uoc.pacman.model.utils.Direction;

public interface Movable {

    public abstract void move();

    public abstract void setDirection(Direction direction);

}
