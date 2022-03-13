package game.ground;

import engine.Ground;
import game.enums.Abilities;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {

	public Dirt() {
		super('.');
		this.addCapability(Abilities.BLINK_DESTINATION);
	}
}
