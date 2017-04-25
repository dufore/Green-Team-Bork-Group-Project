package zeitz_borkv3;

/**
 * FightCommand class that will extend abstract class Command. Will implement
 * abstract method execute() to return String to player
 *
 * @author James
 */
public class FightCommand extends Command {

    private String myWeaponName;
    private String npcName;
    private boolean myTurn = true;

    /**
     * Constructor that will construct the FightCommand object. Takes in a
     * String parameter and sets it to field.
     *
     * @param f
     */
    FightCommand(String item, String npc) {
        this.myWeaponName = item;
        this.npcName = npc;
    }

    /**
     * Execute() method that overrides the abstract method execute() in the
     * Command class. When called, will return a string to the player telling
     * them that a fight will begin.
     *
     * @return fight
     */
    @Override
    String execute() {
        int attackValue;
        Item myWeapon;
        NonPlayerChar npc = GameState.instance().getAdventurersCurrentRoom().getNPCS().get(0);
        int npcAttackValue = npc.getAttack();
        
        try {
            myWeapon = GameState.instance().getItemFromInventoryNamed(myWeaponName);
        } catch (Item.NoItemException e) {
            myWeapon = null;
        }

        while (npc.getHealth() > 0 && GameState.instance().getHealth() > 0) {
            if (myTurn) { // if myTurn then player attacks
                System.out.println("It is your turn.");

                if (myWeapon == null) {
                    attackValue = 1;
                } else {
                    attackValue = myWeapon.getItemAttack();
                }
                npc.woundHealth(attackValue);
                System.out.println("You dealt " + attackValue + " damage!");
                System.out.println();
                myTurn = false; // makes myTurn false so the npc attacks next

            } else { //if myTurn is false then the npc attacks
                GameState.instance().woundHealth(npcAttackValue);
                System.out.println(npc.getName() + " attacked you.");
                System.out.println("You took " + npcAttackValue + " damage!");
                System.out.println();
                myTurn = true; // makes myTurn true so the Player attacks next
            }
        }

        if (GameState.instance().getHealth() <= 0) {
            GameState.instance().die();
        }

        if (npc.getHealth() <= 0) {
            System.out.println("You have killed " + npcName + "!");
            GameState.instance().removeNPC(npc);
        }

        return null;
    }

}
