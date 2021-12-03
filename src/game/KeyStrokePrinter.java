package game;
import java.util.Queue;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class KeyStrokePrinter implements InputObserver, Runnable {

    private static int DEBUG = 1;
    private static String CLASSID = "KeyStrokePrinter";
    private static Queue<Character> inputQueue = null;
    private ObjectDisplayGrid displayGrid;

    public KeyStrokePrinter(ObjectDisplayGrid grid) {
        inputQueue = new ConcurrentLinkedQueue<>();
        displayGrid = grid;
    }

    @Override
    public void observerUpdate(char ch) {
        if (DEBUG > 0) {
            System.out.println(CLASSID + ".observerUpdate receiving character " + ch);
        }
        inputQueue.add(ch);
    }

    private void rest() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private boolean processInput() {

        char ch;
        boolean processing = true;

        while (processing) {
            if (inputQueue.peek() == null) {
                processing = false;
            } else {
                ch = inputQueue.poll();
                if (DEBUG > 1) {
                    System.out.println(CLASSID + ".processInput peek is " + ch);
                }

                if (ch == 'X') {
                    System.out.println("got an X, ending input checking");
                    return false;
                } else {
                    System.out.println("character " + ch + " entered on the keyboard");
                }

                if(displayGrid.checkPlayerHP() == false)
                    return false;
                switch (ch) {
                    case 'h': // player moves left
                    case 'j': // player moves down
                    case 'k': // player moves up
                    case 'l': // player moves right
                        displayGrid.playerMovement(ch);
                        break;
                    case 'p': // pick up item from floor
                        displayGrid.pickUpItem();
                        break;
                    case 'd': // drop items on the floor
                        while(true) {
                            while(inputQueue.peek() == null); // wait for another input
                            char input = inputQueue.poll();
                            if(Character.isDigit(input)) {
                                int inputInt = Integer.parseInt(String.valueOf(input));
                                if(inputInt > 0 & inputInt < 10) {
                                    if(displayGrid.dropItem(inputInt) == true)
                                        break;
                                }
                            }
                        }
                        break;
                    case 't': // equip weapon
                        while(true) {
                            while(inputQueue.peek() == null); // wait for another input
                            char input = inputQueue.poll();
                            if(Character.isDigit(input)) {
                                int inputInt = Integer.parseInt(String.valueOf(input));
                                if(inputInt > 0 & inputInt < 10) {
                                    if(displayGrid.equipSword(inputInt) == true)
                                        break;
                                }
                            }
                        }
                        break;
                    case 'w': // equip armor
                        while(true) {
                            while(inputQueue.peek() == null); // wait for another input
                            char input = inputQueue.poll();
                            if(Character.isDigit(input)) {
                                int inputInt = Integer.parseInt(String.valueOf(input));
                                if(inputInt > 0 & inputInt < 10) {
                                    if(displayGrid.equipArmor(inputInt) == true)
                                        break;
                                }
                            }
                        }
                        break;
                    case 'c': // unequip armor
                        displayGrid.unequipArmor();
                        break;
                    case 'r': // read scroll
                        while(true) {
                            while(inputQueue.peek() == null); // wait for another input
                            char input = inputQueue.poll();
                            if(Character.isDigit(input)) {
                                int inputInt = Integer.parseInt(String.valueOf(input));
                                if(inputInt > 0 & inputInt < 10) {
                                    if(displayGrid.readScroll(inputInt) == true)
                                        break;
                                }
                            }
                        }
                        break;
                    case 'i': // show inventory
                        displayGrid.displayItems();
                        break;
                    case '?': // show different commands
                        displayGrid.displayCommands();
                        break;
                    case 'H': // show detailed command description
                        while(true) {
                            while(inputQueue.peek() == null); // wait for another input
                            char input = inputQueue.poll();
                            if(displayGrid.displayDetail(input) == true)
                                break;
                        }
                        break;
                    case 'E': // end the game
                        while(true) {
                            while(inputQueue.peek() == null); // wait for another input
                            char input = inputQueue.poll();
                            if(input == 'Y' | input == 'y')
                                displayGrid.endGame();
                                break;
                        }
                        return false;
                    default:
                        System.out.println("INVALID KEY INPUT");
                }
            }
        }
        return true;
    }

    @Override
    public void run() {
        displayGrid.registerInputObserver(this);
        boolean working = true;
        while (working) {
            rest();
            working = (processInput());
        }
    }
}
