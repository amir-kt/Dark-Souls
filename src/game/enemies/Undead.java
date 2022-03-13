package game.enemies;


import engine.*;
import game.*;
import game.behaviours.WanderBehaviour;

/**
 * An undead minion.
 */
public class Undead extends Enemy {

	public Undead() {
		super("Hollow Soldier", 'u', AppConfig.UNDEAD_MAX_HIT_POINTS, new WanderBehaviour());
		this.setSouls(AppConfig.UNDEAD_SOULS);
	}

	@Override
	//undead doesn't have a weapon
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(20, "punches");
	}

	@Override
	public String toString() {
		return name + "(" + Math.max(hitPoints,0) + "/" + maxHitPoints + ")";
	}
}


