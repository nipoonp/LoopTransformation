package main;

import java.util.ArrayList;
import java.util.List;

public class Inversion extends Generic  {

	public Inversion() {
		// TODO Auto-generated constructor stub
		System.out.println("New inversion!");
	}
	
	public ArrayList<ArrayList<String>> compute(ArrayList<ArrayList<String>> stringsArrayList, String inputString){
		
		ArrayList<ArrayList<String>> outputArrayList = new ArrayList<ArrayList<String>>();
		ArrayList<String> outArrayList = new ArrayList<String>();
		String whileLoopContents = "";
		String[] forLoopSplit = new String[3];
		int for_Found = 0;
		int while_Found = 0;
		List<String> NormalConditions = new ArrayList<String>();
		List<String> initilizerConditions = new ArrayList<String>();
		String tempLine = "";

		// Reading the text form the input text box.
		// Divide the user input textbox by each line and storing each line in
		// the String array called lines.
		String[] lines = inputString.split("\\n");

		// iterating through each line the user has inputed.
		for (int i = 0; i < (lines.length); i++) {
			// removing all the white spaces from the line for easier
			// manipulation of data.
			tempLine = lines[i].replaceAll("\\s", "");

			// checking if the while statement has been found and if it has,
			// collecting the relevant data needed form it.
			if ((tempLine.contains("while("))) {
				whileLoopContents = lines[i].substring(lines[i].indexOf("(") + 1, lines[i].indexOf(")"));
				while_Found = 1;

				// checking if the for loop has been found and if it has,
				// collecting the relevant data needed form it.
			} else if ((tempLine.contains("for("))) {
				forLoopSplit = tempLine.split(";");
				for_Found = 1;

			} else if (((for_Found == 1) || (while_Found == 1)) && (!tempLine.contains("{"))
					&& (!tempLine.contains("}"))) {
				NormalConditions.add(lines[i]);

			} else {
				// storeing all the initilizations of variables before the
				// for/while loop has been found.
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

		outArrayList = new ArrayList<String>();
		outArrayList.add("\n");
		outputArrayList.add(outArrayList);
		if (while_Found == 1) {
			outArrayList = new ArrayList<String>();
			outArrayList.add("if(" + whileLoopContents + "){");
			outputArrayList.add(outArrayList);
		} else if (for_Found == 1) {
			outArrayList = new ArrayList<String>();
			outArrayList.add("if(" + forLoopSplit[1] + "){");
			outputArrayList.add(outArrayList);
		}
		System.out.println("do{");
		for (String z : NormalConditions) {
			outArrayList = new ArrayList<String>();
			outArrayList.add(z);
			outputArrayList.add(outArrayList);
		}

		if (while_Found == 1) {
			outArrayList = new ArrayList<String>();
			outArrayList.add("} while (" + whileLoopContents + ") ;");
			outputArrayList.add(outArrayList);
		} else if (for_Found == 1) {
			outArrayList = new ArrayList<String>();
			outArrayList.add("} while (" + forLoopSplit[1] + ") ;");
			outputArrayList.add(outArrayList);
		}
		outArrayList = new ArrayList<String>();
		outArrayList.add("}");
		outputArrayList.add(outArrayList);

	
		
		return outputArrayList;
	}
	
}
