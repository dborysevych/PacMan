package edu.uoc.pacman.model.utils;

import java.util.Objects;

public class Position {


    //attributes
    private int x;
    private int y;

    //constructor
    public Position(int x, int y) {
        setX(x);
        setY(y);
    }


    //methods
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double distance(Position other) {

        if(other == null)
            return 0.0;

        return Math.sqrt( Math.pow(this.x - other.getX(), 2) + Math.pow(this.y - other.y, 2) );

    }

    public static Position add(Position p1,  Position p2) throws NullPointerException {

        if(p1 == null || p2 == null)
            throw new NullPointerException();

        return new Position(p1.getX() + p2.getX(), p1.getY() + p2.getY());
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Position && ((Position) obj).getX() == this.x && ((Position) obj).getY() == this.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public  String toString() {
        return "" + getX() + "," + getY();
    }


}
