package game.weapons;

import engine.*;
import game.*;
import game.actions.DarkmoonRangedAttackAction;
import game.actions.StunnedAction;
import game.enums.Status;

import java.util.List;

public class DarkmoonLongbow extends GameWeaponItem {

    public DarkmoonLongbow() {
        super("Darkmoon Longbow", 'z', AppConfig.DARKMOON_DAMAGE, "shoots", AppConfig.DARKMOON_HIT_RATE);
        criticalHit = AppConfig.DARKMOON_CRITICAL_HIT;
    }

    public List<Action> getAllowableActions(Actor otherActor, String direction, GameMap map) {
        allowableActions.clear();
        allowableActions.add(new DarkmoonRangedAttackAction(otherActor));
        return allowableActions.getUnmodifiableActionList();
    }

    public Action getAttackAction(Actor actor, Actor target, GameMap map){
        if (actor.hasCapability(Status.STUNNED)){
            return new StunnedAction();
        }

        Weapon weapon = actor.getWeapon();
        if (!map.contains(target) && !map.contains(actor))
            return null;


        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);

        NumberRange xs, ys;
        if (here.x() == there.x() || here.y() == there.y()) {
            return new DarkmoonRangedAttackAction(target);
        }
        return null;
    }

}
