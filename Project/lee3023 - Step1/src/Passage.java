package src;

public class Passage extends Structure {
    private String name;
    private int room1;
    private int room2;

    public Passage(String _name) {
        super();
        name = _name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public void setID(int _room1, int _room2) {
        room1 = _room1;
        room2 = _room2;
    }
}
