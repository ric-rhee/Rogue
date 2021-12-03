package game.parserObjects;
import java.util.ArrayList;
import java.util.Stack;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DisplayXMLHandler extends DefaultHandler {

    // unneccessary created to declare a DEBUG flag to control debug print statements
    // and to allow the class to be easily printed out
    private static final int DEBUG = 1;
    private static final String CLASSID = "DisplayXMLHandler";

    private StringBuilder data = null; // the variables that contains information found while parsing the xml file

    // list of current object
    private Displayable displayableBeingParsed = null;
    private Dungeon dungeonBeingParsed = null;
    private Room roomBeingParsed = null;
    private Action actionBeingParsed;

    private Stack<Displayable> stackDisplayables = new Stack<Displayable>();
    private Stack<Displayable> tempStack = new Stack<Displayable>();

    // booleans for variables
    private boolean bVisible = false;
    private boolean bPosX = false;
    private boolean bPosY = false;
    private boolean bWidth = false;
    private boolean bHeight = false;
    private boolean bHP = false;
    private boolean bHPMoves = false;
    private boolean bType = false;
    private boolean bMaxhit = false;
    private boolean bActionMessage = false;
    private boolean bActionIntValue = false;
    private boolean bActionCharValue = false;
    private boolean bItemIntValue = false;
    private boolean bCreatureAction = false;
    private boolean bItemAction = false;
    private boolean bPassage = false;
    private boolean bItem = false;
    private boolean bPlayer = false;

    public DisplayXMLHandler() {
    }

    public Dungeon getDungeon() {
        return dungeonBeingParsed;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (DEBUG > 1) {
            System.out.println(CLASSID + ".startElement qName: " + qName);
        }

        if (qName.equalsIgnoreCase("Dungeon")) {
            String dungeonName = attributes.getValue("name");
            System.out.println(dungeonName);
            int width = Integer.parseInt(attributes.getValue("width"));
            int topHeight = Integer.parseInt(attributes.getValue("topHeight"));
            int gameHeight = Integer.parseInt(attributes.getValue("gameHeight"));
            int bottomHeight = Integer.parseInt(attributes.getValue("bottomHeight"));
            Dungeon dungeon = new Dungeon(dungeonName, width, topHeight, gameHeight, bottomHeight);
            dungeonBeingParsed = dungeon;
        } else if (qName.equalsIgnoreCase("Armor")) {
            String armorName = attributes.getValue("name");
            int armorRoom = Integer.parseInt(attributes.getValue("room"));
            int armorSerial = Integer.parseInt(attributes.getValue("serial"));
            Armor armor = new Armor(armorName, armorRoom, armorSerial);
            displayableBeingParsed = armor;
            dungeonBeingParsed.addItem(armor);
            stackDisplayables.push(armor);
            bItem = true;
        } else if (qName.equalsIgnoreCase("CreatureAction")) {
            String creatureActionName = attributes.getValue("name");
            String creatureActionType = attributes.getValue("type");
            CreatureAction creatureAction = new CreatureAction(creatureActionName, creatureActionType, (Creature) stackDisplayables.peek());
            stackDisplayables.peek().addCreatureAction(creatureAction);
            actionBeingParsed = creatureAction;
            bCreatureAction = true;
        } else if (qName.equalsIgnoreCase("ItemAction")) {
            String itemActionName = attributes.getValue("name");
            String itemActionType = attributes.getValue("type");
            ItemAction itemAction = new ItemAction(itemActionName, itemActionType, (Item) stackDisplayables.peek());
            stackDisplayables.peek().addItemAction(itemAction);
            actionBeingParsed = itemAction;
            bItemAction = true;
        } else if (qName.equalsIgnoreCase("Monster")) {
            String monsterName = attributes.getValue("name");
            int monsterRoom = Integer.parseInt(attributes.getValue("room"));
            int monsterSerial = Integer.parseInt(attributes.getValue("serial"));
            Monster monster = new Monster(monsterName, monsterRoom, monsterSerial);
            displayableBeingParsed = monster;
            dungeonBeingParsed.addCreature(monster);
            stackDisplayables.push(monster);
        } else if (qName.equalsIgnoreCase("Passage")) {
            int room1 = Integer.parseInt(attributes.getValue("room1"));
            int room2 = Integer.parseInt(attributes.getValue("room2"));
            Passage passage = new Passage(room1, room2);
            displayableBeingParsed = passage;
            dungeonBeingParsed.addPassage(passage);
            stackDisplayables.push(passage);
            bPassage = true;
        } else if (qName.equalsIgnoreCase("Passages")) {
            ;
        } else if (qName.equalsIgnoreCase("Player")) {
            String playerName = attributes.getValue("name");
            int playerRoom = Integer.parseInt(attributes.getValue("room"));
            int playerSerial = Integer.parseInt(attributes.getValue("serial"));
            Player player = new Player(playerName, playerRoom, playerSerial);
            displayableBeingParsed = player;
            dungeonBeingParsed.addCreature(player);
            stackDisplayables.push(player);
            bPlayer = true;
        } else if (qName.equalsIgnoreCase("Room")) {
            int roomNumber = Integer.parseInt(attributes.getValue("room"));
            Room room = new Room(roomNumber);
            roomBeingParsed = room;
            displayableBeingParsed = room;
            dungeonBeingParsed.addRoom(room);
            stackDisplayables.push(room);
        } else if (qName.equalsIgnoreCase("Rooms")) {
            ;
        } else if (qName.equalsIgnoreCase("Scroll")) {
            String scrollName = attributes.getValue("name");
            int scrollRoom = Integer.parseInt(attributes.getValue("room"));
            int scrollSerial = Integer.parseInt(attributes.getValue("serial"));
            Scroll scroll = new Scroll(scrollName, scrollRoom, scrollSerial);
            displayableBeingParsed = scroll;
            dungeonBeingParsed.addItem(scroll);
            stackDisplayables.push(scroll);
            bItem = true;
        } else if (qName.equalsIgnoreCase("Sword")) {
            String swordName = attributes.getValue("name");
            int swordRoom = Integer.parseInt(attributes.getValue("room"));
            int swordSerial = Integer.parseInt(attributes.getValue("serial"));
            Sword sword = new Sword(swordName, swordRoom, swordSerial);
            displayableBeingParsed = sword;
            dungeonBeingParsed.addItem(sword);
            stackDisplayables.push(sword);
            bItem = true;
        } else if (qName.equalsIgnoreCase("visible")) {
            bVisible = true;
        } else if (qName.equalsIgnoreCase("posX")) {
            bPosX = true;
        } else if (qName.equalsIgnoreCase("posY")) {
            bPosY = true;
        } else if (qName.equalsIgnoreCase("width")) {
            bWidth = true;
        } else if (qName.equalsIgnoreCase("height")) {
            bHeight = true;
        } else if (qName.equalsIgnoreCase("hp")) {
            bHP = true;
        } else if (qName.equalsIgnoreCase("hpMoves")) {
            bHPMoves = true;
        } else if (qName.equalsIgnoreCase("type")) {
            bType = true;
        } else if (qName.equalsIgnoreCase("maxhit")) {
            bMaxhit = true;
        } else if (qName.equalsIgnoreCase("actionMessage")) {
            bActionMessage= true;
        } else if (qName.equalsIgnoreCase("actionIntValue")) {
            bActionIntValue = true;
        } else if (qName.equalsIgnoreCase("actionCharValue")) {
            bActionCharValue = true;
        } else if (qName.equalsIgnoreCase("itemIntValue")) {
            bItemIntValue = true;
        } else {
            System.out.println("Unknown qname: " + qName);
        }

        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        Displayable displayable;

        if (bVisible) {
            displayable = (Displayable) displayableBeingParsed;
            if(bPlayer == true && bItem == true)
                displayable.setVisible(0);
            else
                displayable.setVisible(Integer.parseInt(data.toString()));
            bVisible = false;
        } else if (bPosX) {
            int offsetX;
            displayable = (Displayable) displayableBeingParsed;
            tempStack.push(stackDisplayables.pop());
            if(stackDisplayables.isEmpty() == true) {
                if(bPassage == true) // if is passage
                    displayableBeingParsed.addPosX(Integer.parseInt(data.toString()));
                else // if is room
                    displayable.setPosX(Integer.parseInt(data.toString()));
            }
            else { // if is creature or item
                offsetX = stackDisplayables.peek().getPosX();
                displayable.setPosX(Integer.parseInt(data.toString())+offsetX);
            }
            bPosX = false;
            stackDisplayables.push(tempStack.pop());
        } else if (bPosY) {
            int offsetY;
            displayable = (Displayable) displayableBeingParsed;
            tempStack.push(stackDisplayables.pop());
            if(stackDisplayables.isEmpty() == true) {
                if(bPassage == true) // if is passage
                    displayableBeingParsed.addPosY(Integer.parseInt(data.toString()));
                else // if is room
                    displayable.setPosY(Integer.parseInt(data.toString()));
            }
            else { // if is creature or item
                offsetY = stackDisplayables.peek().getPosY();
                displayable.setPosY(Integer.parseInt(data.toString())+offsetY);
            }
            bPosY = false;
            stackDisplayables.push(tempStack.pop());
        } else if (bWidth) {
            roomBeingParsed.setWidth(Integer.parseInt(data.toString()));
            bWidth = false;
        } else if (bHeight) {
            roomBeingParsed.setHeight(Integer.parseInt(data.toString()));
            bHeight = false;
        } else if (bHP) {
            Creature creature = (Creature) displayableBeingParsed;
            creature.setHP(Integer.parseInt(data.toString()));
            bHP = false;
        } else if (bHPMoves) {
            Creature creature = (Creature) displayableBeingParsed;
            creature.setHPMoves(Integer.parseInt(data.toString()));
            bHPMoves = false;
        } else if (bType) {
            Creature creature = (Creature) displayableBeingParsed;
            creature.setType(data.toString().charAt(0));
            bType = false;
        } else if (bMaxhit) {
            Creature creature = (Creature) displayableBeingParsed;
            creature.setMaxhit(Integer.parseInt(data.toString()));
            bMaxhit = false;
        } else if (bActionMessage) {
            actionBeingParsed.setMessage(data.toString());
            bActionMessage = false;
        } else if (bActionIntValue) {
            actionBeingParsed.setActionIntValue(Integer.parseInt(data.toString()));
            bActionIntValue = false;
        } else if (bActionCharValue) {
            actionBeingParsed.setActionCharValue(data.toString().charAt(0));
            bActionCharValue = false;
        } else if (bItemIntValue) {
            Item item = (Item) displayableBeingParsed;
            item.setItemIntValue(Integer.parseInt(data.toString()));
            bItemIntValue = false;
        } else if (bCreatureAction) {
            actionBeingParsed = null;
            bCreatureAction = false;
        } else if (bItemAction) {
            actionBeingParsed = null;
            bItemAction = false;
        } else if (bPassage) {
            stackDisplayables.pop();
            bPassage = false;
        } else if (bItem) {
            bItem = false;
            stackDisplayables.pop();
        } else if (bPlayer) {
            bPlayer = false;
            stackDisplayables.pop();
        } else if (stackDisplayables.isEmpty() == false) {
            stackDisplayables.pop();
        }
    }

    @Override
    public String toString() {
        String str = "toString\n";
        return str;
    }

    public void characters(char ch[], int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
        if (DEBUG > 1) {
            System.out.println(CLASSID + ".characters: " + new String(ch, start, length));
            System.out.flush();
        }
    }
}
