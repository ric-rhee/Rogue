package game.parserObjects;
import game.Char;
import game.ObjectDisplayGrid;

public class Armor extends Item {

    public Armor(String name, int room, int serial) {
        super(name, room, serial);
        this.setType(']');
    }

    public String toString() {
        String str = "Armor:\n";
        str += "\t" + "name: " + getName();
        str += "\t" + "room: " + getRoom();
        str += "\t" + "serial: " + getSerial();
        str += "\t" + "posX: " + getPosX();
        str += "\t" + "posY: " + getPosY();
        str += "\t" + "visible: " + getVisible();
        return str;
    }

    public void drawObject(ObjectDisplayGrid displayGrid, int offsetY) {
        displayGrid.addObjectToDisplay(']', getPosX(), offsetY + getPosY());
    }

    public void pushObject(ObjectDisplayGrid displayGrid, int offsetY) {
        displayGrid.pushObjectToGrid(this, getPosX(), offsetY + getPosY());
    }
}
