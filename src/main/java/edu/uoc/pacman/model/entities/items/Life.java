package edu.uoc.pacman.model.entities.items;


import edu.uoc.pacman.model.utils.Position;
import edu.uoc.pacman.model.utils.Sprite;

public class Life extends MapItem implements Pickable{

    //attributes
    private boolean picked;

    //constructors
    public Life(Position position) {
        super(position, true, Sprite.LIFE);
    }

    //methods
    public boolean isPicked() {
        return this.picked;
    }

    @Override
    public void setPicked(boolean picked) {
        this.picked = picked;
    }


}
