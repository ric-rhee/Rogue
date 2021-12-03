package src;
import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DisplayXMLHandler extends DefaultHandler {
    // unneccessary created to declare a DEBUG flag to control debug print statements
    // and to allow the class to be easily printed out
    private static final int DEBUG = 1;
    private static final String CLASSID = "DisplayXMLHandler";

    private StringBuilder data = null; // the variables that contains information found while parsing the xml file
    private ArrayList<Displayable> displayables = new ArrayList<Displayable>(); // add references to objects to this array so that it has a list of all specified displays

    // list of current object
    private Displayable displayableBeingParsed = null;
    private Dungeon dungeonBeingParsed = null;

    static private int creatureActionCount = 0;

    // bX
    private boolean bVisible = false;
    private boolean bPosX = false;
    private boolean bPosY = false;
    private boolean bWidth = false;
    private boolean bHeight = false;
    private boolean bHp = false;
    private boolean bHpMoves = false;
    private boolean bType = false;
    private boolean bMaxhit = false;
    private boolean bActionMessage = false;
    private boolean bActionIntValue = false;
    private boolean bActionCharValue = false;
    private boolean bItemIntValue = false;

    private boolean bPlayer = false;
    private boolean bMonster = false;

    public ArrayList<Displayable> getDisplayables() {
        return displayables;
    }

    public DisplayXMLHandler() {
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
            Dungeon dungeon = new Dungeon(dungeonName, width, gameHeight);
            dungeonBeingParsed = dungeon;
        } else if (qName.equalsIgnoreCase("Armor")) {
            String armorName = attributes.getValue("name");
            int armorRoom = Integer.parseInt(attributes.getValue("room"));
            int armorSerial = Integer.parseInt(attributes.getValue("serial"));
            Armor armor = new Armor(armorName, armorRoom, armorSerial);
            displayables.add(armor);
            displayableBeingParsed = armor;
        } else if (qName.equalsIgnoreCase("CreatureAction")) {
            String creatureActionName = attributes.getValue("name");
            String creatureActionType = attributes.getValue("type");
            creatureActionCount++;
        } else if (qName.equalsIgnoreCase("ItemAction")) {
            String itemActionName = attributes.getValue("name");
            String itemActionType = attributes.getValue("type");
        } else if (qName.equalsIgnoreCase("Monster")) {
            String monsterName = attributes.getValue("name");
            int monsterRoom = Integer.parseInt(attributes.getValue("room"));
            int monsterSerial = Integer.parseInt(attributes.getValue("serial"));
            Monster monster = new Monster(monsterName, monsterRoom, monsterSerial);
            displayables.add(monster);
            displayableBeingParsed = monster;
        } else if (qName.equalsIgnoreCase("Passage")) {
            String room1 = attributes.getValue("room1");
            String room2 = attributes.getValue("room2");
        } else if (qName.equalsIgnoreCase("Passages")) {
            ;
        } else if (qName.equalsIgnoreCase("Player")) {
            String playerName = attributes.getValue("name");
            int playerRoom = Integer.parseInt(attributes.getValue("room"));
            int playerSerial = Integer.parseInt(attributes.getValue("serial"));
            Player player = new Player(playerName, playerRoom, playerSerial);
            displayables.add(player);
            displayableBeingParsed = player;
        } else if (qName.equalsIgnoreCase("Room")) {
            int room = Integer.parseInt(attributes.getValue("room"));
        } else if (qName.equalsIgnoreCase("Rooms")) {
            displayables = new ArrayList<Displayable>();
        } else if (qName.equalsIgnoreCase("Scroll")) {
            String scrollName = attributes.getValue("name");
            int scrollRoom = Integer.parseInt(attributes.getValue("room"));
            int scrollSerial = Integer.parseInt(attributes.getValue("serial"));
            Scroll scroll = new Scroll(scrollName, scrollRoom, scrollSerial);
            displayables.add(scroll);
            displayableBeingParsed = scroll;
        } else if (qName.equalsIgnoreCase("Sword")) {
            String swordName = attributes.getValue("name");
            int swordRoom = Integer.parseInt(attributes.getValue("room"));
            int swordSerial = Integer.parseInt(attributes.getValue("serial"));
            Sword sword = new Sword(swordName, swordRoom, swordSerial);
            displayables.add(sword);
            displayableBeingParsed = sword;
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
            bHp = true;
        } else if (qName.equalsIgnoreCase("hpMoves")) {
            bHpMoves = true;
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
        } else if (qName.equalsIgnoreCase("Player")) {
            bPlayer = true;
        } else if (qName.equalsIgnoreCase("Monster")) {
            bMonster = true;
        } else {
            System.out.println("Unknown qname: " + qName);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        Displayable displayable;

        if (bVisible) {
            displayable = (Displayable) displayableBeingParsed;
            bVisible = false;
        } else if (bPosX) {
            bPosX = false;
        } else if (bPosY) {
            bPosY = false;
        } else if (bWidth) {
            bWidth = false;
        } else if (bHeight) {
            bHeight = false;
        } else if (bHp) {
            bHp = false;
        } else if (bHpMoves) {
            bHpMoves = false;
        } else if (bType) {
            bType = false;
        } else if (bMaxhit) {
            bMaxhit = false;
        } else if (bActionMessage) {
            bActionMessage = false;
        } else if (bActionIntValue) {
            bActionIntValue = false;
        } else if (bActionCharValue) {
            bActionCharValue = false;
        } else if (bItemIntValue) {
            bItemIntValue = false;
        } else if (bPlayer) {
            bPlayer = false;
            ///System.out.println(creatureActionCount);
            creatureActionCount = 0;
        } else if (bMonster) {
            bMonster = false;
            //System.out.println(creatureActionCount);
            creatureActionCount = 0;
        }
    }

    @Override
    public String toString() {
        String str = "TESTING ACTION COUNT\n";
        str += creatureActionCount;
        return str;
    }
}
