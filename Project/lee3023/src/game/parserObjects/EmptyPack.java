package game.parserObjects;
import game.ObjectDisplayGrid;

public class EmptyPack extends CreatureAction {

    public EmptyPack(String name, String type, Creature owner) {
        super(name, type, owner);
    }

    public void action(ObjectDisplayGrid displayGrid) {
        while(displayGrid.dropItem(1));
        displayGrid.setInfoString("Pack was emptied");
        displayGrid.initializeText();
    }
}