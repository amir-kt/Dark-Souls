package game.interfaces;

import engine.Actor;

public interface Drinkable {
    String consume(boolean condition, Actor actor);
}
