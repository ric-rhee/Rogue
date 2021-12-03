package src;

public class Displayable {
    private int visible;
    private int posX;
    private int posY;
    private int width;
    private int height;
    private int hp;
    private int hpMoves;
    private int hpMax;

    private Structure[] rooms;
    private Creature[] creature;
    private Item[] item;
    private Magic[] magiks;

    public Displayable() {

    }

    /*
    public void setInvisible();
    public void setVisible();
    public void setMaxHit(int maxHit);
    public void setHpMove(int hpMoves);
    public void setHp(int Hp);
    public void setType(char t);
    public void setIntValue(int v);
    public void setPosX(int x);
    public void setPosY(int y);
    public void setWidth(int x);
    public void setHeight(int y);
    */

    public String toString() {
        String str = "Displayable: \n";
        return str;
    }
}
