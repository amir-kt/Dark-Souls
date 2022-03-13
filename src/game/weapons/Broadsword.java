package game.weapons;

import game.AppConfig;

public class Broadsword extends GameWeaponItem {
    public Broadsword() {
        super("Broadsword", '3', AppConfig.BROADSWORD_DAMAGE, "strikes", AppConfig.BROADSWORD_HIT_RATE);
        criticalHit = AppConfig.BROADSWORD_CRITICAL_HIT;
    }
}

