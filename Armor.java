
package groupborkv1;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * This is a class of type Armor that is an extension of the Item class. It
 * holds all of the necessary information about the armor in the Zork game.
 * 
 * @author Austin Portell
 * @version BorkGroupV1
 */
public class Armor extends Item {

    private int defense;
    private boolean equippable;

    /**
     * The constructor for the Armor class.
     * Called if an Armor item is read into the game.
     * 
     * @param Item being read in as an Armor item.
     */
    public Armor() {

    }

    /**
     * A method to tell whether the user can equip the armor or not.
     * 
     * @return A boolean that's true if the armor is equippable, 
     * false if not.
     */
    public boolean isEquippable() {

    }

}
