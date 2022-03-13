package game.weapons;

import engine.*;
import game.AppConfig;
import game.actions.PassiveWeaponAction;
import game.Utility;
import game.actions.StunnedAction;
import game.enemies.LordOfCinder;
import game.enums.Status;

public class YhormsGreatMachete extends GameWeaponItem {
    public YhormsGreatMachete() {
        super("Yhorm's Great Machete", '9', AppConfig.YHORMS_GREAT_MACHETE_DAMAMGE, "cuts", AppConfig.YHORMS_GREAT_MACHETE_HIT_RATE);
        hitRateIncrease = AppConfig.YHORMS_GREAT_MACHETE_EMBER_FORM_HIT_RATE_INCREASE;
    }

    @Override
    public Action getAttackAction(Actor actor, Actor target, GameMap map){
        if (actor.hasCapability(Status.STUNNED)){
            return new StunnedAction();
        }

        Weapon weapon = actor.getWeapon();
        if (actor.getClass() == LordOfCinder.class) {

            if (!map.contains(target) && !map.contains(actor))
                return null;

            Location here = map.locationOf(actor);
            Location there = map.locationOf(target);
            int currentDistance = Utility.distance(here, there);

            if (currentDistance == 1) { //condition ensures actor will attack target only if in adjacent squares

                for (Exit exit : here.getExits()) {
                    Location destination = exit.getDestination();

                    if (destination == there) {
                        return new PassiveWeaponAction(target, exit.getName());
                    }
                }
            }
        }
        return null;
    }
}
