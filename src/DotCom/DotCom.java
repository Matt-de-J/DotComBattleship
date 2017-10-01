package DotCom;

import java.util.*;
/**
 * Manages the basic info of the dotCom "battleships". Each battleship is it's own object consisting of
 * coordinates and a name. Each coordinate will be removed as the user guesses them correctly, and a kill
 * will be marked when the coordinates list is empty
 * 
 * 
 * @author mattj
 * Created by HFJ
 *
 */
public class DotCom {
	private ArrayList<String> locationCells;
	private String name;
	
	/**
	 * Created by HFJ
	 * @param loc
	 */
	public void setLocationCells(ArrayList<String> loc) {
		locationCells = loc;
	}
	
	/**
	 * Created by HFJ
	 * Sets the name of the dotCom website to the passed string
	 * @param n
	 */
	public void setName(String n) {
		name = n;
	}
	
	/**
	 * Created by Matt J
	 * @return the name of the dotcom object
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Takes the user input and returns if it was a hit/miss/kill
	 * 
	 * @param userInput
	 * @return
	 */
	public String checkYourself(String userInput) {
		String result = "miss";
		int index = locationCells.indexOf(userInput);		//if userInput is in the locationCells List, it will return the index, else it'll return -1
		if(index >= 0) {		//if the userInput wasn't in the list, this is skipped
			locationCells.remove(index);		//removes the coordinate that the user correctly guessed
			
			if(locationCells.isEmpty()) {
				result = "kill";
				System.out.println("Ouch you sunk " + name + " :(");
			}
			else {
				result = "hit";
			}
		}
		return result;
	}
}
