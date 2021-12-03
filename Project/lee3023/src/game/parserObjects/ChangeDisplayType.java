package game.parserObjects;

import game.ObjectDisplayGrid;

public class ChangeDisplayType extends CreatureAction {

    public ChangeDisplayType(String name, String type, Creature owner) {
        super(name, type, owner);
    }

    public void action(ObjectDisplayGrid displayGrid) {
        getOwner().setType(getActionCharValue());
    }
}
