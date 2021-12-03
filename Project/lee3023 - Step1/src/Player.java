package src;

public class Player extends Creature {
    private String name;
    private int room;
    private int serial;
    private Item sword;
    private Item armor;

    public Player(String name, int room, int serial) {
        super();
        setName(name);
        setID(room, serial);
        //setWeapon(sword);
        //setArmor(armor);
    }

    public void setName(String _name) {
        name = _name;
    }

    public void setID(int _room, int _serial) {
        room = _room;
        serial = _serial;
    }

    public void setWeapon(Item _sword) {
        sword = _sword;
    }

    public void setArmor(Item _armor) {
        armor = _armor;
    }

    public String toString() {
        String str = "  Player: \n";
        /*
        str += "        " + "sword: " + sword + "\n";
        str += "        " + "armor: " + armor + "\n";
        */
        str += "        " + "name: " + name + "\n";
        str += "        " + "room: " + room + "\n";
        str += "        " + "serial: " + serial + "\n";
        return str;
    }
}
