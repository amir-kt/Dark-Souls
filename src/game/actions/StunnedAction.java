package game.actions;

import engine.Action;
import engine.Actor;
import engine.GameMap;
import game.enums.Status;

public class StunnedAction extends Action {
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.removeCapability(Status.STUNNED);
        return actor + " was Stunned";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "stunning";
    }


}
