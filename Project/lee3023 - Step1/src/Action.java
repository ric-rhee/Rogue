package src;

public class Action extends Displayable {
    private String msg;
    private int v;
    private char c;

    public void setMessage(String _msg) {
        msg = _msg;
    }

    public void setIntValue(int _v) {
        v = _v;
    }

    public void setCharValue(char _c) {
        c = _c;
    }
}
