package game.actions;

import engine.*;
import game.enums.Abilities;

/**
 * this action is used for moving the actor to a different location in the map
 *
 */
public class TeleportAction extends MoveActorAction {
    public TeleportAction(Location moveToLocation) {
        super(moveToLocation, "");
    }

    public TeleportAction(Location moveToLocation, String direction, String hotKey){
        super(moveToLocation, direction, hotKey);
    }

    /**
     * this method makes sure the actor can teleport before moving it to the new location
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return returns a message to print to console
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.hasCapability(Abilities.CAN_TELEPORT)) {
            super.execute(actor, map);
            return menuDescription(actor);
        }
        return actor + " cannot move " + actor + " :(";
    }

    @Override
    public String menuDescription(Actor actor) {
        if (direction.isEmpty()){
            return actor + " moves to " + moveToLocation.getGround();
        }
        else {
            return actor + " blinks " + direction;
        }
    }
}
