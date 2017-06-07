package main;

import java.util.ArrayList;
import java.util.List;

public class Reversal  extends Generic {

	public Reversal() {
		// TODO Auto-generated constructor stub
		System.out.println("New reversal!");
	}
	
	public String compute(ArrayList<ArrayList<String>> stringsArrayList, String inputString){
		

		int for_Found = 0;
		String tempLine = "";
		String[] forLoopSplit = new String[3];
		String i_value = "";
		String N_value = "";
		String initialValue = "";
		boolean incrementing = true;

		List<String> initilizerConditions = new ArrayList<String>();
		List<String> NormalConditions = new ArrayList<String>();

		// Reading the text form the input text box.
		// Divide the user input textbox by each line and storing each line in
		// the String array called lines.
		String[] lines = inputString.split("\\n");

		// iterating through each line the user has inputed.
		for (int i = 0; i < (lines.length); i++) {
			// removing all the white spaces from the line for easier
			// manipulation of data.
			tempLine = lines[i].replaceAll("\\s", "");

			// if a for loop is found.
			if (tempLine.contains("for(")) {
				// split the for loop at the ';' thus effectively into 3
				// segments.
				forLoopSplit = lines[i].split(";");

				// finding the i value
				int resultI = forLoopSplit[0].indexOf("=") - 1;
				char i_value_char = forLoopSplit[0].charAt(resultI);
				i_value = Character.toString(i_value_char);

				// finding the N value
				if (forLoopSplit[1].contains("<")) {
					N_value = forLoopSplit[1].substring(forLoopSplit[1].indexOf("<") + 1);
				} else if (forLoopSplit[1].contains("<=")) {
					N_value = forLoopSplit[1].substring(forLoopSplit[1].indexOf("<=") + 1);
				} else if (forLoopSplit[1].contains("=")) {
					N_value = forLoopSplit[1].substring(forLoopSplit[1].indexOf("=") + 1);
				} else {
					System.out.println("Check your For loop's syntax. Increment to N value must either be < , <= , = ");
				}

				// finding initial value
				initialValue = forLoopSplit[0].substring(forLoopSplit[0].indexOf("=") + 1, forLoopSplit[0].length());

				// finding the if loop is incrementing or decrementing
				if (forLoopSplit[2].contains("++")) {
					incrementing = true;
				} else if (forLoopSplit[2].contains("--")) {
					incrementing = false;
				} else {
					System.out.println("Check your For loop's syntax. Increment to N value must either be < , <= , = ");
				}

				for_Found = 1;

				// after a for loop is found storing the normal conditions.
			} else if ((for_Found == 1) && (!tempLine.contains("{")) && (!tempLine.contains("}"))) {
				NormalConditions.add(lines[i]);
			} else {
				// storing the initial conditions.
				if ((!tempLine.contains("{")) && (!tempLine.contains("}"))) {
					initilizerConditions.add(lines[i]);
				}
			}
		}

		// printing the data so that the initialization variables are first,
		// then the optimized structure below followed
		// by the conditions.

		for (String z : initilizerConditions) {
			System.out.println(z);
		}

		// if print the reversed for loop depending on if the original for loop
		// was incrementing or decrementing.
		if (incrementing == true) {
			System.out.println("for (" + i_value + "=" + N_value + "-1; " + i_value + "<=" + initialValue + "; "
					+ i_value + "--) {");
		} else if (incrementing == false) {
			System.out.println("for (" + i_value + "=" + N_value + "-1; " + i_value + "<=" + initialValue + "; "
					+ i_value + "++) {");
		}

		for (String z : NormalConditions) {
			System.out.println(z);
		}
		System.out.println("}");
	
		
		return "reversal";
	}

}
