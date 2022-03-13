package game.actions;

import engine.Action;
import engine.Actor;
import engine.GameMap;
import game.enums.Abilities;
import game.interfaces.Drinkable;

import static java.lang.Integer.parseInt;

public class DrinkAction extends Action {
    protected Drinkable potion;
    public DrinkAction(Drinkable item) {
        potion = item;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return potion.consume(actor.hasCapability(Abilities.DRINK), actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " drinks " + potion.toString();
    }
}
