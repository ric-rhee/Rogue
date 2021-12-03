package src;

public class Creature extends Displayable {
    private int hp;
    private int hpMoves;
    private CreatureAction charAction;
    private String hitAction;

    public Creature() {
        super();
    }

    public void setHp(int h) {
        hp = h;
    }

    public void setHpMoves(int hpm) {
        hpMoves = hpm;
    }

    public void setDeathAction(CreatureAction da) {
        charAction = da;
    }

    public void setHitAction(String ha) {
        hitAction = ha;
    }
}
