package game.parserObjects;
import game.ObjectDisplayGrid;

public class Wall extends Displayable {

    public Wall(int x, int y) {
        setPosX(x);
        setPosY(y);
        setType('X');
    }
}
