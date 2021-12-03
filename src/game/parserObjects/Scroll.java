package game.parserObjects;
import game.ObjectDisplayGrid;

public class Scroll extends Item {

    public Scroll(String name, int room, int serial) {
        super(name, room, serial);
        this.setType('?');
    }

    public String toString() {
        String str = "Scroll:\n";
        str += "\t" + "name: " + getName();
        str += "\t" + "room: " + getRoom();
        str += "\t" + "serial: " + getSerial();
        str += "\t" + "posX: " + getPosX();
        str += "\t" + "posY: " + getPosY();
        str += "\t" + "visible: " + getVisible();
        return str;
    }

    public void drawObject(ObjectDisplayGrid displayGrid, int offsetY) {
        displayGrid.addObjectToDisplay('?', getPosX(), offsetY + getPosY());
    }
}
