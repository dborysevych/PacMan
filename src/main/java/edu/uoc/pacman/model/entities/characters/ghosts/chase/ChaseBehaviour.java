package edu.uoc.pacman.model.entities.characters.ghosts.chase;

import edu.uoc.pacman.model.entities.characters.ghosts.Ghost;
import edu.uoc.pacman.model.utils.Position;

public interface ChaseBehaviour {

    public abstract Position getChasePosition(Ghost ghost);

}
