package game.actions;

import engine.Action;
import engine.Actor;
import engine.GameMap;
import game.Player;
import game.ground.Bonfire;
import game.helpers.BonfireHelper;

public class LightBonfireAction extends Action {

    private Bonfire bonfire;

    /**
     * This action is responsible for activating bonfires
     *
     * @param bonfire the bonfire we are trying to light
     */
    public LightBonfireAction(Bonfire bonfire) {
        this.bonfire = bonfire;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        bonfire.setLit(true);

        if (null != BonfireHelper.findBonfireLocation(map, bonfire))
            ((Player) actor).setSpawnLocation(BonfireHelper.findBonfireLocation(map, bonfire));

        return bonfire + " lit";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " lights the " + bonfire;
    }
}
