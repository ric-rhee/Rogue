package game.parserObjects;
import game.Char;
import game.ObjectDisplayGrid;

public class Room extends Structure {

    private String name;
    private int room;

    public Room(int _room) {
        setID(_room);
    }

    public void setName(String _name) {
        name = _name;
    }

    public void setID(int _room) {
        room = _room;
    }

    public String getName() {
        return name;
    }

    public int getRoom() {
        return room;
    }

    public void drawObject(ObjectDisplayGrid displayGrid, int offsetY) {
        for(int x = getPosX(); x < getPosX() + getWidth(); x++) {
            for(int y = getPosY() + offsetY; y < getPosY() + getHeight() + offsetY; y++) {
                if(x == getPosX() | x == (getPosX() + getWidth() - 1))
                    displayGrid.addObjectToDisplay(new Char('X'), x, y);
                else if(y == getPosY() + offsetY | y == (getPosY() + getHeight() + offsetY - 1))
                    displayGrid.addObjectToDisplay(new Char('X'), x, y);
                else
                    displayGrid.addObjectToDisplay(new Char('.'), x, y);
            }
        }
    }

    public String toString() {
        String str = "Room: " + getRoom() + "\n";
        str += "\t" + "posX: " + getPosX();
        str += "\t" + "posY: " + getPosY();
        str += "\t" + "visible: " + getVisible();
        return str;
    }
}
