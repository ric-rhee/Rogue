package game;
import asciiPanel.AsciiPanel;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Random;
import game.parserObjects.Displayable;
import game.parserObjects.Creature;
import game.parserObjects.Player;
import game.parserObjects.Monster;
import game.parserObjects.CreatureAction;
import game.parserObjects.Item;
import game.parserObjects.ItemAction;
import game.parserObjects.ChangeDisplayType;
import game.parserObjects.Remove;
import game.parserObjects.Teleport;
import game.parserObjects.UpdateDisplay;
import game.parserObjects.YouWin;
import game.parserObjects.DropPack;
import game.parserObjects.EmptyPack;
import game.parserObjects.EndGame;
import game.parserObjects.Scroll;

public class ObjectDisplayGrid extends JFrame implements KeyListener, InputSubject {

    private static final int DEBUG = 0;
    private static final String CLASSID = ".ObjectDisplayGrid";

    private static AsciiPanel terminal;
    private char[][] objectGrid = null;
    private Stack<Displayable>[][] objectStacks = null;

    private List<InputObserver> inputObservers = null;

    private static int height;
    private int topHeight;
    private static int width;
    private static int playerPosX;
    private static int playerPosY;
    private static int score;
    private static int count;
    private static char pressed;
    private boolean hallucinate = false;
    private static int hallucinateCount;
    private static int hallucinateStep;

    private static String infoStr = "";
    private static char[] displayableChars = new char[]{'X', '.', '#', '+', 'T', 'H', 'S', '@', ')', ']', '?'};

