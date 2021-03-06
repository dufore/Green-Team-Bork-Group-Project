package zeitz_borkv3;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

/**
 * Three methods will be added to GameState to account for the players health,
 * score, and will also let the player know if they have died and will end the
 * game.
 *
 * @author James
 */
public class GameState {

    public static class IllegalSaveFormatException extends Exception {

        public IllegalSaveFormatException(String e) {
            super(e);
        }
    }

    static String DEFAULT_SAVE_FILE = "bork_save";
    static String SAVE_FILE_EXTENSION = ".sav";
    static String SAVE_FILE_VERSION = "Bork v3.0 save data";

    static String ADVENTURER_MARKER = "Adventurer:";
    static String CURRENT_ROOM_LEADER = "Current room: ";
    static String INVENTORY_LEADER = "Inventory: ";

    private static GameState theInstance;
    private Dungeon dungeon;
    private ArrayList<Item> inventory;
    private Item[] equipped;
    private Room adventurersCurrentRoom;

    private ArrayList<NonPlayerChar> NPCs;

    private int health;
    private int score;

    static synchronized GameState instance() {
        if (theInstance == null) {
            theInstance = new GameState();
        }
        return theInstance;
    }

    private GameState() {
        inventory = new ArrayList<Item>();
        health = 100;
        score = 0;
        equipped = new Item[2];
    }

    void restore(String filename) throws FileNotFoundException,
            IllegalSaveFormatException, Dungeon.IllegalDungeonFormatException {

        Scanner s = new Scanner(new FileReader(filename));

        if (!s.nextLine().equals(SAVE_FILE_VERSION)) {
            throw new IllegalSaveFormatException("Save file not compatible.");
        }

        String dungeonFileLine = s.nextLine();

        if (!dungeonFileLine.startsWith(Dungeon.FILENAME_LEADER)) {
            throw new IllegalSaveFormatException("No '"
                    + Dungeon.FILENAME_LEADER
                    + "' after version indicator.");
        }

        dungeon = new Dungeon(dungeonFileLine.substring(
                Dungeon.FILENAME_LEADER.length()), false);
        dungeon.restoreState(s);

        s.nextLine();  // Throw away "Adventurer:".
        String currentRoomLine = s.nextLine();
        adventurersCurrentRoom = dungeon.getRoom(
                currentRoomLine.substring(CURRENT_ROOM_LEADER.length()));
        if (s.hasNext()) {
            String inventoryList = s.nextLine().substring(
                    INVENTORY_LEADER.length());
            String[] inventoryItems = inventoryList.split(",");
            for (String itemName : inventoryItems) {
                try {
                    addToInventory(dungeon.getItem(itemName));
                } catch (Item.NoItemException e) {
                    throw new IllegalSaveFormatException("No such item '"
                            + itemName + "'");
                }
            }
        }
    }

    void store() throws IOException {
        store(DEFAULT_SAVE_FILE);
    }

    void store(String saveName) throws IOException {
        String filename = saveName + SAVE_FILE_EXTENSION;
        PrintWriter w = new PrintWriter(new FileWriter(filename));
        w.println(SAVE_FILE_VERSION);
        dungeon.storeState(w);
        w.println(ADVENTURER_MARKER);
        w.println(CURRENT_ROOM_LEADER + adventurersCurrentRoom.getTitle());
        if (inventory.size() > 0) {
            w.print(INVENTORY_LEADER);
            for (int i = 0; i < inventory.size() - 1; i++) {
                w.print(inventory.get(i).getPrimaryName() + ",");
            }
            w.println(inventory.get(inventory.size() - 1).getPrimaryName());
        }
        w.close();
    }

    void initialize(Dungeon dungeon) {
        this.dungeon = dungeon;
        adventurersCurrentRoom = dungeon.getEntry();
    }

