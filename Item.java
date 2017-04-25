package zeitz_borkv3;

import java.util.Scanner;
import java.util.Hashtable;

public class Item {

    static class NoItemException extends Exception {
    }

    private String primaryName;
    private int weight;
    private String event; //String for the event that is in the file such as Wound, Disappear, Transform, etc.
    private int eventNum; //Some events have values in paranthesis.  Grab that value and store it here to be passed into event constructor
    private String event2;
    private Hashtable<String, String> messages;

    Item(Scanner s) throws NoItemException,
            Dungeon.IllegalDungeonFormatException {

        messages = new Hashtable<String, String>();
        
        // Read item name.
        primaryName = s.nextLine();
        if (primaryName.equals(Dungeon.TOP_LEVEL_DELIM)) {
            throw new NoItemException();
        }

        // Read item weight.
        weight = Integer.parseInt(s.nextLine());
        // Read and parse verbs lines, as long as there are more.
        String verbLine = s.nextLine();
        while (!verbLine.equals(Dungeon.SECOND_LEVEL_DELIM)) {
            if (verbLine.equals(Dungeon.TOP_LEVEL_DELIM)) {
                throw new Dungeon.IllegalDungeonFormatException("No '"
                        + Dungeon.SECOND_LEVEL_DELIM + "' after item.");
            }
            String[] verbParts = verbLine.split(":");
            //Hydrate events
            if (verbParts[0].contains("[")) { //if there exists events declared with brackets
                String[] eventPartOne = verbParts[0].split("\\["); //Split string on that opening bracket
                eventPartOne[1].replace("]", ""); //Get rid of the end bracket 
                if (eventPartOne[1].contains(",")) { //If there are multiple events for item separated by comma
                    String[] eventPartTwo = eventPartOne[1].split(","); //Split on comma
                    messages.put(eventPartOne[0], verbParts[1]); //Put item name and message in hashtable
                    if (eventPartTwo[0].contains("(")) { //If event contains an int value
                        String[] eventPartThree = eventPartTwo[0].split("\\("); //Split on paranthesis
                        eventPartThree[1].replace(")", ""); //remove second paranthesis
                        try{
                        this.eventNum = Integer.parseInt(eventPartThree[1]); //Save number as eventNum
                        } catch(NumberFormatException e) {
                            
                        }
                        this.event = eventPartThree[0]; //Save event
                        this.event2 = eventPartTwo[1]; //Save second event
                    } else {
                        this.event = eventPartTwo[0]; //Only saving the first one, not sure how to save the second one as well
                    }
                } else {
                    try {
                        messages.put(eventPartOne[0], verbParts[1]); // Put item name and message in hashtable
                        this.event = eventPartOne[1]; //If there is only one event, save it as the event String
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                }
            } else {
                messages.put(verbParts[0], verbParts[1]); //Do the default
            }

            verbLine = s.nextLine();
        }
    }

    boolean goesBy(String name) {
        // could have other aliases
        return this.primaryName.equals(name);
    }

    String getPrimaryName() {
        return primaryName;
    }

    public String getMessageForVerb(String verb) {
        return messages.get(verb);
    }

    public String toString() {
        return primaryName;
    }

    public int getEventNum() {
        return this.eventNum;
    }

    public String getEvent() {
        return this.event;
    }

    public String getEventTwo() {
        return this.event2;
    }
}
