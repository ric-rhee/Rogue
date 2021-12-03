package game.parserObjects;
import game.ObjectDisplayGrid;

public class DropPack extends CreatureAction {

    public DropPack(String name, String type, Creature owner) {
        super(name, type, owner);
    }

    public void action(ObjectDisplayGrid displayGrid) {
        displayGrid.dropItem(1);
    }
}