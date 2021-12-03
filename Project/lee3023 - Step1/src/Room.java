package src;
import java.util.ArrayList;

public class Room extends Structure {
    private String name;
    private int room;
    private ArrayList<Creature> creatures = new ArrayList<Creature>();

    public Room(String name, int room, ArrayList<Creature> Monster) {
        super();
        setName(name);
        setId(room);
        setCreature(Monster);
    }

    public void setName(String _name) {
        name = _name;
    }

    public void setId(int _room) {
        room = _room;
    }

    public void setCreature(ArrayList<Creature>  Monster) {
        creatures = Monster;
    }
}
