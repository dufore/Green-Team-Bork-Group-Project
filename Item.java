
package zeitz_borkv3;

import java.util.Scanner;
import java.util.Hashtable;

public class Item {

    static class NoItemException extends Exception {}

    private String primaryName;
    private int weight;
    private String event;
    private Hashtable<String,String> messages;


    Item(Scanner s) throws NoItemException,
        Dungeon.IllegalDungeonFormatException {

        messages = new Hashtable<String,String>();

        // Read item name.
        primaryName = s.nextLine();
        if (primaryName.equals(Dungeon.TOP_LEVEL_DELIM)) {
            throw new NoItemException();
        }

        // Read item weight.
        weight = Integer.valueOf(s.nextLine());

        // Read and parse verbs lines, as long as there are more.
        String verbLine = s.nextLine();
        while (!verbLine.equals(Dungeon.SECOND_LEVEL_DELIM)) {
            if (verbLine.equals(Dungeon.TOP_LEVEL_DELIM)) {
                throw new Dungeon.IllegalDungeonFormatException("No '" +
                    Dungeon.SECOND_LEVEL_DELIM + "' after item.");
            }
            String[] verbParts = verbLine.split(":");
            //For split '[' needs two backslashes, so \\[
            if(verbParts[0].contains("[")){
                String[] eventPartOne = verbParts[0].split("\\[");
                eventPartOne[1].replace("]", "");
                if(eventPartOne[1].contains(",")){
                    String[] eventPartTwo = eventPartOne[1].split(",");
                    messages.put(eventPartOne[0], verbParts[1]);
                    this.event = eventPartTwo[0]; //Only saving the first one, not sure how to save the second one as well
                } else {
                    messages.put(eventPartOne[0], verbParts[1]);
                    this.event = eventPartOne[1];
                }
            } else {
                messages.put(verbParts[0],verbParts[1]);
            }
            
            
            
            verbLine = s.nextLine();
        }
    }

    boolean goesBy(String name) {
        // could have other aliases
        return this.primaryName.equals(name);
    }

    String getPrimaryName() { return primaryName; }

    public String getMessageForVerb(String verb) {
        return messages.get(verb);
    }

    public String toString() {
        return primaryName;
    }
}
