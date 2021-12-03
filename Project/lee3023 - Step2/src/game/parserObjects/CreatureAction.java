package game.parserObjects;

public class CreatureAction extends Action {
    private Creature owner;

    public CreatureAction(String name, String type, Creature _owner) {
        super(name, type);
        owner = _owner;
    }

    public Creature getOwner() {
        return owner;
    }
}
