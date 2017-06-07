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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JOptionPane;

public class Generic {
	   /**
	   * This method is used to check if two strings are the same.
	   * 
	   * @param input This is the first paramter to checkIfEqual method
	   * @param input2  This is the second parameter to checkIfEqual method
	   * @return boolean This returns true if both strings are the same.
	   */
	protected boolean checkIfEqual(String input, String input2) {
		input = input.substring(input.indexOf("=") + 1);
		input = input.substring(0, input.indexOf(")"));
		String I = Character.toString(getIterationVariable(input));
		input = input.replaceAll(I, "");

		input2 = input2.substring(input2.indexOf("=") + 1);
		input2 = input2.substring(0, input2.indexOf(")"));
		String J = Character.toString(getIterationVariable(input2));
		input2 = input2.replaceAll(J, "");

		return input.equalsIgnoreCase(input2);
	}

	
	   /**
	   * This method is used to get the iteration variable for a for loop.
	   * @param word This is the first line of the for loop which is used to find
	   * out the iteration variable 
	   * @return char This returns the iteration variable used in this for loop..
	   */
	protected char getIterationVariable(String word) {
		if (word == null || word.isEmpty()) {
			throw new IllegalArgumentException("input word must have non-empty value.");
		}
		char maxchar = ' ';
		int maxcnt = 0;
		// if you are confident that your input will be only ascii, then this
		// array can be size 128.
		int[] charcnt = new int[Character.MAX_VALUE + 1];
		for (int i = word.length() - 1; i >= 0; i--) {
			char ch = word.charAt(i);
			// increment this character's cnt and compare it to our max.
			if ((++charcnt[ch] >= maxcnt) && (Character.isLetter(ch))) {
				maxcnt = charcnt[ch];
				maxchar = ch;
			}
		}
		return maxchar;
	}

	protected String getMaxIterationValue(String input) {

		input = input.substring(input.indexOf(";") + 1);
		input = input.substring(0, input.indexOf(";"));

		if (input.contains("<=")) {
			input = input.substring(input.indexOf("<=") + 2);

		} else if (input.contains(">=")) {
			input = input.substring(input.indexOf(">=") + 2);
		} else if (input.contains("<")) {
			input = input.substring(input.indexOf("<") + 1);
		} else if (input.contains(">")) {
			input = input.substring(input.indexOf(">") + 1);
		} else {
			System.out.println("***Incorrect for loop input!***");
		}

		return input;
	}

	protected String getStartingIterationValue(String input) {
		input = input.substring(input.indexOf("=") + 1);
		input = input.substring(0, input.indexOf(";"));

		return input;
	}

	protected String getIterationIncrement(String input) {
		input = input.substring(input.indexOf(";") + 1);
		input = input.substring(0, input.indexOf(")"));

		return input;
	}

	protected String getIterationIncrementOnly(String input) {
		input = getIterationIncrement(input);
		return input.substring(input.indexOf(";") + 1);
	}

