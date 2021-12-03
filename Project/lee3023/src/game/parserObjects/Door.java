package game.parserObjects;
import game.ObjectDisplayGrid;

public class Door extends Displayable {

    public Door(int x, int y) {
        setPosX(x);
        setPosY(y);
        setType('+');
    }
}
