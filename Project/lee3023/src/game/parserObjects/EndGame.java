package game.parserObjects;
import game.ObjectDisplayGrid;

public class EndGame extends CreatureAction {

    public EndGame(String name, String type, Creature owner) {
        super(name, type, owner);
    }

    public void action(ObjectDisplayGrid displayGrid) {
        displayGrid.setInfoString("");
        displayGrid.initializeText();
        displayGrid.setInfoString(getMessage());
        System.out.println(getMessage());
        displayGrid.initializeText();
        displayGrid.endGame();
    }
}
