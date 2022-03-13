package game;

import engine.Actor;
import engine.DropItemAction;
import engine.Item;
import game.interfaces.Soul;

import static java.lang.Math.max;

public class TokenOfSouls extends Item implements Soul {
    private int souls=0;
    public TokenOfSouls() {
        super("Token of souls", '$', true);
    }

    public int getSouls() {
        return souls;
    }

    public void setSouls(int souls) {
        this.souls = souls;
    }

    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(souls);
        this.subtractSouls(souls);
    }

    @Override
    public boolean subtractSouls(int souls) {
        this.setSouls(max(getSouls()-souls, 0));
        return true;
    }

    @Override
    public boolean addSouls(int souls) {
        this.setSouls(max(getSouls()+souls, 0));
        return true;
    }

    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }
}