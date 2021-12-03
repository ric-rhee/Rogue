package game.parserObjects;

public class ItemAction extends Action {
    private Item owner;

    public ItemAction(String name, String type, Item _owner) {
        super(name, type);
        owner = _owner;
    }

    public Item getOwner() {
        return owner;
    }
}
