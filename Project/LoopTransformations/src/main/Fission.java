/**
* <h1>Loop Optimization</h1>
* The loop optimization program implements an application that
* finds dependencies in loops and rearranges them to make them
* paralleizable.

*
* @author  Softeng 751 2017 group 17
* @version 1.0
* @since   2017-05-08
*/
package main;

import java.util.ArrayList;
import java.util.List;

public class Fission extends Generic {

	public Fission() {
		// TODO Auto-generated constructor stub
		System.out.println("New fission!");
	}
	
	/**
	 * This method computs the fission on the set of inputs.
	 * 
	 * @param stringsArrayList
	 *            The arraylist containing the input code which was typed and we want to do the transformation on
	 * @param inputString
	 *            The string containing the input code which was typed and we want to do the transformation on
	 * @return ArrayList<ArrayList<String>> This returns the first iteration value used in this for loop..
	 */
	public ArrayList<ArrayList<String>> compute(ArrayList<ArrayList<String>> stringsArrayList, String inputString){

		int for_Found = 0;
		String tempLine = "";
		
		ArrayList<ArrayList<String>> outputArrayList = new ArrayList<ArrayList<String>>();
		ArrayList<String> outArrayList = new ArrayList<String>();

		if (test(stringsArrayList.get(2), stringsArrayList.get(1))) {
			System.out.println("cannot fission!");
			outArrayList.add("cannot fission!");
			outputArrayList.add(outArrayList);
			return outputArrayList;
		}

		List<String> initilizerConditions = new ArrayList<String>();
		List<String> NormalConditions = new ArrayList<String>();
		String mainForLoop = "";

		// Reading the text form the input text box.
		// Divide the user input textbox by each line and storing each line in
		// the String array called lines.
		String[] lines = inputString.split("\\n");

		// iterating through each line the user has inputed.
		for (int i = 0; i < (lines.length); i++) {
			// removing all the white spaces from the line for easier
			// manipulation of data.
			tempLine = lines[i].replaceAll("\\s", "");

			// if the for loop is found
			if (tempLine.contains("for(")) {
				// copy the contents of the for loop
				mainForLoop = lines[i];
				for_Found = 1;

				// after the for loop is found take the normal conditions.
			} else if ((for_Found == 1) && (!tempLine.contains("{")) && (!tempLine.contains("}"))) {
				NormalConditions.add(lines[i]);
			} else {
				// get the initialization conditions.
				if ((!tempLine.contains("{")) && (!tempLine.contains("}"))) {
					initilizerConditions.add(lines[i]);
				}
			}
		}

		// printing the data so that the initialization variables are first,
		// then the optimized structure below followed
		// by the conditions.

		for (String z : initilizerConditions) {
			outArrayList = new ArrayList<String>();
			outArrayList.add(z);
			outputArrayList.add(outArrayList);
		}
		if (mainForLoop.contains("{")) {
			outArrayList = new ArrayList<String>();
			outArrayList.add(mainForLoop);
			outputArrayList.add(outArrayList);
		} else {
			outArrayList = new ArrayList<String>();
			outArrayList.add(mainForLoop);
			outputArrayList.add(outArrayList);
			outArrayList = new ArrayList<String>();
			outArrayList.add("{");
			outputArrayList.add(outArrayList);
		}
		// for each condition , print it in a seperate for loop.
		int normalConditions_size = NormalConditions.size();
		for (int z = 0; z < normalConditions_size; z++) {
			outArrayList = new ArrayList<String>();
			outArrayList.add(NormalConditions.get(z));
			outputArrayList.add(outArrayList);
			outArrayList = new ArrayList<String>();
			outArrayList.add("}");
			outputArrayList.add(outArrayList);
			if (z != (normalConditions_size - 1)) {
				if (mainForLoop.contains("{")) {
					outArrayList = new ArrayList<String>();
					outArrayList.add(mainForLoop);
					outputArrayList.add(outArrayList);
				} else {
					outArrayList = new ArrayList<String>();
					outArrayList.add(mainForLoop);
					outputArrayList.add(outArrayList);
					outArrayList = new ArrayList<String>();
					outArrayList.add("{");
					outputArrayList.add(outArrayList);
				}
			}
		}

	
		
		return outputArrayList;
	}

}
