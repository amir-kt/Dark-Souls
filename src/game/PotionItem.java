package game;

import engine.Item;
import game.enums.Abilities;

public abstract class PotionItem extends Item {
    protected int charges;
    protected int maxCharges;

    public PotionItem(String name, char displayChar, boolean portable, int charges, int maxCharges) {
        super(name, displayChar, portable);
        this.addCapability(Abilities.DRINKABLE);
        this.charges = charges;
        this.maxCharges = maxCharges;
    }

    public abstract void reduceCharges(int num);

    public abstract void increaseCharges(int num);

    public int getCharges() {
        return charges;
    }

    public int getMaxCharges() {
        return maxCharges;
    }

    @Override
    public String toString() {
        return this.name + "(charges: " + this.charges + "/" + maxCharges + ")";
    }
}
