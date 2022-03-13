package game.interfaces;

import engine.Actions;
import engine.Actor;
import engine.GameMap;

/**
 * this interface can be used to implement blink behaviour for different actors
 */
public interface Blink {
    Actions getBlinkActionsForActor(GameMap map, Actor actor);
}