    ArrayList<String> getInventoryNames() {
        ArrayList<String> names = new ArrayList<String>();
        for (Item item : inventory) {
            names.add(item.getPrimaryName());
        }
        return names;
    }

    void addToInventory(Item item) /* throws TooHeavyException */ {
        inventory.add(item);
    }

    void removeFromInventory(Item item) {
        inventory.remove(item);
    }

    Item getItemInVicinityNamed(String name) throws Item.NoItemException {

        // First, check inventory.
        for (Item item : inventory) {
            if (item.goesBy(name)) {
                return item;
            }
        }

        // Next, check room contents.
        for (Item item : adventurersCurrentRoom.getContents()) {
            if (item.goesBy(name)) {
                return item;
            }
        }

        throw new Item.NoItemException();
    }

    Item getItemFromInventoryNamed(String name) throws Item.NoItemException {

        for (Item item : inventory) {
            if (item.goesBy(name)) {
                return item;
            }
        }
        throw new Item.NoItemException();
    }

    Room getAdventurersCurrentRoom() {
        return adventurersCurrentRoom;
    }

    void setAdventurersCurrentRoom(Room room) {
        adventurersCurrentRoom = room;
    }

    Dungeon getDungeon() {
        return dungeon;
    }

    /**
     * Void method that will be called when player picks up health item or is
     * damaged. Takes in an int value for the health and will add or subtract to
     * the players total health, field health
     *
     * @param healthAdded
     */
    void woundHealth(int healthAdded) {
        this.health -= healthAdded;
    }

    /**
     * Void method that will be called to add points to the player score when
     * player earns more points. Will take in an int parameter and add to
     * players total score, field score.
     *
     * @param scoreAdded
     */
    void addScore(int scoreAdded) {
        this.score += scoreAdded;
    }

    /**
     * Method that will check to see if the players health is at 0; if so,
     * method will print strings telling the player their score, 
     * they have died and will end the game.
     *
     */
    void die() {
        System.out.println("You have died.");
        System.out.println("Your score is " + score);
        System.exit(0);
    }
    
    /**
     * Method that will check the player's score and return it to them so that
     * they can see how far they are.
     *
     * @return score
     */
    int getScore() {
        return this.score;
    }

    /**
     * Method that will check the player's health and return it to them so that
     * they can see how much health they've lost.
     *
     * @return health
     */
    int getHealth() {
        return this.health;
    }

    String getEquipped() {
        return this.equipped.toString();
    }

    String addItemToEquip(Item i) {
        String itemAdded;
        if (this.equipped.length == 0) {
            this.equipped[0] = i;
            itemAdded = "You have equipped " + i.getPrimaryName();
        } else if (this.equipped.length == 1) {
            this.equipped[1] = i;
            itemAdded = "You have equipped " + i.getPrimaryName();
        } else {
            itemAdded = "You cannot equip anymore items.";
        }
        return itemAdded;
    }

    String removeItemFromEquip(Item i) {
        String itemRemoved;
        if (Arrays.asList(this.equipped).contains(i)) {
            List<Item> list = new ArrayList<Item>(Arrays.asList(this.equipped));
            list.removeAll(Arrays.asList(i));
            this.equipped = list.toArray(this.equipped);
            itemRemoved = "You have unequipped " + i.getPrimaryName();
        } else {
            itemRemoved = "You do not have " + i.getPrimaryName() + " equipped";
        }
        return itemRemoved;
    }

    NonPlayerChar getNpcInVicinityNamed(String npcName) 
            throws NonPlayerChar.NoNPCException {
        for (NonPlayerChar npc : this.NPCs) {
            if (npc.getName().equals(npcName)) {
                return npc;
            }
        }

        throw new NonPlayerChar.NoNPCException();
    }

    void removeNPC(NonPlayerChar t) {
        if (this.NPCs.contains(t)) {
            this.NPCs.remove(t);
        } else {
            System.out.println("NPC does not exist here.");
        }
    }

}
