package game.parserObjects;
import game.ObjectDisplayGrid;

public class ItemAction extends Action {
    private Item owner;

    public ItemAction(String name, String type, Item _owner) {
        super(name, type);
        owner = _owner;
    }

    public void action(ObjectDisplayGrid displayGrid) {
        if(getActionCharValue() == 0)
            hallucinate(displayGrid);
        else
            blesscurse(displayGrid, getActionCharValue());
    }

    private void hallucinate(ObjectDisplayGrid displayGrid) {
        displayGrid.setHallucinate();
        displayGrid.setHallucinateCount(getActionIntValue());
    }

    private void blesscurse(ObjectDisplayGrid displayGrid, char which) {
        Player player = (Player) displayGrid.peekObjectInGrid(displayGrid.getPlayerPosX(), displayGrid.getPlayerPosY());
        if(which == 'a') { // scroll affects armor
            if(player.getArmor() != null) { // there is armor equipped
                player.getArmor().setItemIntValue(player.getArmor().getItemIntValue()+getActionIntValue());
                if(getActionIntValue() < 0){ // is a curse
                    displayGrid.setInfoString(String.format("%s cursed! + %d", player.getArmor().getName(), getActionIntValue()));
                    displayGrid.initializeText();
                }
                else if(getActionIntValue() > 0) { // is a blessing
                    displayGrid.setInfoString(String.format("%s blessed! + %d", player.getArmor().getName(), getActionIntValue()));
                    displayGrid.initializeText();
                }
                Item item = (Item) player.getArmor();
                if(item.getItemIntValue() > 0)
                    item.setName(String.format("+%d Armor", item.getItemIntValue()));
                else
                    item.setName(String.format("%d Armor", item.getItemIntValue()));
            }
            else { // there is no armor equipped
                displayGrid.setInfoString("scroll of blessing does nothing because armor not being used");
                displayGrid.initializeText();
            }
        }
        else if(which == 'w') { // scroll affects weapon
            if(player.getSword() != null) { // there is sword equipped
                player.getSword().setItemIntValue(player.getSword().getItemIntValue()+getActionIntValue());
                if(getActionIntValue() < 0){ // is a curse
                    displayGrid.setInfoString(String.format("%s cursed! + %d", player.getSword().getName(), getActionIntValue()));
                    displayGrid.initializeText();
                }
                else if(getActionIntValue() > 0) { // is a blessing
                    displayGrid.setInfoString(String.format("%s blessed! + %d", player.getSword().getName(), getActionIntValue()));
                    displayGrid.initializeText();
                }
                Item item = (Item) player.getSword();
                if(item.getItemIntValue() > 0)
                    item.setName(String.format("+%d Armor", item.getItemIntValue()));
                else
                    item.setName(String.format("%d Armor", item.getItemIntValue()));
            }
            else { // there is no sword equipped
                displayGrid.setInfoString("scroll of blessing does nothing because sword not being used");
                displayGrid.initializeText();
            }
        }
    }

    public Item getOwner() {
        return owner;
    }
}