	private boolean checkBounds(String currentTemp1, String currentTemp2, String forLine) {

		currentTemp1 = currentTemp1.substring(currentTemp1.indexOf("[") + 1, currentTemp1.indexOf("]"));
		currentTemp2 = currentTemp2.substring(currentTemp2.indexOf("[") + 1, currentTemp2.indexOf("]"));

		System.out.println("-----------");
		System.out.println(currentTemp1 + " " + currentTemp2 + " " + forLine);
		System.out.println("---------------");
		int inc = 0;

		if (getIterationIncrementOnly(forLine).contains("++")) {
			inc = 1;
		} else if (getIterationIncrementOnly(forLine).contains("--")) {
			inc = -1;
		} else if ((getIterationIncrementOnly(forLine).contains("+="))) {
			inc = Integer.parseInt((getIterationIncrementOnly(forLine))
					.substring(getIterationIncrementOnly(forLine).indexOf("=") + 1));
		} else if ((getIterationIncrementOnly(forLine).contains("-="))) {
			inc = Integer.parseInt((getIterationIncrementOnly(forLine))
					.substring(getIterationIncrementOnly(forLine).indexOf("=") + 1));
		}

		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");

		int a1[] = new int[Integer.parseInt(getMaxIterationValue(forLine))];
		int a2[] = new int[Integer.parseInt(getMaxIterationValue(forLine))];

		for (int i = Integer.parseInt(getStartingIterationValue(forLine)); i < Integer
				.parseInt(getMaxIterationValue(forLine)); i += inc) {
			try {
				a1[i] = ((int) engine.eval(currentTemp1.replaceAll("i", Integer.toString(i))));
				a2[i] = ((int) engine.eval(currentTemp2.replaceAll("i", Integer.toString(i))));
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return findDupes(a1, a2);
	}

	private boolean findDupes(int[] a, int[] b) {

		int temp1 = 0;
		int temp2 = 0;

		for (int i = 0; i < a.length; i++) {
			temp1 = a[i];
			for (int j = 0; j < b.length; j++) {
				temp2 = b[j];

				if (temp1 == temp2) {
					// if (i < j) {
					// System.out.println(x);
					// }
					// System.out.println(arg0);
					System.out.println("true---");
					return true;
					// }
				}
			}
		}

		System.out.println("false---");
		return false;
	}

	private ArrayList<String> splitLine(String string) {

		ArrayList<String> temp = new ArrayList<String>();
		int count = 0;

		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) == 'a' || string.charAt(i) == 'b' || string.charAt(i) == 'c') {
				if (string.charAt(i + 1) == '[') {
					int j = i + 2;
					String appendto = "";
					if (string.charAt(i) == 'a') {
						appendto = "a[";
					}
					if (string.charAt(i) == 'b') {
						appendto = "b[";
					}
					if (string.charAt(i) == 'c') {
						appendto = "c[";
					}
					while (string.charAt(j) != ']') {
						appendto = appendto + string.charAt(j);
						j++;
					}
					appendto = appendto + ']';
					temp.add(appendto);

				}
			}

			if (string.charAt(i) == 'x' || string.charAt(i) == 'y' || string.charAt(i) == 'z') {
				if (string.charAt(i + 1) == '[') {
					int j = i + 2;
					String appendto = "";
					if (string.charAt(i) == 'x') {
						appendto = "x[";
					}
					if (string.charAt(i) == 'y') {
						appendto = "y[";
					}
					if (string.charAt(i) == 'z') {
						appendto = "z[";
					}

					while (count != 2) {
						if (string.charAt(j) == ']') {
							count++;
						}
						appendto = appendto + string.charAt(j);
						j++;
					}
					appendto = appendto + ']';
					temp.add(appendto);
					count = 0;

				}
			}
		}

