package game.behaviours;

import engine.*;
import game.interfaces.Behaviour;

public class EmptyBehaviour implements Behaviour {

    @Override
    public Action getAction(Actor actor, GameMap map) {
        return new DoNothingAction();
    }
}
