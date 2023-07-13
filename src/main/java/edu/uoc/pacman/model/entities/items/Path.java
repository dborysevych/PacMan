package edu.uoc.pacman.model.entities.items;


import edu.uoc.pacman.model.utils.Position;
import edu.uoc.pacman.model.utils.Sprite;

public class Path extends MapItem{

    //constructors
    public Path(Position position) {
        super(position, true, Sprite.PATH);
    }

}
