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

public class Unrolling extends Generic {

	public Unrolling() {
		// TODO Auto-generated constructor stub
		System.out.println("New unroll!");
	}
	/**
	 * This method computs the unrolling on the set of inputs.
	 * 
	 * @param stringsArrayList
	 *            The arraylist containing the input code which was typed and we want to do the transformation on
	 * @param inputString
	 *            The string containing the input code which was typed and we want to do the transformation on
	 * @return ArrayList<ArrayList<String>> This returns the first iteration value used in this for loop..
	 */
	public ArrayList<ArrayList<String>> compute(ArrayList<ArrayList<String>> stringsArrayList, String inputString){
		
		ArrayList<ArrayList<String>> outputArrayList = new ArrayList<ArrayList<String>>();
		ArrayList<String> outArrayList = new ArrayList<String>();
		int forFound = 0;
		int increment_var = 4;
		int N_value_int = 0;
		String unroll_letter = "";
		String N_value = "";
		String ForLoopSegments = "";
		List<String> normalConditions = new ArrayList<String>();
		List<String> remainderConditions = new ArrayList<String>();
		List<String> initilizerConditions = new ArrayList<String>();
		String tempLine = "";
		int remainder = 0;

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
				// find the i_value
				int result = tempLine.indexOf("++") - 1;
				char unroll_letter_char = tempLine.charAt(result);
				unroll_letter = Character.toString(unroll_letter_char);
				// split the for loop at the ';' and store the 3 sections to
				// manipulate data from them.
				String[] ForLoopSplit = tempLine.split(";");

				// finding the N value and storing it.
				if (ForLoopSplit[1].contains("<")) {
					N_value = ForLoopSplit[1].substring(ForLoopSplit[1].indexOf("<") + 1);
				} else if (ForLoopSplit[1].contains("<=")) {
					N_value = ForLoopSplit[1].substring(ForLoopSplit[1].indexOf("<=") + 1);
				} else if (ForLoopSplit[1].contains("=")) {
					N_value = ForLoopSplit[1].substring(ForLoopSplit[1].indexOf("=") + 1);
				} else {
					outArrayList = new ArrayList<String>();
					outArrayList.add("Check your For loop's syntax. Increment to N value must either be < , <= , = ");
					outputArrayList.add(outArrayList);
				}
				N_value_int = Integer.parseInt(N_value);
				ForLoopSegments = (lines[i].substring(0, lines[i].indexOf("++")) + "+=" + increment_var + ") {");
				forFound = 1;

				// if storing all the conditions once the for loop has been
				// found.
			} else if ((forFound == 1) && (!tempLine.contains("{")) && (!tempLine.contains("}"))) {
				// store the conditions.
				normalConditions.add(lines[i]);

				// If the conditions have the unroll letter then perform the
				// unroll.
				for (int j = 1; j < increment_var; j++) {
					// if the condition has the unroll character add a +1 to
					// each unroll.
					if (tempLine.contains(unroll_letter)) {
						String replaced_increment = lines[i].replaceAll(unroll_letter, unroll_letter + "+" + j);
						normalConditions.add(replaced_increment);
					} else {
						normalConditions.add(lines[i]);
					}
				}
				// remainder conditions
				remainder = N_value_int % increment_var;

				for (int k = 0; k < remainder; k++) {
					// if the condition has the unroll character add a +1 to
					// each unroll.
					if (tempLine.contains(unroll_letter)) {
						String replaced_increment = lines[i].replaceAll(unroll_letter, unroll_letter + "+" + k);
						remainderConditions.add(replaced_increment);
					} else {
						remainderConditions.add(lines[i]);
					}
				}

			} else { // storing all the initializer conditions before the for
						// loop.
				if ((!tempLine.contains("{")) && (!tempLine.contains("}"))) {
					initilizerConditions.add(lines[i]);
				}
			}
		}

		// printing the data so that the initialization variables are first,
		// then the optimized structure below followed
		// by the conditions. Any remaining/remainder conditions are placed
		// after the loop.

		for (String z : initilizerConditions) {
			outArrayList = new ArrayList<String>();
			outArrayList.add(z);
			outputArrayList.add(outArrayList);
		}

		outArrayList = new ArrayList<String>();
		outArrayList.add(ForLoopSegments);
		outputArrayList.add(outArrayList);
		outArrayList = new ArrayList<String>();
		outArrayList.add("{");
		outputArrayList.add(outArrayList);
		for (String z : normalConditions) {
			outArrayList = new ArrayList<String>();
			outArrayList.add(z);
			outputArrayList.add(outArrayList);
		}
		outArrayList = new ArrayList<String>();
		outArrayList.add("}");
		outputArrayList.add(outArrayList);
		for (String z : remainderConditions) {
			outArrayList = new ArrayList<String>();
			outArrayList.add(z);
			outputArrayList.add(outArrayList);
		}

	
		
		return outputArrayList;
	}

}
