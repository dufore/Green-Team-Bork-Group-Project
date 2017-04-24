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
    private int points;
    private String name;

    /**
     * The constructor for the Weapon class.
     *
     */
    public Weapon(Scanner s) throws NoItemException,
            Dungeon.IllegalDungeonFormatException {

        super(s);
        name = s.next();
        points = Integer.valueOf(s.next());
        attack = Integer.valueOf(s.next());

        while (!s.nextLine().equals(Dungeon.SECOND_LEVEL_DELIM)
                && !s.nextLine().equals(Dungeon.TOP_LEVEL_DELIM)) {
            if (s.nextLine().equals(Dungeon.TOP_LEVEL_DELIM)) {
                throw new Dungeon.IllegalDungeonFormatException("No '"
                        + Dungeon.SECOND_LEVEL_DELIM + "' after item.");
            }

            this.moves.put(s.next(), attack);
            s.nextLine();

        }

    }

    /**
     * A method to display to the user what moves a certain weapon has.
     *
     * @return An ArrayList of different moves a weapon has.
     */
    public ArrayList getMoves() {
        ArrayList<String> moves = new ArrayList<>();
        for (String key : this.moves.keySet()) {
            moves.add(key);
        }

        return moves;
    }

    /**
     * A method to tell whether the user can equip the weapon or not.
     *
     * @return A boolean that's true if the weapon is equippable, false if not.
     */
    public boolean isEquippable() {
        equippable = true;
        return equippable;
    }

    /**
     * A method to retrieve the attack that a weapon does
     *
     * @return the attack of the given weapon
     */
    public int getAttack() {
        return this.attack;
    }

}
