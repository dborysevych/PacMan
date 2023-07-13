package edu.uoc.pacman.model.entities.characters.ghosts.chase;

import edu.uoc.pacman.model.Level;
import edu.uoc.pacman.model.entities.characters.ghosts.Ghost;
import edu.uoc.pacman.model.entities.characters.pacman.Pacman;
import edu.uoc.pacman.model.utils.Position;

public class ChaseCoward implements ChaseBehaviour{

    private static final int TILES_TO_CHASE = 8;

    public ChaseCoward() {};

    @Override
    public Position getChasePosition(Ghost ghost) {

        Level level = ghost.getLevel();
        Pacman pacman = level.getPacman();
        Position pacmanPosition = pacman.getPosition();
        double distance = pacmanPosition.distance(ghost.getPosition());

        if(distance < 8.0) {
            return ghost.getScatterPosition();
        }

        return pacmanPosition;

    }

}
