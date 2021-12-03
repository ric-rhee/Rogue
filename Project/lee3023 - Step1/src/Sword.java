package src;

public class Sword extends Item {
    private String name;
    private int room;
    private int serial;

    public Sword(String name, int room, int serial) {
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

    public String toString() {
        String str = "  Sword: \n";
        str += "        " + "name: " + name + "\n";
        str += "        " + "room: " + room + "\n";
        str += "        " + "serial: " + serial + "\n";
        return str;
    }
}
