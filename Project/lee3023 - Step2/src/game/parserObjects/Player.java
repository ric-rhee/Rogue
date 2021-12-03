package game.parserObjects;

public class Player extends Creature {

    private Item armor;
    private Item sword;

    public Player(String name, int room, int serial) {
        super(name, room, serial);
        setType('@');
    }

    public void setArmor(Item _armor) {
        armor = _armor;
    }

    public void setWeapon(Item _sword) {
        sword = _sword;
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
