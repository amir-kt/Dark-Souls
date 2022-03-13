package game.actions;

import engine.*;
import game.weapons.GameWeaponItem;

public class ChargeWeaponAction extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {
        Weapon weapon = actor.getWeapon();

        ((GameWeaponItem)weapon).addCharge();

        return actor + " charged " + weapon + " " + ((GameWeaponItem)weapon).getCharge();
    }

    @Override
    public String menuDescription(Actor actor){
        Weapon weapon = actor.getWeapon();

        return actor + " charge " + weapon + " " + ((GameWeaponItem)weapon).getCharge();
    }
}
