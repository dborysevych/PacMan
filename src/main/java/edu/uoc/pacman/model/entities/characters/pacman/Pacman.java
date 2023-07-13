package edu.uoc.pacman.model.entities.characters.pacman;

import edu.uoc.pacman.model.Level;
import edu.uoc.pacman.model.entities.Scorable;
import edu.uoc.pacman.model.entities.characters.Character;
import edu.uoc.pacman.model.entities.characters.ghosts.Behaviour;
import edu.uoc.pacman.model.entities.characters.ghosts.Ghost;
import edu.uoc.pacman.model.entities.items.Energizer;
import edu.uoc.pacman.model.entities.items.Life;
import edu.uoc.pacman.model.entities.items.MapItem;
import edu.uoc.pacman.model.entities.items.Pickable;
import edu.uoc.pacman.model.utils.Direction;
import edu.uoc.pacman.model.utils.Position;
import edu.uoc.pacman.model.utils.Sprite;

import java.util.List;

public class Pacman extends Character {

    //attributes
    private State state;

    //constructor
    public Pacman(Position startPosition, Direction direction, State state, Level level) {
        super(startPosition, null, null, level);

        setState(state);
        setDirection(direction);

    }


    //methods
    public State getState() {
        return state;
    }

    public void setState(State state) {
        if(state != null) {
            this.state = state;
            setDuration(getState().getDuration());
        }
    }

    private void nextState() {

        setDuration(getDuration() - 1);

        if(getDuration() <= 0) {

            if(getState() != State.NORMAL)
                setState(State.NORMAL);

        }
    }

    public void reset() {
        super.reset();
        setState(State.INVINCIBLE);
        setDirection(Direction.UP);
        setSprite(Sprite.PACMAN_UP);
    }

    @Override
    public void move() {

        nextState();
        Level level = getLevel();
        MapItem item;
        Position pacmanPosition = getPosition();
        Position newPosition = new Position(0, 0);

        if(getDirection() == Direction.DOWN) {
            newPosition.setX(pacmanPosition.getX());
            newPosition.setY(pacmanPosition.getY() - 1);

            item = level.getMapItem(newPosition);

            if(item.isPathable())
                setPosition(newPosition);
        }

        if(getDirection() == Direction.RIGHT) {
            newPosition.setX(pacmanPosition.getX() + 1);
            newPosition.setY(pacmanPosition.getY());

            item = level.getMapItem(newPosition);

            if(item.isPathable())
                setPosition(newPosition);
        }

        if(getDirection() == Direction.LEFT) {
            newPosition.setX(pacmanPosition.getX() - 1);
            newPosition.setY(pacmanPosition.getY());

            item = level.getMapItem(newPosition);

            if(item.isPathable())
                setPosition(newPosition);
        }

        if(getDirection() == Direction.UP) {
            newPosition.setX(pacmanPosition.getX());
            newPosition.setY(pacmanPosition.getY() + 1);

            item = level.getMapItem(newPosition);

            if(item.isPathable())
                setPosition(newPosition);
        }

        eat();
        hit();
    }

    @Override
    public void setDirection(Direction direction) {

        super.setDirection(direction);

        if(direction == Direction.DOWN)
            setSprite(Sprite.PACMAN_DOWN);

        if(direction == Direction.UP)
            setSprite(Sprite.PACMAN_UP);

        if(direction == Direction.RIGHT)
            setSprite(Sprite.PACMAN_RIGHT);

        if(direction == Direction.LEFT)
            setSprite(Sprite.PACMAN_LEFT);

    }

    private void eat() {


        Position pacmanPosition = getPosition();
        Level level = getLevel();
        MapItem item = level.getMapItem(pacmanPosition.getX(), pacmanPosition.getY());

         if(item instanceof Pickable) {
             ((Pickable) item).setPicked(true);
             level.removeMapItem(item);
         }

         if(item instanceof Scorable)
             level.addPoints(((Scorable) item).getPoints());

         if(item instanceof Energizer) {
             level.setGhostsFrightened();
             setState(State.EATER);
         }

         if(item instanceof Life)
             level.increaseLives();



    }

    @Override
    public boolean hit() {

        Position pacmanPosition = getPosition();
        Level level = getLevel();
        List<Ghost> listGhost = level.getGhostList();

        if(getState() == State.INVINCIBLE)
            return false;


        for (Ghost ghost: listGhost) {

            if(ghost.getBehaviour() != Behaviour.INACTIVE && pacmanPosition.equals(ghost.getPosition())) {

                if(ghost.getBehaviour() == Behaviour.FRIGHTENED) {
                    ghost.kill();
                    return true;
                }

                if(state == State.NORMAL)
                    kill();

                return true;

            }

        }


        return false;
    }

    @Override
    public void kill() {
        super.kill();
        getLevel().decreaseLives();
        setState(State.INVINCIBLE);
    }
}
