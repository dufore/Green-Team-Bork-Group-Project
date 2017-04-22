
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
    
    
    public NonPlayerChar(Scanner S){
        
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
    
}