		return temp;

	}

	private ArrayList<String> getGCDParams_ifNumberOnRightofj(String s) {

		String temp1_leftofi = "";
		String temp2_rightofi = "";
		String var1 = "";
		String var2 = "";
		ArrayList<String> gcdparams = new ArrayList<String>();

		for (int i = 0; i < s.length(); i++) {

			if (s.charAt(i) == 'j') {
				int j = i - 1;
				while (j != -1) {
					if (s.charAt(j) == '[') {
						break;
					}
					;
					temp1_leftofi = temp1_leftofi + s.charAt(j);
					j--;

				}

				temp1_leftofi = new StringBuilder(temp1_leftofi).reverse().toString();

				int k = i + 1;
				while (k != s.length()) {
					if (s.charAt(k) == ']') {
						break;
					}
					;
					temp2_rightofi = temp2_rightofi + s.charAt(k);
					k++;

				}
			}

		}

		for (int i = 0; i < temp2_rightofi.length(); i++) {
			if (temp2_rightofi.charAt(i) == '+' || temp2_rightofi.charAt(i) == '-') {
				int j = i;
				while (j != temp2_rightofi.length() - 1) {
					var2 = var2 + temp2_rightofi.charAt(j + 1);
					j++;
				}
			}
		}

		for (int i = 0; i < temp2_rightofi.length(); i++) {
			if (temp2_rightofi.charAt(i) == '*') {
				int j = i;
				while (j != temp2_rightofi.length() - 1) {
					j++;
					if (temp2_rightofi.charAt(j) == '+' || temp2_rightofi.charAt(j) == '-') {
						break;
					} else {
						var1 = var1 + temp2_rightofi.charAt(j);
					}

				}
			}
		}

		if (var1 == "") {
			var1 = var1 + "1";
		}
		if (var2 == "") {
			var2 = var2 + "0";
		}

		gcdparams.add(var1);
		gcdparams.add(var2);

		return gcdparams;

	}

	private ArrayList<String> getGCDParams_ifNumberOnLeftofj(String s) {
		String temp1_leftofi = "";
		String temp2_rightofi = "";
		String var1 = "";
		String var2 = "";
		ArrayList<String> gcdparams = new ArrayList<String>();

		for (int i = 0; i < s.length(); i++) {

			if (s.charAt(i) == 'j') {
				int j = i - 1;
				while (j != -1) {
					if (s.charAt(j) == '[') {
						break;
					}
					;
					temp1_leftofi = temp1_leftofi + s.charAt(j);
					j--;

				}

				temp1_leftofi = new StringBuilder(temp1_leftofi).reverse().toString();

				int k = i + 1;
				while (k != s.length()) {
					if (s.charAt(k) == ']') {
						break;
					}
					;
					temp2_rightofi = temp2_rightofi + s.charAt(k);
					k++;

				}
			}

		}

		var1 = temp1_leftofi.replace("*", "");
		for (int i = 0; i < temp2_rightofi.length(); i++) {
			if (temp2_rightofi.charAt(i) == '+' || temp2_rightofi.charAt(i) == '-') {
				int j = i;
				while (j != temp2_rightofi.length() - 1) {
					var2 = var2 + temp2_rightofi.charAt(j + 1);
					j++;
				}
			}
		}

		if (var1 == "") {
			var1 = var1 + "1";
		}
		if (var2 == "") {
			var2 = var2 + "0";
		}

		gcdparams.add(var1);
		gcdparams.add(var2);

		return gcdparams;
	}

	private ArrayList<String> getGCDParams_forJ(String s) {

		if (s.contains("[j")) {
			// System.out.println(getGCDParams_ifNumberOnRightofj(s) + "here it
			// is for j");
			return getGCDParams_ifNumberOnRightofj(s);
		} else {

			// System.out.println(getGCDParams_ifNumberOnLeftofj(s) + "here it
			// is for j");
			return getGCDParams_ifNumberOnLeftofj(s);
		}

	}

	protected boolean test(ArrayList<String> testList, ArrayList<String> forLine) {

		ArrayList<ArrayList<String>> twoDArrayList = new ArrayList<ArrayList<String>>();
		boolean returned;
		
		for (int i = 0; i < testList.size(); i++) {
			twoDArrayList.add(splitLine(testList.get(i)));
		}
		for (int i = 0; i < testList.size(); i++) {
			String currentTemp1 = twoDArrayList.get(i).get(0);

			for (int j = 0; j < twoDArrayList.size(); j++) {


				ArrayList<String> subRow = twoDArrayList.get(j);

				for (int k = 1; k < subRow.size(); k++) {

					String currentTemp2 = twoDArrayList.get(j).get(k);

					if (currentTemp1.charAt(0) != currentTemp2.charAt(0)) {
						continue;
					}
					returned = (calculate_gcd_dependence(getGCDParams_forI(currentTemp1),
							getGCDParams_forI(currentTemp2)));
					if (returned) {
						if (checkBounds(currentTemp1, currentTemp2, forLine.get(0))) {
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	protected boolean test2DI(ArrayList<String> testList, ArrayList<String> forLine) {

		ArrayList<ArrayList<String>> twoDArrayList = new ArrayList<ArrayList<String>>();
		int tru = 0;
		int fals = 0;
		boolean returned;
		for (int i = 0; i < testList.size(); i++) {
			twoDArrayList.add(splitLine(testList.get(i)));
		}
		
		for (int i = 0; i < testList.size(); i++) {
			String currentTemp1 = twoDArrayList.get(i).get(0);

			for (int j = 0; j < twoDArrayList.size(); j++) {

				ArrayList<String> subRow = twoDArrayList.get(j);

				for (int k = 1; k < subRow.size(); k++) {
					
					String currentTemp2 = twoDArrayList.get(j).get(k);
					if (currentTemp1.charAt(0) != currentTemp2.charAt(0)) {
						continue;
					}
					returned = (calculate_gcd_dependence(getGCDParams_forI(currentTemp1),
							getGCDParams_forI(currentTemp2)));
					if (returned) {
						if (checkBounds(currentTemp1, currentTemp2, forLine.get(0))) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	protected boolean test2DJ(ArrayList<String> testList, ArrayList<String> forLine) {

		ArrayList<ArrayList<String>> twoDArrayList = new ArrayList<ArrayList<String>>();
		boolean returned;
		for (int i = 0; i < testList.size(); i++) {
			twoDArrayList.add(splitLine(testList.get(i)));
		}

		for (int i = 0; i < testList.size(); i++) {
			String currentTemp1 = twoDArrayList.get(i).get(0);

			for (int j = 0; j < twoDArrayList.size(); j++) {

				ArrayList<String> subRow = twoDArrayList.get(j);

				for (int k = 1; k < subRow.size(); k++) {
					
					String currentTemp2 = twoDArrayList.get(j).get(k);

					if (currentTemp1.charAt(0) != currentTemp2.charAt(0)) {
						continue;
					}
					returned = (calculate_gcd_dependence(getGCDParams_forJ(currentTemp1),
							getGCDParams_forJ(currentTemp2)));
					if (returned) {
						if (checkBounds(currentTemp1, currentTemp2, forLine.get(0))) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	private boolean calculate_gcd_dependence(ArrayList<String> s1, ArrayList<String> s2) {

		int a = Integer.parseInt(s1.get(0));
		int b = Integer.parseInt(s2.get(0));
		int c = gcd(a, b);
		int d = Integer.parseInt(s1.get(1));
		int e = Integer.parseInt(s2.get(1));

		int r1 = Math.abs(d - e);

		if ((a == b) && (d == e)) {
			return false;
		}

		if (r1 % c == 0) {
			return true; // there is a dependence
		} else {
			return false; // there is no dependence
		}

	}

	private ArrayList<String> getGCDParams_forI(String s) {
		// returns the gcd parameters for i based on where i is located in the string

		if (s.contains("[i")) {
			return getGCDParams_ifNumberOnRightofi(s);
		} else {
			return getGCDParams_ifNumberOnLeftofi(s);
		}

	}

	private ArrayList<String> getGCDParams_ifNumberOnRightofi(String s) {
		//this function allows us to acquire the GCD parameters if the user inputs a format
		// like i*a + c.
		
		String temp1_leftofi = "";
		String temp2_rightofi = "";
		String var1 = "";
		String var2 = "";
		ArrayList<String> gcdparams = new ArrayList<String>();

		for (int i = 0; i < s.length(); i++) {

			if (s.charAt(i) == 'i') {
				int j = i - 1;
				while (j != -1) {
					if (s.charAt(j) == '[') {
						break;
					}
					;
					temp1_leftofi = temp1_leftofi + s.charAt(j);
					j--;

				}

				temp1_leftofi = new StringBuilder(temp1_leftofi).reverse().toString();

				int k = i + 1;
				while (k != s.length()) {
					if (s.charAt(k) == ']') {
						break;
					}
					;
					temp2_rightofi = temp2_rightofi + s.charAt(k);
					k++;

				}
			}

		}

		for (int i = 0; i < temp2_rightofi.length(); i++) {
			if (temp2_rightofi.charAt(i) == '+' || temp2_rightofi.charAt(i) == '-') {
				int j = i;
				while (j != temp2_rightofi.length() - 1) {
					var2 = var2 + temp2_rightofi.charAt(j + 1);
					j++;
				}
			}
		}

		for (int i = 0; i < temp2_rightofi.length(); i++) {
			if (temp2_rightofi.charAt(i) == '*') {
				int j = i;
				while (j != temp2_rightofi.length() - 1) {
					j++;
					if (temp2_rightofi.charAt(j) == '+' || temp2_rightofi.charAt(j) == '-') {
						break;
					} else {
						var1 = var1 + temp2_rightofi.charAt(j);
					}

				}
			}
		}

		if (var1 == "") {
			var1 = var1 + "1";
		}
		if (var2 == "") {
			var2 = var2 + "0";
		}

		gcdparams.add(var1);
		gcdparams.add(var2);

		return gcdparams;

	}

	private ArrayList<String> getGCDParams_ifNumberOnLeftofi(String s) {
		//this function allows us to acquire the GCD parameters if the user inputs a format
		// like a*i + c.
			
		String temp1_leftofi = "";
		String temp2_rightofi = "";
		String var1 = "";
		String var2 = "";
		ArrayList<String> gcdparams = new ArrayList<String>();

		for (int i = 0; i < s.length(); i++) {

			if (s.charAt(i) == 'i') {
				int j = i - 1;
				while (j != -1) {
					if (s.charAt(j) == '[') {
						break;
					}
					;
					temp1_leftofi = temp1_leftofi + s.charAt(j);
					j--;

				}

				temp1_leftofi = new StringBuilder(temp1_leftofi).reverse().toString();

				int k = i + 1;
				while (k != s.length()) {
					if (s.charAt(k) == ']') {
						break;
					}
					;
					temp2_rightofi = temp2_rightofi + s.charAt(k);
					k++;

				}
			}

		}

		var1 = temp1_leftofi.replace("*", "");
		for (int i = 0; i < temp2_rightofi.length(); i++) {
			if (temp2_rightofi.charAt(i) == '+' || temp2_rightofi.charAt(i) == '-') {
				int j = i;
				while (j != temp2_rightofi.length() - 1) {
					var2 = var2 + temp2_rightofi.charAt(j + 1);
					j++;
				}
			}
		}

		if (var1 == "") {
			var1 = var1 + "1";
		}
		if (var2 == "") {
			var2 = var2 + "0";
		}

		gcdparams.add(var1);
		gcdparams.add(var2);

		return gcdparams;

	}

	private int gcd(int a, int b) {
		if (a == 0 || b == 0)
			return a + b; // base case
		return gcd(b, a % b);
	}

	
	
	
	
	
}
