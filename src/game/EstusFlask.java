package game;

import engine.Actor;
import game.actions.DrinkAction;
import game.interfaces.Drinkable;

public class EstusFlask extends PotionItem implements Drinkable {

    public EstusFlask() {
        super("EstusFlask", 'E', false, 3, 3);
        allowableActions.add(new DrinkAction(this));
    }

    @Override
    public void reduceCharges(int num) {
        charges = Math.max(charges - num, 0);
    }

    @Override
    public void increaseCharges(int num) {
        charges = Math.min(charges + num, maxCharges);
    }

    @Override
    public String consume(boolean condition, Actor actor) {
        if (condition) {
            int healAmount = (int) (AppConfig.PLAYER_HIT_POINTS * 0.4);
            if (actor.getInventory().contains(this) && this.getCharges() > 0) {
                actor.heal(healAmount);
                this.reduceCharges(1);
                return "Healed " + actor + " by " + healAmount + " hit points";
            }
            return "could not heal " + actor + " because actor did not have the correct potion or did not have enough charges";
        }
       return "could not heal " + actor + " because actor is not a player";
    }
}
