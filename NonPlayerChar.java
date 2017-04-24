
package zeitz_borkv3;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is a class of type NonPlayerChar.  It is a class that holds all 
 * the information about the Non Player Characters (NPCs) in the Zork game.
 *
 * @author Austin Portell
 * @version BorkGroupV1
 */
public class NonPlayerChar {
    
	static class NoNPCException extends Exception {}

    private int health;
    private ArrayList<Item> inventory;
    private ArrayList<Item> equipped;
    private int attack;
    private int defense;
    private boolean hasTalked;
    private ArrayList<String> messages;
    private Item wanted;
    private Item toGive;
    private String name;
    
    //Variables relating to file formatting
    public static String INVENTORY_MARKER = "Inventory:";
    public static String EQUIPPED_MARKER = "Equipped:";
    public static String MESSAGES_MARKER = "Messages:";
    public static String TRADE_MARKER = "Trades:";

    public static String LOW_LEVEL_DELIMITER = "---";
    public static String HIGH_LEVEL_DELIMITER = "===";

    public NonPlayerChar(Scanner s,Dungeon d) throws NoNPCException,
	   Dungeon.IllegalDungeonFormatException{
		   String line = s.nextLine();
		   if(line.equals(HIGH_LEVEL_DELIMITER)){
			   throw new NoNPCException();
		   }

        name = line;
	health = Integer.parseInt(s.nextLine());
	attack = Integer.parseInt(s.nextLine());
	defense = Integer.parseInt(s.nextLine());

	line = s.nextLine();
	if (line.startsWith(INVENTORY_MARKER)) {
		String itemList = line.substring(INVENTORY_MARKER.length());
		String[] itemNames = itemList.split(",");
		for (String itemName : itemNames) {
			try{
				inventory.add(d.getItem(itemName));
			} catch (Item.NoItemException e) {
				throw new Dungeon.IllegalDungeonFormatException(
					"No such item '" + itemName + "'");
			}
		}
		line = s.nextLine();
	}

	if (line.startsWith(EQUIPPED_MARKER)) {
		String itemList = line.substring(EQUIPPED_MARKER.length());
		String[] itemNames = itemList.split(",");
		for (String itemName : itemNames) {
			try{
				equipped.add(d.getItem(itemName));
			} catch (Item.NoItemException e) {
				throw new Dungeon.IllegalDungeonFormatException(
						"No such item '" + itemName + "'");
			}
		}
		line = s.nextLine();
	}

	if (line.startsWith(MESSAGES_MARKER)) {
		String messagePair = line.substring(MESSAGES_MARKER.length());
		String[] messageArray = messagePair.split(",");
		messages = new ArrayList<String>();
		messages.add(messageArray[0]);
		messages.add(messageArray[1]);
		line = s.nextLine();
	}

	if(line.startsWith(TRADE_MARKER)) {
		String itemList = line.substring(TRADE_MARKER.length());
		String[] itemNames = itemList.split(",");
		try{
		wanted = d.getItem(itemNames[0]);
		toGive = d.getItem(itemNames[1]);
		} catch (Item.NoItemException e) {
			throw new Dungeon.IllegalDungeonFormatException(
					"No such item '" + itemList + "'");
		}
		line = s.nextLine();
	}
	

			

    }
    
    private void init(){
        this.hasTalked = false;
    }
     /**
     * A method to get the message of what the NPC is saying to the player.
     * 
     * @param keyMess
     * @return The String message to the player from the NPC.
     */ 
    public String getMessage(){
        String message;
        if(hasTalked){
            message = this.messages.get(1);
        } else {
            message = this.messages.get(0);
        }
        hasTalked = true;
        return message;
    }
    
     /**
     * A method to get the strength of the NPCs attack.
     * 
     * @return The int of how strong the NPCs attack is.
     */
    public int getAttack(){
        return this.attack;
    }
        
     /**
     * A method to get the defense of the NPC.
     * 
     * @return The int of how high the defense of the NPC is.
     */
    public int getDefense(){
        return this.defense;
    }
        
     /**
     * A method to see what Item the NPC wants.
     * 
     * @return The Item object that the NPC wants.
     */
    public String getWanted() {
        return this.wanted.getPrimaryName();
    }
        
     /**
     * A method to see what Item the NPC will give the player.
     * 
     * @return The Item object that the NPC will give.
     */
    public Item getToGive(){
        return this.toGive;
    }
        
     /**
     * A method to see what Items are currently being held by the NPC.
     * 
     * @return The ArrayList of items in the NPCs inventory.
     */
    public String getEquipped(){
        for(Item i : this.equipped){
            return i.getPrimaryName();
        }
        return null;
    }
    
    public int getHealth(){
        return this.health;
    }
    
    public String getName(){
        return this.name;
    }
    
    void woundHealth(int healthAdded) {
        this.health -= healthAdded;
    }
    
}
