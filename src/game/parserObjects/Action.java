package game.parserObjects;

public abstract class Action {

    private String name;
    private String type;
    private String message;
    private int actionIntValue;
    private char actionCharValue;

    public Action(String _name, String _type) {
        setName(_name);
        setType(_type);
    }

    public void setName(String _name) {
        name = _name;
    }

    public void setType(String _type) {
        type = _type;
    }

    public void setMessage(String _message) {
        message = _message;
    }

    public void setActionIntValue(int _actionIntValue) {
        actionIntValue = _actionIntValue;
    }

    public void setActionCharValue(char _actionCharValue) {
        actionCharValue = _actionCharValue;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public int getActionIntValue() {
        return actionIntValue;
    }

    public char getActionCharValue() {
        return actionCharValue;
    }
}
