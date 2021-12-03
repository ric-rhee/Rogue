package game.parserObjects;
import game.ObjectDisplayGrid;

public class Remove extends CreatureAction {

    public Remove(String name, String type, Creature owner) {
        super(name, type, owner);
    }

    public void action(ObjectDisplayGrid displayGrid) {
        displayGrid.popObjectFromGrid(getOwner().getPosX(), getOwner().getPosY()+displayGrid.getTopHeight());
        displayGrid.writeToTerminal(getOwner().getPosX(), getOwner().getPosY()+displayGrid.getTopHeight());
    }
}
