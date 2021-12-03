package game.parserObjects;
import java.util.ArrayList;

public class Player extends Creature {

    private ArrayList<Displayable> inventory = new ArrayList<Displayable>();
    private Item armor;
    private Item sword;

    public Player(String name, int room, int serial) {
        super(name, room, serial);
        setType('@');
    }

    public void pickUpItem(Displayable item) {
        inventory.add(item);
    }

    public void setArmor(Item _armor) {
        armor = _armor;
    }

    public void setWeapon(Item _sword) {
        sword = _sword;
    }

    public ArrayList<Displayable> getInventory() {
        return inventory;
    }

    public Item getArmor() {
        return armor;
    }

    public Item getSword() {
        return sword;
    }

    public String toString() {
        String str = "Player:\n";
        str += "\t" + "name: " + getName();
        str += "\t" + "room: " + getRoom();
        str += "\t" + "serial: " + getSerial();
        str += "\t" + "posX: " + getPosX();
        str += "\t" + "posY: " + getPosY();
        return str;
    }
}
