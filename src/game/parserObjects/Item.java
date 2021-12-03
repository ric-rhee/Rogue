package game.parserObjects;
import java.util.ArrayList;

public abstract class Item extends Displayable {
    private String name;
    private int room;
    private int serial;
    private Creature owner = null;
    private ArrayList<ItemAction> itemActions = new ArrayList<ItemAction>();

    public Item(String name, int room, int serial) {
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

    public void setOwner(Creature _owner) {
        owner = _owner;
    }

    public void addItemAction(ItemAction ia) {
        itemActions.add(ia);
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

    public Creature getOwner() {
        return owner;
    }

    public ArrayList<ItemAction> getItemActions() {
        return itemActions;
    }
}