    public ObjectDisplayGrid(int _width, int _height, int _topHeight) {
        width = _width;
        height = _height;
        topHeight = _topHeight;

        score = 0;

        terminal = new AsciiPanel(width, height);

        objectGrid = new char[width][height];
        objectStacks = (Stack<Displayable>[][]) new Stack[width][height];
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++)
                objectStacks[i][j] = new Stack<Displayable>();
        }

        super.add(terminal);
        super.setSize(width * 9, height * 16);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // super.repaint();
        // terminal.repaint();
        super.setVisible(true);
        terminal.setVisible(true);
        super.addKeyListener(this);
        inputObservers = new ArrayList<>();
        super.repaint();
    }

    public void setPlayerPos(int x, int y) {
        playerPosX = x;
        playerPosY = y;
    }

    public void setHallucinate() {
        hallucinate = true;
    }

    public void setHallucinateCount(int n) {
        hallucinateCount = n;
    }

    public int getPlayerPosX() {
        return playerPosX;
    }

    public int getPlayerPosY() {
        return playerPosY;
    }

    public Stack<Displayable>[][] getObjectStacks() {
        return objectStacks;
    }

    public char getChar() {
        return pressed;
    }

    public int getDisplayWidth() {
        return width;
    }

    public int getDisplayHeight() {
        return height;
    }

    public int getTopHeight() {
        return topHeight;
    }

    public static AsciiPanel getTerminal() {
        return terminal;
    }

    @Override
    public void registerInputObserver(InputObserver observer) {
        if (DEBUG > 0) {
            System.out.println(CLASSID + ".registerInputObserver " + observer.toString());
        }
        inputObservers.add(observer);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (DEBUG > 0) {
            System.out.println(CLASSID + ".keyTyped entered" + e.toString());
        }
        KeyEvent keypress = (KeyEvent) e;
        notifyInputObservers(keypress.getKeyChar());
        pressed = keypress.getKeyChar();
    }

    private void notifyInputObservers(char ch) {
        for (InputObserver observer : inputObservers) {
            observer.observerUpdate(ch);
            if (DEBUG > 0) {
                System.out.println(CLASSID + ".notifyInputObserver " + ch);
            }
        }
    }

    // we have to override, but we don't use this
    @Override
    public void keyPressed(KeyEvent even) {
    }

    // we have to override, but we don't use this
    @Override
    public void keyReleased(KeyEvent e) {
    }

    public final void initializeDisplay() {
        char ch = '.';
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                addObjectToDisplay(ch, i, j);
            }
        }
        terminal.repaint();
    }

    public void fireUp() {
        if (terminal.requestFocusInWindow()) {
            System.out.println(CLASSID + ".ObjectDisplayGrid(...) requestFocusInWindow Succeeded");
        } else {
            System.out.println(CLASSID + ".ObjectDisplayGrid(...) requestFocusInWindow FAILED");
        }
    }

    public void addObjectToDisplay(char ch, int x, int y) {
        if ((0 <= x) && (x < objectGrid.length)) {
            if ((0 <= y) && (y < objectGrid[0].length)) {
                objectGrid[x][y] = ch;
                terminal.write(ch, x, y);
                terminal.repaint();
            }
        }
        terminal.repaint();
    }

    public void pushObjectToGrid(Displayable displayable, int x, int y) {
        objectStacks[x][y].push(displayable);
        if(objectStacks[x][y].peek().getVisible() == 1)
            writeToTerminal(x, y);
    }

    public Displayable popObjectFromGrid(int x, int y) {
        return objectStacks[x][y].pop();
    }

    public Displayable peekObjectInGrid(int x, int y) {
        return objectStacks[x][y].peek();
    }

    public void writeToTerminal(int x, int y) {
        if(objectStacks[x][y].isEmpty() == false) {
            char ch = objectStacks[x][y].peek().getType();
            terminal.write(ch, x, y);
            terminal.repaint();
        }
    }

    public void playerMovement(char ch) {

        int changeX = 0;
        int changeY = 0;

        if(ch == 'h')
            changeX = -1;
        else if(ch == 'j')
            changeY = 1;
        else if(ch == 'k')
            changeY = -1;
        else if(ch == 'l')
            changeX = 1;

        if(objectStacks[playerPosX+changeX][playerPosY+changeY].isEmpty() == false) {
            char type = objectStacks[playerPosX+changeX][playerPosY+changeY].peek().getType();
            if(type != 'X') { // if not a wall
                if((type == 'T' | type == 'S' | type == 'H') & objectStacks[playerPosX+changeX][playerPosY+changeY].peek().getHP() > 0) { // if a monster alive
                    Random rand = new Random();
                    Monster monster = (Monster) objectStacks[playerPosX+changeX][playerPosY+changeY].peek();
                    Player player = (Player) objectStacks[playerPosX][playerPosY].peek();
                    int weaponValue = 0;
                    int armorValue = 0;
                    int monsterMaxHit = monster.getMaxhit();
                    int playerMaxHit = player.getMaxhit();
                    int monsterHP = monster.getHP();
                    int playerHP = player.getHP();
                    if(player.getSword() != null)
                        weaponValue = player.getSword().getItemIntValue();
                    int playerDamage = rand.nextInt(playerMaxHit+1) + weaponValue;
                    monsterHP -= playerDamage;
                    monster.setHP(monsterHP);
                    if(creatureActionEvent(monster, "hit") == 1) { // monster deals damage
                        int monsterDamage = rand.nextInt(monsterMaxHit+1);
                        if(player.getArmor() != null)
                            armorValue = player.getArmor().getItemIntValue();
                        monsterDamage -= armorValue;
                        if(monsterDamage < 0)
                            monsterDamage = 0;
                        playerHP -= monsterDamage;
                        player.setHP(playerHP);
                        infoStr = String.format("Player deals %d damage to %s, Player receives %d damage", playerDamage, monster.getName(), monsterDamage);
                        creatureActionEvent(player, "hit");
                    } else {
                        infoStr = String.format("Player deals %d damage to %s", playerDamage, monster.getName());
                    }
                    if(monster.getHP() < 1) {
                        creatureActionEvent(monster, "death");
                        writeToTerminal(monster.getPosX(), monster.getPosY());
                    }
                    displayInfo();
                    displayHPScore();
                    if(checkPlayerHP() == false) {
                        creatureActionEvent(player, "death");
                        endGame();
                    }
                } else { // movement otherwise
                    Displayable poppedPlayer = this.popObjectFromGrid(playerPosX, playerPosY);
                    this.pushObjectToGrid(poppedPlayer, playerPosX+changeX, playerPosY+changeY);
                    this.setPlayerPos(playerPosX+changeX, playerPosY+changeY);
                    count++;
                    if(hallucinate) {
                        if(hallucinateStep++ == hallucinateCount) { // hallucinate over
                            hallucinateStep = 0;
                            hallucinate = false;
                            fixGrid();
                        } else randomizeGrid(); // hallucinating
                    }
                    if(count % poppedPlayer.getHPMoves() == 0)
                        poppedPlayer.setHP(poppedPlayer.getHP() + 1);
                    displayHPScore();
                }
            }
        }
        writeToTerminal(playerPosX-changeX, playerPosY-changeY);
        writeToTerminal(playerPosX, playerPosY);
    }

    public void pickUpItem() {
        Displayable poppedPlayer = this.popObjectFromGrid(playerPosX, playerPosY);
        Displayable belowPlayer = this.peekObjectInGrid(playerPosX, playerPosY);
        char type = belowPlayer.getType();
        if(type == ')' | type == ']' | type == '?') { // if below player is an item
            Displayable item = popObjectFromGrid(playerPosX, playerPosY);
            poppedPlayer.pickUpItem(item);
        }
        pushObjectToGrid(poppedPlayer, playerPosX, playerPosY);
    }

    public boolean dropItem(int i) {
        Player player = (Player) popObjectFromGrid(playerPosX, playerPosY);
        ArrayList<Displayable> inventory = player.getInventory();
        if(i > inventory.size()) { // input check
            pushObjectToGrid(player, playerPosX, playerPosY);
            infoStr = "INVALID DROP ITEM VALUE";
            displayInfo();
            return false;
        }
        Item item = (Item) inventory.remove(i-1);
        item.setOwner(null);
        item.setVisible(1);
        if(item.getType() == ')')
            player.setWeapon(null);
        if(item.getType() == ']')
            player.setArmor(null);
        pushObjectToGrid((Displayable) item, playerPosX, playerPosY);
        pushObjectToGrid(player, playerPosX, playerPosY);
        return true;
    }

    public boolean equipSword(int num) {
        Player player = (Player) peekObjectInGrid(playerPosX, playerPosY);
        ArrayList<Displayable> inventory = player.getInventory();
        if(num > inventory.size() | inventory.get(num-1).getType() != ')') {
            infoStr = "OUT OF BOUNDS OR NOT A SWORD";
            displayInfo();
            return false;
        }
        if(player.getSword() != null)
            player.getSword().setOwner(null);
        player.setWeapon((Item) inventory.get(num-1));
        player.getSword().setOwner(player);
        infoStr = String.format("EQUIPPED %s", inventory.get(num-1).getName());
        displayInfo();
        return true;
    }

    public boolean equipArmor(int num) {
        Player player = (Player) peekObjectInGrid(playerPosX, playerPosY);
        ArrayList<Displayable> inventory = player.getInventory();
        if(num > inventory.size() | inventory.get(num-1).getType() != ']') {
            infoStr = "OUT OF BOUNDS OR NOT AN ARMOR";
            displayInfo();
            return false;
        }
        if(player.getArmor() != null)
            player.getArmor().setOwner(null);
        player.setArmor((Item) inventory.get(num-1));
        player.getArmor().setOwner(player);
        infoStr = String.format("EQUIPPED %s", inventory.get(num-1).getName());
        displayInfo();
        return true;
    }

    public void unequipArmor() {
        Player player = (Player) peekObjectInGrid(playerPosX, playerPosY);
        if(player.getArmor() == null)
            infoStr = "NO ARMOR EQUIPPED";
        else {
            infoStr = "ARMOR UNEQUIPPED";
            player.getArmor().setOwner(null);
            player.setArmor(null);
        }
        displayInfo();
    }

    public boolean readScroll(int num) {
        Player player = (Player) peekObjectInGrid(playerPosX, playerPosY);
        ArrayList<Displayable> inventory = player.getInventory();
        if(num > inventory.size() | inventory.get(num-1).getType() != '?') {
            infoStr = "OUT OF BOUNDS OR NOT A SCROLL";
            displayInfo();
            return false;
        }
        Scroll scroll = (Scroll) inventory.get(num-1);
        for(ItemAction itemAction : scroll.getItemActions())
            itemAction.action(this);

        return true;
    }

    public void displayItems() {
        Player player = (Player) peekObjectInGrid(playerPosX, playerPosY);
        ArrayList<Displayable> inventory = player.getInventory();
        String str = "Pack: ";
        for(int i = 0; i < inventory.size(); i++) {
            int n = i+1;
            str += "(" + n + ")" + inventory.get(i).getName() + " ";
            Item item = (Item) inventory.get(i);
            if(item.getOwner() != null) {
                if(item.getType() == ')')
                    str += "(w) ";
                if(item.getType() == ']')
                    str += "(a) ";
            }
        }
        putStrOnDisplay(str, height - 3);
    }

    public boolean checkPlayerHP() {
        if(objectStacks[playerPosX][playerPosY].peek().getHP() <= 0)
            return false;
        return true;
    }

    public void endGame() {
        Player player = (Player) peekObjectInGrid(playerPosX, playerPosY);
        for(CreatureAction action: player.getCreatureActions()){
            if(action.getType() == "EndGame"){
                infoStr = action.getMessage();
            }
        }
        displayInfo();
    }

    public void displayCommands() {
        infoStr = "h,l,k,j,i,?,H,c,d,p,r,t,w,E,0-9. H <cmd> for more info";
        displayInfo();
    }

    public boolean displayDetail(char input) {
        boolean state = false;
        switch (input) {
            case 'h': infoStr = "Move player left"; state = true; break;
            case 'j': infoStr = "Move player down"; state = true; break;
            case 'k': infoStr = "Move player up"; state = true; break;
            case 'l': infoStr = "Move player right"; state = true; break;
            case 'i': infoStr = "Show items in pack"; state = true; break;
            case '?': infoStr = "Show available commands"; state = true; break;
            case 'H': infoStr = "Show detailed description of <next input character>"; state = true; break;
            case 'c': infoStr = "Take off/change armor"; state = true; break;
            case 'd': infoStr = "Drop <item number> item from pack"; state = true; break;
            case 'p': infoStr = "Pick up item under player and put into pack"; state = true; break;
            case 'r': infoStr = "Read the scroll which is item number <item number> in pack"; state = true; break;
            case 't': infoStr = "Take out weapon from pack"; state = true; break;
            case 'w': infoStr = "Equip armor <item number> from pack"; state = true; break;
            case 'E': infoStr = "If <next input character> is 'Y' or 'y', end the game"; state = true; break;
            default: infoStr = "NOT A VALID COMMAND";
        }
        displayInfo();
        return state;
    }

    private void displayHPScore() {
        int playerHP = objectStacks[playerPosX][playerPosY].peek().getHP();
        String str = String.format("HP: %-3d", playerHP);
        str += String.format("Score: %d", score);
        putStrOnDisplay(str, 0);
    }

    private void displayPack() {
        String str = "Pack: ";
        putStrOnDisplay(str, height - 3);
    }

    private void displayInfo() {
        String str = "Info: " + infoStr;
        putStrOnDisplay(str, height - 1);
    }

    public void initializeText() {
        displayHPScore();
        displayPack();
        displayInfo();
    }

    private void putStrOnDisplay(String str, int y) {
        for(int i = 0; i < str.length(); i++)
            addObjectToDisplay(str.charAt(i), i, y);
        for(int j = str.length(); j < width; j++)
            addObjectToDisplay(' ', j, y);
    }

    public void setInfoString(String string) {
        infoStr = string;
        for(int i = string.length(); i < width; i++)
            infoStr += " ";
    }

    private int creatureActionEvent(Creature creature, String eventType) {
        boolean ifTeleport = false;
        for(CreatureAction action : creature.getCreatureActions()) {
            switch(action.getName()) {
                case "ChangeDisplayedType":
                    if(action.getType().equals(eventType)) {
                        ChangeDisplayType changeDisplayType = (ChangeDisplayType) action;
                        changeDisplayType.action(this);
                    }
                    break;
                case "Remove":
                    if(action.getType().equals(eventType)) {
                        Remove remove = (Remove) action;
                        remove.action(this);
                    }
                    break;
                case "Teleport":
                    if(action.getType().equals(eventType)) {
                        Teleport teleport = (Teleport) action;
                        teleport.action(this);
                        ifTeleport = true;
                    }
                    break;
                case "UpdateDisplay":
                    if(action.getType().equals(eventType)) {
                        UpdateDisplay updateDisplay = (UpdateDisplay) action;
                        updateDisplay.action(this);
                    }
                    break;
                case "YouWin":
                    if(action.getType().equals(eventType)) {
                        YouWin youWin = (YouWin) action;
                        youWin.action(this);
                    }
                    break;
                case "DropPack":
                    if(action.getType().equals(eventType)) {
                        DropPack dropPack = (DropPack) action;
                        dropPack.action(this);
                    }
                    break;
                case "EmptyPack":
                    if(action.getType().equals(eventType)) {
                        EmptyPack emptyPack = (EmptyPack) action;
                        emptyPack.action(this);
                    }
                    break;
                case "EndGame":
                    if(action.getType().equals(eventType)) {
                        EndGame endGame = (EndGame) action;
                        endGame.action(this);
                    }
                    break;
            }
        }
        if(ifTeleport)
            return 0;
        return 1;
    }

    private void randomizeGrid() {
        Random random = new Random();
        int length = displayableChars.length;
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                if(!objectStacks[x][y].isEmpty())
                    terminal.write(displayableChars[random.nextInt(length)], x, y);
            }
        }
        terminal.repaint();
    }

    private void fixGrid() {
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                if(!objectStacks[x][y].isEmpty())
                    terminal.write(objectStacks[x][y].peek().getType(), x, y);
            }
        }
        terminal.repaint();
    }
}
