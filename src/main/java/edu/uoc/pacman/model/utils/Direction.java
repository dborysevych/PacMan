package edu.uoc.pacman.model.utils;

public enum Direction{

    //constants
    RIGHT(1, 0, 22),
    DOWN(0, 1, 20),
    LEFT(-1, 0, 21),
    UP(0, -1, 19);

    //attributes
    private final int keyCode;
    private final int x;
    private final int y;

    //constructor
    private Direction(int x, int y, int keyCode) {
        this.x = x;
        this.y = y;
        this.keyCode = keyCode;
    }

    //methods
    public int getKeyCode() {
        return keyCode;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Direction getDirectionByKeyCode(int keyCode) {

        if(keyCode == 19)
            return Direction.UP;

        if(keyCode == 22)
            return Direction.RIGHT;

        if(keyCode == 21)
            return Direction.LEFT;

        if(keyCode == 20)
            return Direction.DOWN;

        return null;
    }

    public Direction opposite() {

        if(this == DOWN)
            return Direction.UP;

        if(this == LEFT)
            return Direction.RIGHT;

        if(this == RIGHT)
            return Direction.LEFT;

        if(this == UP)
            return Direction.DOWN;

        return null;
    }

}
