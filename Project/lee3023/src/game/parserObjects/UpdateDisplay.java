package game.parserObjects;
import game.ObjectDisplayGrid;

public class UpdateDisplay extends CreatureAction {

    public UpdateDisplay(String name, String type, Creature owner) {
        super(name, type, owner);
    }

    public void action(ObjectDisplayGrid displayGrid) {
        displayGrid.initializeText();
    }
}
