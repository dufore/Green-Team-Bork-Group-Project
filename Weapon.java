package zeitz_borkv3;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

/**
 * This is a class of type Weapon that is an extension of the Item class. It
 * holds all of the necessary information about the weapons in the Zork game.
 *
 * @author Austin Portell
 * @version BorkGroupV1
 */
public class Weapon extends Item {

    private int attack;
    private Hashtable<String, Integer> moves;
    private boolean equippable;

    /**
     * The constructor for the Weapon class.
     *
     */
    public Weapon() {

    }

    /**
     * A method to display to the user what moves a certain weapon has.
     * 
     * @return An ArrayList of different moves a weapon has.
     */
    public ArrayList getMoves() {

    }

    /**
     * A method to tell whether the user can equip the weapon or not.
     * 
     * @return A boolean that's true if the weapon is equippable, 
     * false if not.
     */
    public boolean isEquippable() {

    }


}
