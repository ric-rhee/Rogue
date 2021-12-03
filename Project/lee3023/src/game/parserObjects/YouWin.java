package game.parserObjects;
import game.ObjectDisplayGrid;

public class YouWin extends CreatureAction {

    public YouWin(String name, String type, Creature owner) {
        super(name, type, owner);
    }

    public void action(ObjectDisplayGrid displayGrid) {
        displayGrid.setInfoString(getMessage());
        displayGrid.initializeText();
    }
}
