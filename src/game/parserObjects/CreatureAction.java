package game.parserObjects;
import game.ObjectDisplayGrid;

public abstract class CreatureAction extends Action {

    private Creature owner;

    public CreatureAction(String name, String type, Creature _owner) {
        super(name, type);
        owner = _owner;
    }

    public Creature getOwner() {
        return owner;
    }

    public void action(ObjectDisplayGrid displayGrid) {
    }
}
