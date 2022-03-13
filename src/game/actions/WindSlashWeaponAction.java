package game.actions;

import engine.Action;
import engine.Actor;
import engine.GameMap;
import engine.Weapon;
import game.weapons.GameWeaponItem;
import game.enums.Status;

public class WindSlashWeaponAction extends Action {
    private Actor target;

    public WindSlashWeaponAction(Actor target) {
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Weapon weapon = actor.getWeapon();
        BasicAttackAction action = new BasicAttackAction(target);
        action.setDamage(weapon.damage()*2);


        if (target.isConscious()){
            target.addCapability(Status.STUNNED);
        }
        ((GameWeaponItem)weapon).addCharge();

        return action.execute(actor, map) + " WINDSLASH";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " windslashes " + target;
    }

}
