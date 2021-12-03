package game.parserObjects;
import game.Char;
import game.ObjectDisplayGrid;
import java.util.ArrayList;

public abstract class Creature extends Displayable {

    private String name;
    private int room;
    private int serial;
    private ArrayList<CreatureAction> creatureActions = new ArrayList<CreatureAction>();

    public Creature(String name, int room, int serial) {
        super();
        setName(name);
        setID(room, serial);
    }

    public void setName(String _name) {
        name = _name;
    }

    public void setID(int _room, int _serial) {
        room = _room;
        serial = _serial;
    }

    public void addCreatureAction(CreatureAction ca) {
        creatureActions.add(ca);
    }

    public String getName() {
        return name;
    }

    public int getRoom() {
        return room;
    }

    public int getSerial() {
        return serial;
    }

    public ArrayList<CreatureAction> getCreatureActions() {
        return creatureActions;
    }

    public void drawObject(ObjectDisplayGrid displayGrid, int offsetY) {
        displayGrid.addObjectToDisplay(new Char(getType()), getPosX(), offsetY + getPosY());
    }
}
