package edu.uoc.pacman.model.entities.characters.ghosts;

import edu.uoc.pacman.model.Level;
import edu.uoc.pacman.model.entities.Scorable;
import edu.uoc.pacman.model.entities.characters.Character;
import edu.uoc.pacman.model.entities.characters.ghosts.chase.ChaseBehaviour;
import edu.uoc.pacman.model.entities.characters.pacman.Pacman;
import edu.uoc.pacman.model.entities.characters.pacman.State;
import edu.uoc.pacman.model.entities.items.MapItem;
import edu.uoc.pacman.model.utils.Direction;
import edu.uoc.pacman.model.utils.Position;
import edu.uoc.pacman.model.utils.Sprite;

import java.util.Iterator;

public abstract class Ghost extends Character implements Scorable {

    //attributes
    private Behaviour behaviour;
    protected ChaseBehaviour chaseBehaviour;
    private Position scatterPosition;

    protected Ghost(Position position, Position scatterPosition, Direction direction,
                    Behaviour behaviour, Sprite sprite, Level level) {
        super(position, direction, sprite, level);
        setScatterPosition(scatterPosition);
        setBehaviour(behaviour);
    }

    public Behaviour getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(Behaviour behaviour) {
        if(behaviour != null) {
            this.behaviour = behaviour;
            setDuration(getBehaviour().getDuration());
        }
    }

    private void nextBehaviour() {
        setDuration(getDuration() - 1);

        if(getDuration() <= 0){

            if(getBehaviour() == Behaviour.CHASE)
                setBehaviour(Behaviour.SCATTER);
            else
                setBehaviour(Behaviour.CHASE);

        }

    }

    @Override
    public void reset() {
        super.reset();
        setBehaviour(Behaviour.INACTIVE);
        setDirection(Direction.UP);
    }

    @Override
    public boolean equals(Object o) {
        return getClass() == o.getClass() && isDead() == ((Ghost) o).isDead() &&
                getBehaviour() == ((Ghost) o).getBehaviour() &&
                getDirection() == ((Ghost) o).getDirection() &&
                getPosition().equals(((Ghost) o).getPosition()) &&
                getDuration() == ((Ghost) o).getDuration();
    }

    @Override
    public String toString() {
        return getPosition().toString() + "," + getDirection().name() + "," + getBehaviour().name() + ":" + getDuration();
    }

    public Position getScatterPosition() {
        return scatterPosition;
    }

    private void setScatterPosition(Position scatterPosition) {
        this.scatterPosition = scatterPosition;
    }

    private Position getTargetPosition() {

        if(behaviour == Behaviour.CHASE)
            return chaseBehaviour.getChasePosition(this);

        if(behaviour == Behaviour.SCATTER || behaviour == Behaviour.FRIGHTENED)
            return getScatterPosition();

        return null;

    }

    @Override
    public void move() {

        nextBehaviour();

        Position targetPosition = getTargetPosition();

        if(targetPosition == null)
            return;



        Position newPosition = new Position(0, 0);
        Level level = getLevel();
        MapItem item;
        Position possiblePosition;
        Direction possibleDirection = Direction.RIGHT;
        double distance;
        double min = Double.MAX_VALUE;

        //commments
        System.out.println(level.getPacman().getPosition().toString() + " " + level.getPacman().getDirection().name());
        System.out.println(getPosition().toString() + " " + getDirection().name());

        possiblePosition = new Position(getPosition().getX() + 1, getPosition().getY());
        item = level.getMapItem(possiblePosition);
        distance = item.isPathable() && Direction.RIGHT != getDirection().opposite() ? Math.abs(possiblePosition.distance(targetPosition)) : -1.0;
        if(distance != -1.0 && distance < min) {
            min = distance;
            newPosition.setX(possiblePosition.getX());
            newPosition.setY(possiblePosition.getY());
            possibleDirection = Direction.RIGHT;
        }


        possiblePosition.setX(getPosition().getX());
        possiblePosition.setY(getPosition().getY() + 1);
        item = level.getMapItem(possiblePosition);
        distance = item.isPathable() && Direction.DOWN != getDirection().opposite() ? Math.abs(possiblePosition.distance(targetPosition)) : -1.0;
        if(distance != -1.0 && distance <= min) {
            min = distance;
            newPosition.setX(possiblePosition.getX());
            newPosition.setY(possiblePosition.getY());
            possibleDirection = Direction.DOWN;
        }

        possiblePosition.setX(getPosition().getX() - 1);
        possiblePosition.setY(getPosition().getY());
        item = level.getMapItem(possiblePosition);
        distance =  item.isPathable() && Direction.LEFT != getDirection().opposite() ? Math.abs(possiblePosition.distance(targetPosition)) : -1.0;
        if(distance != -1.0 && distance <= min) {
            min = distance;
            newPosition.setX(possiblePosition.getX());
            newPosition.setY(possiblePosition.getY());
            possibleDirection = Direction.LEFT;
        }

        possiblePosition.setX(getPosition().getX());
        possiblePosition.setY(getPosition().getY() - 1);
        item = level.getMapItem(possiblePosition);
        distance = item.isPathable() && Direction.UP != getDirection().opposite() ? possiblePosition.distance(targetPosition) : -1.0;
        if(distance != -1.0 && distance <= min) {
            min = distance;
            newPosition.setX(possiblePosition.getX());
            newPosition.setY(possiblePosition.getY());
            possibleDirection = Direction.UP;
        }


        setPosition(newPosition);
        setDirection(possibleDirection);
        System.out.println(getPosition().toString() + " " + getDirection().name());
        System.out.println();
        hit();
    }

    @Override
    public boolean hit() {

        Pacman pacman = getLevel().getPacman();

        if(getBehaviour() != Behaviour.INACTIVE && getPosition().equals(pacman.getPosition())) {

            if (getBehaviour() == Behaviour.FRIGHTENED)
                this.kill();

            if (pacman.getState() == State.NORMAL)
                pacman.kill();

            return  true;
        }

        return false;

    }

    @Override
    public void kill() {
        super.kill();
        getLevel().addPoints(getPoints());
        setBehaviour(Behaviour.INACTIVE);
    }
}
