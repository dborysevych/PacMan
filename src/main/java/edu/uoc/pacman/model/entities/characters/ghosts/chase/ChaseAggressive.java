package edu.uoc.pacman.model.entities.characters.ghosts.chase;

import edu.uoc.pacman.model.Level;
import edu.uoc.pacman.model.entities.characters.ghosts.Ghost;
import edu.uoc.pacman.model.entities.characters.pacman.Pacman;
import edu.uoc.pacman.model.utils.Position;

public class ChaseAggressive implements ChaseBehaviour{

    //constructor
    public ChaseAggressive(){};

    //methods
    @Override
    public Position getChasePosition(Ghost ghost) {
        Level level = ghost.getLevel();
        Pacman pacman = level.getPacman();
        return pacman.getPosition();
    }

}
