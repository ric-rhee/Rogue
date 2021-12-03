package game.parserObjects;

public class Monster extends Creature {

    public Monster(String name, int room, int serial) {
        super(name, room, serial);
    }

    public String toString() {
        String str = "Monster:\n";
        str += "\t" + "name: " + getName();
        str += "\t" + "room: " + getRoom();
        str += "\t" + "serial: " + getSerial();
        str += "\t" + "posX: " + getPosX();
        str += "\t" + "posY: " + getPosY();
        str += "\t" + "visible: " + getVisible();
        return str;
    }
}
