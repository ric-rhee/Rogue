package game;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

import game.parserObjects.Action;
import game.parserObjects.Armor;
import game.parserObjects.CreatureAction;
import game.parserObjects.Creature;
import game.parserObjects.Displayable;
import game.parserObjects.DisplayXMLHandler;
import game.parserObjects.Dungeon;
import game.parserObjects.ItemAction;
import game.parserObjects.Item;
import game.parserObjects.Magic;
import game.parserObjects.Monster;
import game.parserObjects.Passage;
import game.parserObjects.Player;
import game.parserObjects.Room;
import game.parserObjects.Scroll;
import game.parserObjects.Structure;
import game.parserObjects.Sword;

public class Rogue implements Runnable {

    private static final int DEBUG = 0;
    private boolean isRunning;
    public static final int FRAMESPERSECOND = 60;
    public static final int TIMEPERLOOP = 1000000000 / FRAMESPERSECOND;
    private static ObjectDisplayGrid displayGrid = null;
    private Thread keyStrokePrinter;
    private static Dungeon dungeon;
    private static int width;
    private static int topHeight;
    private static int gameHeight;
    private static int bottomHeight;
    private static DisplayXMLHandler handler;

    public Rogue(int width, int topHeight, int gameHeight, int bottomHeight) {
        displayGrid = new ObjectDisplayGrid(width, topHeight + gameHeight + bottomHeight);
    }

    @Override
    public void run() {
        for(Displayable displayable : dungeon.getDisplayables()) {
            if(displayable.getVisible() == 1)
                displayable.drawObject(displayGrid, dungeon.getTopHeight());
        }
    }

    private static void setDimensions() {
        width = dungeon.getWidth();
        topHeight = dungeon.getTopHeight();
        gameHeight = dungeon.getGameHeight();
        bottomHeight = dungeon.getBottomHeight();
    }

    public static void main(String[] args) throws Exception {
        // Read XML file
        String fileName = null;
        switch (args.length) { // check if filename passed in
            case 1:
                fileName = "xmlFiles/" + args[0]; // open file
                break;
            default:
                System.out.println("java Test <xmlfilename>"); // did not pass filename
                return;
        }

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance(); // Create a saxParserFactory, that will allow use to create a parser
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            handler = new DisplayXMLHandler();
            saxParser.parse(new File(fileName), handler); // This will parse the xml file given by fileName
        }
        catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace(System.out);
        }

        // Create Display
        dungeon = handler.getDungeon();
        setDimensions();
        Rogue rogue = new Rogue(width, topHeight, gameHeight, bottomHeight);
        displayGrid = new ObjectDisplayGrid(width, gameHeight);
        displayGrid.initializeDisplay();
        Thread rogueThread = new Thread(rogue);
        rogueThread.start();

        rogue.keyStrokePrinter = new Thread(new KeyStrokePrinter(displayGrid));
        rogue.keyStrokePrinter.start();

        rogueThread.join();
        rogue.keyStrokePrinter.join();
    }
}
