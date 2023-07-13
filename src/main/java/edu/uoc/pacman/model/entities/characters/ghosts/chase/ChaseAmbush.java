package edu.uoc.pacman.model.entities.characters.ghosts.chase;

import edu.uoc.pacman.model.Level;
import edu.uoc.pacman.model.entities.characters.ghosts.Ghost;
import edu.uoc.pacman.model.entities.characters.pacman.Pacman;
import edu.uoc.pacman.model.utils.Direction;
import edu.uoc.pacman.model.utils.Position;

public class ChaseAmbush implements ChaseBehaviour{

    //attributes
    private static final int TILES_OFFSET = 4;

    //constructor
    public ChaseAmbush(){};

    //methods

    @Override
    public Position getChasePosition(Ghost ghost) {

        Level level = ghost.getLevel();
        Pacman pacman = level.getPacman();
        Direction direction = pacman.getDirection();
        Position pacmanPosition = pacman.getPosition();
        Position addPosition = new Position(0, 0);

        if(direction.equals(Direction.UP))
            addPosition.setY(-TILES_OFFSET);

        if(direction.equals(Direction.LEFT))
            addPosition.setX(-TILES_OFFSET);

        if(direction.equals(Direction.RIGHT))
            addPosition.setX(TILES_OFFSET);

        if(direction.equals(Direction.DOWN))
            addPosition.setY(TILES_OFFSET);

        return Position.add(pacmanPosition, addPosition);

    }
}
