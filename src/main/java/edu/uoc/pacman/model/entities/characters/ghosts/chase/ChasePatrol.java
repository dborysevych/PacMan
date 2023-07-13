package edu.uoc.pacman.model.entities.characters.ghosts.chase;

import edu.uoc.pacman.model.Level;
import edu.uoc.pacman.model.entities.characters.ghosts.Blinky;
import edu.uoc.pacman.model.entities.characters.ghosts.Ghost;
import edu.uoc.pacman.model.entities.characters.pacman.Pacman;
import edu.uoc.pacman.model.utils.Direction;
import edu.uoc.pacman.model.utils.Position;

public class ChasePatrol implements ChaseBehaviour{

    private static final int TILES_OFFSET = 2;
    private static final int VECTOR_INCREASE = 2;

    public ChasePatrol() {};

    @Override
    public Position getChasePosition(Ghost ghost) {

        Level level = ghost.getLevel();
        Pacman pacman = level.getPacman();
        Direction direction = pacman.getDirection();
        Position pacmanPosition = pacman.getPosition();
        Position targetBlinkyPosition = new Position(0, 0);
        Blinky firstBlinky = level.getBlinky();
        Position firstBlinkyPosition = firstBlinky.getPosition();
        Position blinkyPositionToRest = new Position(-firstBlinkyPosition.getX(), -firstBlinkyPosition.getY());

        if(direction.equals(Direction.UP))
            targetBlinkyPosition.setY(-TILES_OFFSET);

        if(direction.equals(Direction.LEFT))
            targetBlinkyPosition.setX(-TILES_OFFSET);

        if(direction.equals(Direction.RIGHT))
            targetBlinkyPosition.setX(TILES_OFFSET);

        if(direction.equals(Direction.DOWN))
            targetBlinkyPosition.setY(TILES_OFFSET);

        targetBlinkyPosition = Position.add(pacmanPosition, targetBlinkyPosition);


        Position retPosition = Position.add(targetBlinkyPosition, blinkyPositionToRest);
        retPosition.setX(retPosition.getX() * VECTOR_INCREASE);
        retPosition.setY(retPosition.getY() * VECTOR_INCREASE);


        return retPosition;

    }

}
