package game.weapons;

import engine.*;
import game.*;
import game.actions.ChargeWeaponAction;

import java.util.List;

public class StormRuler extends GameWeaponItem {


    public StormRuler() {
        super("Storm Ruler", '7', AppConfig.STORM_RULER_DAMAGE, "strikes", AppConfig.STORM_RULER_HIT_RATE);
        maxCharges = AppConfig.STORM_RULER_ACTIVE_SKILL_CHARGES;
    }

    @Override //only Player can pick up Storm Ruler
    public PickUpItemAction getPickUpAction(Actor actor) {
        if (actor instanceof Player) {
            return super.getPickUpAction(actor);
        }
        return null;
    }


    @Override
    public List<Action> getAllowableActions() {
        allowableActions.clear();
        if (!charged()){
            allowableActions.add(new ChargeWeaponAction());
        }
        return allowableActions.getUnmodifiableActionList();
    }


}
