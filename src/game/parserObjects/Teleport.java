package game.parserObjects;
import game.ObjectDisplayGrid;
import java.util.Random;
import java.util.Stack;

public class Teleport extends CreatureAction {

    public Teleport(String name, String type, Creature owner) {
        super(name, type, owner);
    }

    public void action(ObjectDisplayGrid displayGrid) {

        Random rand = new Random();
        int width = displayGrid.getDisplayWidth();
        int height = displayGrid.getDisplayHeight();
        int randX;
        int randY;

        Stack<Displayable>[][] objectStacks = displayGrid.getObjectStacks();

        while(true) {
            randX = rand.nextInt(width);
            randY = rand.nextInt(height-displayGrid.getTopHeight())+displayGrid.getTopHeight();
            if(!objectStacks[randX][randY].isEmpty()) { // is a valid space
                if(objectStacks[randX][randY].peek().getType() != 'X'
                 && objectStacks[randX][randY].peek().getType() == '#'
                 || objectStacks[randX][randY].peek().getType() == '.'
                 || objectStacks[randX][randY].peek().getType() == '+'
                 || objectStacks[randX][randY].peek().getType() == ')'
                 || objectStacks[randX][randY].peek().getType() == ']'
                 || objectStacks[randX][randY].peek().getType() == '?') { // not a wall or monster
                    Displayable monster = displayGrid.popObjectFromGrid(getOwner().getPosX(), getOwner().getPosY()+displayGrid.getTopHeight());
                    displayGrid.writeToTerminal(getOwner().getPosX(), getOwner().getPosY()+displayGrid.getTopHeight());
                    displayGrid.pushObjectToGrid(monster, randX, randY);
                    break;
                }
            }
        }

        getOwner().setPosX(randX);
        getOwner().setPosY(randY-displayGrid.getTopHeight());
        displayGrid.setInfoString(getMessage());
    }
}
