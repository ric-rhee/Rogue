package game.parserObjects;
import game.ObjectDisplayGrid;

public class Floor extends Displayable {

    public Floor(int x, int y) {
        setPosX(x);
        setPosY(y);
        setType('.');
    }
}
