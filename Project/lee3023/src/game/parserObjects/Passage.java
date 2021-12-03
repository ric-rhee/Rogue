package game.parserObjects;
import java.util.ArrayList;
import game.Char;
import game.ObjectDisplayGrid;

public class Passage extends Structure {

    private String name;
    private int room1;
    private int room2;
    private ArrayList<Integer> coords = new ArrayList<Integer>();

    public Passage(int _room1, int _room2) {
        super();
        setID(_room1, _room2);
    }

    public void setName(String _name) {
        name = _name;
    }

    public void setID(int _room1, int _room2) {
        room1 = _room1;
        room2 = _room2;
    }

    public void addPosX(int _posX) {
        coords.add(_posX);
    }

    public void addPosY(int _posY) {
        coords.add(_posY);
    }

    public String getName() {
        return name;
    }

    public int getRoom1() {
        return room1;
    }

    public int getRoom2() {
        return room2;
    }

    public void drawObject(ObjectDisplayGrid displayGrid, int offsetY) {

        if(coords.size() == 0) {
            System.out.println("no coordinates given for passage");
            return;
        }

        int x;
        int y;
        int a = coords.get(0);
        int b = coords.get(1) + offsetY;
        int endx = coords.get(coords.size() - 2);
        int endy = coords.get(coords.size() - 1) + offsetY;

        for (int n = 2; n < coords.size(); n += 2) {
            x = coords.get(n);
            y = coords.get(n+1) + offsetY;

            if (a != x) { // draw horizontally
                if (a == coords.get(0) && b == coords.get(1) + offsetY) { // check if first coord
                    displayGrid.addObjectToDisplay('+', a, b);
                } else {
                    displayGrid.addObjectToDisplay('#', a, b);
                }
                if (a < x) {
                    a += 1;
                    for (; a < x; a++) {
                        displayGrid.addObjectToDisplay('#', a, b);
                    }
                }
                else if (a > x) {
                    a -= 1;
                    for (; a > x; a--) {
                        displayGrid.addObjectToDisplay('#', a, b);
                    }
                }
                if (a == endx) {
                    displayGrid.addObjectToDisplay('+', a, b);
                } else {
                    displayGrid.addObjectToDisplay('#', a, b);
                }
                a = x;
            }
            else if (b != y) { // draw vertically
                if (a == coords.get(0) && b == coords.get(1) + offsetY) {
                    displayGrid.addObjectToDisplay('+', a, b);
                } else {
                    displayGrid.addObjectToDisplay('#', a, b);
                }
                if (b < y) {
                    b += 1;
                    for (; b < y; b++) {
                        displayGrid.addObjectToDisplay('#', a, b);
                    }
                }
                else if (b > y) {
                    b -= 1;
                    for(; b > y; b--) {
                        displayGrid.addObjectToDisplay('#', a, b);
                    }
                }
                if (b == endy) {
                    displayGrid.addObjectToDisplay('+', a, b);
                } else {
                    displayGrid.addObjectToDisplay('#', a, b);
                }
                b = y;
            }
        }
    }

    public void pushObject(ObjectDisplayGrid displayGrid, int offsetY) {

        if(coords.size() == 0) {
            System.out.println("no coordinates given for passage");
            return;
        }

        int x;
        int y;
        int a = coords.get(0);
        int b = coords.get(1) + offsetY;
        int endx = coords.get(coords.size() - 2);
        int endy = coords.get(coords.size() - 1) + offsetY;

        for (int n = 2; n < coords.size(); n += 2) {
            x = coords.get(n);
            y = coords.get(n+1) + offsetY;
            if (a != x) { // draw horizontally
                if (a == coords.get(0) && b == coords.get(1) + offsetY) { // check if first coord
                    displayGrid.pushObjectToGrid(new Door(a, b), a, b);
                } else {
                    displayGrid.pushObjectToGrid(new Corridor(a, b), a, b);
                }
                if (a < x) {
                    a += 1;
                    for (; a < x; a++) {
                        displayGrid.pushObjectToGrid(new Corridor(a, b), a, b);
                    }
                }
                else if (a > x) {
                    a -= 1;
                    for (; a > x; a--) {
                        displayGrid.pushObjectToGrid(new Corridor(a, b), a, b);
                    }
                }
                if (a == endx) {
                    displayGrid.pushObjectToGrid(new Door(a, b), a, b);
                } else {
                    displayGrid.pushObjectToGrid(new Corridor(a, b), a, b);
                }
                a = x;
            }
            else if (b != y) { // draw vertically
                if (a == coords.get(0) && b == coords.get(1) + offsetY) {
                    displayGrid.pushObjectToGrid(new Door(a, b), a, b);
                } else {
                    displayGrid.pushObjectToGrid(new Corridor(a, b), a, b);
                }
                if (b < y) {
                    b += 1;
                    for (; b < y; b++) {
                        displayGrid.pushObjectToGrid(new Corridor(a, b), a, b);
                    }
                }
                else if (b > y) {
                    b -= 1;
                    for(; b > y; b--) {
                        displayGrid.pushObjectToGrid(new Corridor(a, b), a, b);
                    }
                }
                if (b == endy) {
                    displayGrid.pushObjectToGrid(new Door(a, b), a, b);
                } else {
                    displayGrid.pushObjectToGrid(new Corridor(a, b), a, b);
                }
            }
        }
    }

    public String toString() {
        String str = "Passages:";
        str += "\t" + "visible: " + getVisible();
        str += "\t" + "room1: " + getRoom1();
        str += "\t" + "room2: " + getRoom2();
        str += "\t" + coords.toString();
        return str;
    }
}
