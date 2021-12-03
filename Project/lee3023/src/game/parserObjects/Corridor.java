package game.parserObjects;
import game.ObjectDisplayGrid;

public class Corridor extends Displayable {

    public Corridor(int x, int y) {
        setPosX(x);
        setPosY(y);
        setType('#');
    }
}
