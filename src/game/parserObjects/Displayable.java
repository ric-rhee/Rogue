package game.parserObjects;
import game.ObjectDisplayGrid;

public abstract class Displayable {

    // for everything
    private int visible = 1;
    private int posX;
    private int posY;

    // for rooms
    private int width;
    private int height;

    // for creatures
    private char type;
    private int hp;
    private int hpMoves;
    private int maxhit;

    // for items
    private int ItemIntValue;

    public Displayable() {
    }

    public void setVisible(int _visible) {
        visible = _visible;
    }

    public void setPosX(int _posX) {
        posX = _posX;
    }

    public void setPosY(int _posY) {
        posY = _posY;
    }

    public void setWidth(int _width) {
        width = _width;
    }

    public void setHeight(int _height) {
        height = _height;
    }

    public void setType(char _type) {
        type = _type;
    }

    public void setHP(int _hp) {
        hp = _hp;
    }

    public void setHPMoves(int _hpMoves) {
        hpMoves = _hpMoves;
    }

    public void setMaxhit(int _maxhit) {
        maxhit = _maxhit;
    }

    public void setItemIntValue(int _ItemIntValue) {
        ItemIntValue = _ItemIntValue;
    }

    public int getVisible() {
        return visible;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public char getType() {
        return type;
    }

    public int getHP() {
        return hp;
    }

    public int getHPMoves() {
        return hpMoves;
    }

    public int getMaxhit() {
        return maxhit;
    }

    public int getItemIntValue() {
        return ItemIntValue;
    }

    public String getName() {
        String str = "";
        return str;
    }

    public void addPosX(int posX) {
    }

    public void addPosY(int posY) {
    }

    public void drawObject(ObjectDisplayGrid displayGrid) {
    }

    public void drawObject(ObjectDisplayGrid displayGrid, int offsetY) {
    }

    public void drawObject(ObjectDisplayGrid displayGrid, int x, int y) {
    }

    public void addCreatureAction(CreatureAction action) {
    }

    public void addItemAction(ItemAction action) {
    }

    public void pushObject(ObjectDisplayGrid displayGrid) {
    }

    public void pushObject(ObjectDisplayGrid displayGrid, int offsetY) {
        displayGrid.pushObjectToGrid(this, getPosX(), offsetY + getPosY());
    }

    public void pickUpItem(Displayable item) {
    }
}
