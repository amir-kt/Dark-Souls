package game.behaviours;

import engine.*;
import game.weapons.GameWeaponItem;
import game.actions.PassiveWeaponAction;
import game.Utility;
import game.interfaces.Behaviour;

public class FollowAttackBehaviour implements Behaviour {

    private Actor target;

    public FollowAttackBehaviour(Actor subject) {
        this.target = subject;
    }

    public Action getAction(Actor actor, GameMap map) {

        Weapon weapon = actor.getWeapon();

        Action action;
        if (weapon.getClass() == GameWeaponItem.class){
            action = ((GameWeaponItem) weapon).getAttackAction(actor, target, map);
        }
        else {
            action = new PassiveWeaponAction(target);
        }



        if (action != null) {
            return action;
        }

        if(!map.contains(target) && !map.contains(actor))
            return null;

        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);
        int currentDistance = Utility.distance(here, there);

        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                int newDistance = Utility.distance(destination, there);
                if (newDistance < currentDistance) {
                    return new MoveActorAction(destination, exit.getName());
                }
            }

        }
        return null;
    }

}






