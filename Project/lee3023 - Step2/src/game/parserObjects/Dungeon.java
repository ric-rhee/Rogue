package game.parserObjects;
import java.util.ArrayList;

public class Dungeon {

    private String name;
    private int width;
    private int topHeight;
    private int gameHeight;
    private int bottomHeight;

    private ArrayList<Displayable> displayables = new ArrayList<Displayable>(); // add references to objects to this array so that it has a list of all specified displays

    public Dungeon(String _name, int _width, int _topHeight, int _gameHeight, int _bottomHeight) {
        name = _name;
        width = _width;
        topHeight = _topHeight;
        gameHeight = _gameHeight;
        bottomHeight = _bottomHeight;
    }

    public void addCreature(Creature creature) {
        displayables.add(creature);
    }

    public void addItem(Item item) {
        displayables.add(item);
    }

    public void addRoom(Room room) {
        displayables.add(room);
    }

    public void addPassage(Passage passage) {
        displayables.add(passage);
    }

    public int getWidth() {
        return width;
    }

    public int getTopHeight() {
        return topHeight;
    }

    public int getGameHeight() {
        return gameHeight;
    }

    public int getBottomHeight() {
        return bottomHeight;
    }

    public ArrayList<Displayable> getDisplayables() {
        return displayables;
    }
}
