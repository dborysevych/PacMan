package edu.uoc.pacman.model.utils;

public enum Sprite {

    //constants
    PACMAN_RIGHT('<', "images/pac_right.png"),
    PACMAN_LEFT('>', "images/pac_left.png"),
    PACMAN_UP('^', "images/pac_up.png"),
    PACMAN_DOWN('V', "images/pac_down.png"),
    BLINKY('B', "images/blinky.png"),
    PINKY('P', "images/pinky.png"),
    INKY('I', "images/inky.png"),
    CLYDE('C', "images/clyde.png"),
    DOT('.', "images/dot.png"),
    ENERGIZER('0', "images/energizer.png"),
    WALL('#', "images/wall.png"),
    PATH(' ', "images/path.png"),
    LIFE('L', "images/life.png");

    //attributes
    private final char symbol;
    private final String imageSrc;

    //constructor
    Sprite(char symbol, String imageSrc) {
        this.symbol = symbol;
        this.imageSrc = imageSrc;
    }

    //methods
    public char getSymbol() {
        return symbol;
    }

    public String getImageSrc() {
        return imageSrc;
    }
}
