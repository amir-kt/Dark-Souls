package game.actions;

import engine.Action;
import engine.Actor;
import engine.GameMap;
import game.Player;
import game.ResetManager;
import game.ground.Bonfire;
import game.helpers.BonfireHelper;

public class SoftResetAction extends Action {

    private Bonfire bonfire;

    public SoftResetAction(){}

    public SoftResetAction(Bonfire bonfire) {
        this.bonfire = bonfire;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
            ResetManager.getInstance().run();

            if (null != BonfireHelper.findBonfireLocation(map, bonfire))
                ((Player) actor).setSpawnLocation(BonfireHelper.findBonfireLocation(map, bonfire));

            return "the game has gone through a soft reset";
    }

    @Override
    public String menuDescription(Actor actor) {
        return bonfire != null ? actor + " rests at " + bonfire : actor + " rests";
    }
}
