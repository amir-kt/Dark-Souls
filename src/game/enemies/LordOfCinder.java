package game.enemies;

import engine.*;
import game.AppConfig;
import game.CindersOfALord;
import game.actions.PassiveWeaponAction;
import game.actions.WindSlashWeaponAction;
import game.behaviours.EmptyBehaviour;
import game.enums.Status;
import game.weapons.StormRuler;

public class LordOfCinder extends Enemy  {

    /**
     * Constructor.
     */
    public LordOfCinder(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints, new EmptyBehaviour());
        this.setSouls(AppConfig.LORD_OF_CINDER_SOULS);

        addItemToInventory(new CindersOfALord());
    }

    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();

        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new PassiveWeaponAction(this, direction));
        }


        Weapon weapon = otherActor.getWeapon();
        if (weapon instanceof StormRuler && ((StormRuler) weapon).charged() ) {
            actions.add(new WindSlashWeaponAction(otherActor));
        }

        return actions;
    }

    public void enragedCheck(){
        if (hitPoints < maxHitPoints){
            addCapability(Status.ENRAGED);
        }
    }
}
