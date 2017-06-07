package main;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.SwingConstants;

public class GUI {

	private JFrame frame;
	private JTextArea inputTextField;
	private JTextArea outputTextField;
	private String inputString;
	private JavaSyntaxChecker checker;
	private ArrayList<ArrayList<String>> stringsArrayList = new ArrayList<ArrayList<String>>();

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		// getDependencies();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 767, 477);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnTransform = new JButton("TRANSFORM");
		btnTransform.setForeground(Color.BLACK);
		btnTransform.setBackground(Color.RED);
		btnTransform.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				write_to_file();
			}
		});
		btnTransform.setBounds(413, 210, 106, 32);
		frame.getContentPane().add(btnTransform);

		inputTextField = new JTextArea();
		Font font = new Font("Arial", Font.PLAIN, 20);
		inputTextField.setFont(font);
		inputTextField.setBounds(179, 43, 562, 156);
		frame.getContentPane().add(inputTextField);
		inputTextField.setColumns(10);

		outputTextField = new JTextArea();
		outputTextField.setBounds(179, 284, 562, 143);
		frame.getContentPane().add(outputTextField);
		outputTextField.setColumns(10);

		JLabel inputTitle = new JLabel("INPUT CODE HERE");
		inputTitle.setFont(new Font("Arial Black", Font.PLAIN, 15));
		inputTitle.setBounds(378, 18, 165, 14);
		frame.getContentPane().add(inputTitle);

		JLabel outputTitle = new JLabel("TRASFORMED RESULT");
		outputTitle.setFont(new Font("Arial Black", Font.PLAIN, 15));
		outputTitle.setBounds(361, 259, 199, 14);
		frame.getContentPane().add(outputTitle);

		JLabel unrollLabel = new JLabel("Unroll");
		unrollLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		unrollLabel.setForeground(Color.BLUE);
		unrollLabel.setHorizontalAlignment(SwingConstants.LEFT);
		unrollLabel.setBounds(23, 56, 46, 14);
		frame.getContentPane().add(unrollLabel);

		JLabel unswitchLabel = new JLabel("Unswitch");
		unswitchLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		unswitchLabel.setForeground(Color.BLUE);
		unswitchLabel.setHorizontalAlignment(SwingConstants.LEFT);
		unswitchLabel.setBounds(23, 101, 61, 19);
		frame.getContentPane().add(unswitchLabel);

		JLabel fusionLabel = new JLabel("Fusion");
		fusionLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		fusionLabel.setBackground(new Color(240, 240, 240));
		fusionLabel.setForeground(Color.BLUE);
		fusionLabel.setHorizontalAlignment(SwingConstants.LEFT);
		fusionLabel.setBounds(23, 158, 46, 14);
		frame.getContentPane().add(fusionLabel);

		JLabel fissionLabel = new JLabel("Fission");
		fissionLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		fissionLabel.setForeground(Color.BLUE);
		fissionLabel.setHorizontalAlignment(SwingConstants.LEFT);
		fissionLabel.setBounds(23, 210, 46, 14);
		frame.getContentPane().add(fissionLabel);

		JLabel peelingLabel = new JLabel("Peeling");
		peelingLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		peelingLabel.setForeground(Color.BLUE);
		peelingLabel.setBounds(23, 261, 46, 14);
		frame.getContentPane().add(peelingLabel);

		JLabel loopInversionLabel = new JLabel("Loop Inversion");
		loopInversionLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		loopInversionLabel.setForeground(Color.BLUE);
		loopInversionLabel.setBounds(23, 313, 91, 14);
		frame.getContentPane().add(loopInversionLabel);

		JLabel ReversalLabel = new JLabel("Loop Reversal");
		ReversalLabel.setForeground(Color.BLUE);
		ReversalLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		ReversalLabel.setBounds(23, 366, 82, 14);
		frame.getContentPane().add(ReversalLabel);
	}

	private void processString(String inputString) {
		String[] splitLineArray = inputString.split("\\r?\\n");

		ArrayList<String> currArrayList = new ArrayList<String>();

		for (int ii = 0; ii < splitLineArray.length; ii++) {
			splitLineArray[ii] = splitLineArray[ii].replaceAll("\\s", "");
		}

		int cntr = 0;
		for (int jj = 0; jj < splitLineArray.length; jj++) {
			if (jj != 0) {
				if (splitLineArray[jj - 1].contains("for") || (splitLineArray[jj - 1].contains("}"))) {
					cntr++;
					stringsArrayList.add(currArrayList);
					currArrayList = new ArrayList<String>();
				}

			}
			if ((splitLineArray[jj].contains("for")) || (splitLineArray[jj].contains("}"))) {
				cntr++;
				stringsArrayList.add(currArrayList);
				currArrayList = new ArrayList<String>();
			}
			currArrayList.add(splitLineArray[jj]);
		}

		if (!(splitLineArray[splitLineArray.length - 1].contains("}"))) {
			stringsArrayList.add(currArrayList);
		}

		System.out.println(stringsArrayList);

		// skewing();
		// interchange();
		// unrolling();
		// inversion();
		// fission();
		fusion();
	}

	public void fission() {
		int for_Found = 0;
		String tempLine = "";

		List<String> initilizerConditions = new ArrayList<String>();
		List<String> NormalConditions = new ArrayList<String>();
		String mainForLoop = "";

		inputString = inputTextField.getText();
		String[] lines = inputTextField.getText().split("\\n");

		for (int i = 0; i < (lines.length); i++) {
			tempLine = lines[i].replaceAll("\\s", "");
			if (tempLine.contains("for(")) {
				mainForLoop = lines[i];
				for_Found = 1;

			} else if ((for_Found == 1) && (!tempLine.contains("{")) && (!tempLine.contains("}"))) {
				NormalConditions.add(lines[i]);
			} else {
				if ((!tempLine.contains("{")) && (!tempLine.contains("}"))) {
					initilizerConditions.add(lines[i]);
				}

			}
		}

		for (String z : initilizerConditions) {
			System.out.println(z);
		}
		if (mainForLoop.contains("{")) {
			System.out.println(mainForLoop);
		} else {
			System.out.println(mainForLoop);
			System.out.println("{");
		}

		int normalConditions_size = NormalConditions.size();
		for (int z = 0; z < normalConditions_size; z++) {
			System.out.println(NormalConditions.get(z));
			System.out.println("}");
			if (z != (normalConditions_size - 1)) {
				if (mainForLoop.contains("{")) {
					System.out.println(mainForLoop);
				} else {
					System.out.println(mainForLoop);
					System.out.println("{");
				}
			}
		}

	}

	private void fusion() {
		// String I =
		// Character.toString(getIterationVariable(stringsArrayList.get(1).get(0)));
		// String J =
		// Character.toString(getIterationVariable(stringsArrayList.get(1).get(0)));
		// String K =
		// Character.toString(getIterationVariable(stringsArrayList.get(1).get(0)));

		ArrayList<String> add = new ArrayList<String>();
		add.add("}");
		stringsArrayList.add(add);

		for (int i = 1; i < stringsArrayList.size(); i += 4) {
			for (int j = i + 4; j < stringsArrayList.size(); j += 4) {

				if ((stringsArrayList.get(i).isEmpty()) || (stringsArrayList.get(j).isEmpty())) {
					continue;
				}
				if (checkIfEqual(stringsArrayList.get(i).get(0), (stringsArrayList.get(j)).get(0))) {
					System.out.println(stringsArrayList.get(i) + " " + (stringsArrayList.get(j)));

					ArrayList<String> testArrayList = new ArrayList<String>();
					testArrayList.add(stringsArrayList.get(i + 1).get(0));
					testArrayList.add(stringsArrayList.get(j + 1).get(0));
					System.out.println(testArrayList);

					System.out.println(test(testArrayList,stringsArrayList.get(i)));
					if (test(testArrayList,stringsArrayList.get(i))) {
						continue;
					}

					// if(){
					// stringsArrayList.set(i+1, stringsArrayList.get(i+1) +
					// stringsArrayList.get(j+1));
					System.out.println(stringsArrayList);
					String stringToAdd = stringsArrayList.get(j + 1).get(0);
					stringsArrayList.get(i + 1).add(0, stringToAdd);

					stringsArrayList.get(j).clear();
					stringsArrayList.get(j + 1).clear();
					stringsArrayList.get(j + 2).clear();
					System.out.println(stringsArrayList);

					// }
				}

			}
		}

	}

	private boolean checkIfEqual(String input, String input2) {
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

	private void interchange() {

		if (test2DI(stringsArrayList.get(4)) == false) {
			ArrayList<String> temp = stringsArrayList.get(1);
			stringsArrayList.set(1, stringsArrayList.get(3));
			stringsArrayList.set(3, temp);
			System.out.println("Interchanging...");
		} else {
			System.out.println("Can't Interchange!");
		}

		// test with 2D
		// find if there is dependence in the i loops

		// if there is no dependence in the i loops then apply the thing below

	}

	public static char getIterationVariable(String word) {
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

	public static String getMaxIterationValue(String input) {

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

	public static String getStartingIterationValue(String input) {
		input = input.substring(input.indexOf("=") + 1);
		input = input.substring(0, input.indexOf(";"));

		return input;
	}

	public static String getIterationIncrement(String input) {
		input = input.substring(input.indexOf(";") + 1);
		input = input.substring(0, input.indexOf(")"));

		return input;
	}

	public static String getIterationIncrementOnly(String input){
		input = getIterationIncrement(input);
		return input.substring(input.indexOf(";") + 1);
	}
	
	private void inversion() {
		String whileLoopContents = "";
		String[] forLoopSplit = new String[3];
		int for_Found = 0;
		int while_Found = 0;
		List<String> NormalConditions = new ArrayList<String>();
		List<String> initilizerConditions = new ArrayList<String>();
		String tempLine = "";
		
		inputString = inputTextField.getText();				
		String[] lines = inputTextField.getText().split("\\n");

		for(int i = 0; i < (lines.length); i++){
			tempLine=lines[i].replaceAll("\\s","");
			
			if ((tempLine.contains("while("))) {
				whileLoopContents = lines[i].substring(lines[i].indexOf("(") + 1, lines[i].indexOf(")"));
				while_Found = 1;
				
			}else if((tempLine.contains("for("))){
				forLoopSplit = tempLine.split(";");
				for_Found = 1;
				
			}else if (((for_Found == 1)||(while_Found == 1))&&(!tempLine.contains("{")) && (!tempLine.contains("}"))){
				NormalConditions.add(lines[i]);
				
			}else{
				if((!tempLine.contains("{")) && (!tempLine.contains("}"))){
					initilizerConditions.add(lines[i]);	
				}	
			}
		}

	    for (String z : initilizerConditions) {
	    	System.out.println(z);
	    }
	    
	    System. out.println("\n");
	    if(while_Found == 1){
	    	System.out.println("if("+whileLoopContents+"){");
	    }else if(for_Found == 1) {
	    	System.out.println("if("+forLoopSplit[1]+"){");
	    }
	    System.out.println("do{");
	    for (String z : NormalConditions) {
	    	System.out.println(z);
	    }
	    
	    if(while_Found == 1){
	    	System.out.println("} while ("+whileLoopContents+") ;");
	    }else if(for_Found == 1) {
	    	System.out.println("} while ("+forLoopSplit[1]+") ;");
	    }
	    System.out.println("}");
	}

	private void unrolling() {
		int For_notFound = 0;
		int increment_var = 4;
		int N_value_int = 0;
		String unroll_letter = "";
		String N_value = "";
		String ForLoopSegments = "";
		List<String> NormalConditions = new ArrayList<String>();

		inputString = inputTextField.getText();
		String[] lines = inputTextField.getText().split("\\n");

		for (int i = 0; i < (lines.length); i++) {
			if ((lines[i].contains("for (")) || (lines[i].contains("for("))) {
				int result = lines[i].indexOf("++") - 1;
				char unroll_letter_char = lines[i].charAt(result);
				unroll_letter = Character.toString(unroll_letter_char);
				String[] ForLoopSplit = lines[i].split(";");

				if (ForLoopSplit[1].contains("<")) {
					N_value = ForLoopSplit[1].substring(ForLoopSplit[1].indexOf("<") + 1);
				} else if (ForLoopSplit[1].contains("<=")) {
					N_value = ForLoopSplit[1].substring(ForLoopSplit[1].indexOf("<=") + 1);
				} else if (ForLoopSplit[1].contains("=")) {
					N_value = ForLoopSplit[1].substring(ForLoopSplit[1].indexOf("=") + 1);
				} else {
					System.out.println("Check your For loop's syntax. Increment to N value must either be < , <= , = ");
				}
				N_value_int = Integer.parseInt(N_value);
				ForLoopSegments = (lines[i].substring(0, lines[i].indexOf("++")) + "+=" + increment_var + ")");
				For_notFound = 1;

			} else if ((For_notFound == 1) && (!lines[i].contains("{")) && (!lines[i].contains("}"))) {
				NormalConditions.add(lines[i]);
				for (int j = 1; j < increment_var; j++) {
					String replaced_increment = lines[i].replaceAll(unroll_letter, unroll_letter + "+" + j);
					NormalConditions.add(replaced_increment);
				}
			}
		}

		System.out.println(ForLoopSegments);
		System.out.println("{");
		for (String z : NormalConditions) {
			System.out.println(z);
		}
		System.out.println("}");

	}

	private void skewing() {

		if ((test2DI(stringsArrayList.get(4)) == true) && (test2DJ(stringsArrayList.get(4)) == true)) {
			System.out.println("skewing...");
		} else {
			System.out.println("cannot skew!");
			return;
		}

		// first for loop
		String I = Character.toString(getIterationVariable(stringsArrayList.get(1).get(0)));
		String P = (getStartingIterationValue(stringsArrayList.get(1).get(0)));
		String N = (getMaxIterationValue(stringsArrayList.get(1).get(0)));
		String X = (getIterationIncrement(stringsArrayList.get(1).get(0)));

		// second for loop
		String J = Character.toString(getIterationVariable(stringsArrayList.get(3).get(0)));
		String Q = (getStartingIterationValue(stringsArrayList.get(3).get(0)));
		String M = (getMaxIterationValue(stringsArrayList.get(3).get(0)));
		String Y = (getIterationIncrement(stringsArrayList.get(3).get(0)));

		String Qplusplus = Integer.toString(Integer.parseInt(Q) + 1);
		String MplusN = Integer.toString(Integer.parseInt(M) + Integer.parseInt(N));

		stringsArrayList.get(1).set(0, ("for(intj=" + Qplusplus + ";j<" + MplusN + ";" + Y + "){"));
		stringsArrayList.get(3).set(0,
				("for(inti=Math.max(" + P + ",j-" + M + ");" + I + "<Math.min(" + N + ",j-1);" + X + "){"));
		for (int ii = 0; ii < stringsArrayList.get(4).size(); ii++) {
			stringsArrayList.get(4).set(ii, (stringsArrayList.get(4)).get(ii).replaceAll(J, "j-" + I));
		}

		System.out.println(stringsArrayList);

	}

	private void write_to_file() {

		inputString = inputTextField.getText();
		processString(inputString);
		outputTextField.setText(inputString);

		try (FileWriter fw = new FileWriter("Loop_to_check.java", false);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println("package main;");
			out.println("public class Loop_to_check {");
			out.println("public class CheckCode {");
			out.println("public void main (String [] args) {");

			out.println("int[] a = new int[100];");
			out.println("int[] b = new int[100];");
			out.println("int[] c = new int[100];");
			out.println("int[][] x = new int[100][100];");
			out.println("int[][] y = new int[100][100];");
			out.println("int[][] z = new int[100][100];");

			out.println("\n");
			out.println("\n");

			out.println(inputString);

			out.println("\n");
			out.println("\n");

			out.println("}");
			out.println("}");
			out.println("}");

			// more code

			// more code
		} catch (IOException e1) {
			// exception handling left as an exercise for the reader
		}

		List<String> errorMessages = JavaSyntaxChecker.check("Loop_to_check.java");
		if (!(errorMessages.isEmpty())) {
			JOptionPane.showMessageDialog(frame, errorMessages);
		}

	}

	private static boolean checkBounds(String currentTemp1, String currentTemp2, String forLine){

//		String input = getIterationIncrement(forLine);
//		input = input.substring(input.indexOf(";") + 1);
//		System.out.println(input);
//		
//		for(int i = 0; i < 10; i = i/1){
//			
//		}

//		System.out.println(currentTemp1 + " " + currentTemp2);
		currentTemp1 = currentTemp1.substring(currentTemp1.indexOf("[")+1,currentTemp1.indexOf("]"));
		currentTemp2 = currentTemp2.substring(currentTemp2.indexOf("[")+1,currentTemp2.indexOf("]"));

		
		
		int inc = 0;
		
		if (getIterationIncrementOnly(forLine).contains("++")){
			inc = 1;
		} else if (getIterationIncrementOnly(forLine).contains("--")){
			inc = -1;
		} else if ((getIterationIncrementOnly(forLine).contains("+="))){
			inc = Integer.parseInt((getIterationIncrementOnly(forLine)).substring(getIterationIncrementOnly(forLine).indexOf("=") + 1));
		} else if ((getIterationIncrementOnly(forLine).contains("-="))){
			inc = Integer.parseInt((getIterationIncrementOnly(forLine)).substring(getIterationIncrementOnly(forLine).indexOf("=") + 1));
		}
		
		
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
		
		int a1[] = new int[Integer.parseInt(getMaxIterationValue(forLine))];
		int a2[] = new int[Integer.parseInt(getMaxIterationValue(forLine))];
		
		for (int i = Integer.parseInt(getStartingIterationValue(forLine)); i < Integer.parseInt(getMaxIterationValue(forLine)); i+=inc){
			try {
				a1[i] = ((int) engine.eval(currentTemp1.replaceAll("i", Integer.toString(i))));
				a2[i] = ((int) engine.eval(currentTemp2.replaceAll("i", Integer.toString(i))));
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return findDupes(a1,a2);
	}
	
	private static boolean findDupes(int[] a, int[] b) {
	    HashSet<Integer> map = new HashSet<Integer>();
	    for (int i : a)
	        map.add(i);
	    for (int i : b) {
	        if (map.contains(i))
//	        	System.out.println("leanign....")s;
	        	return true;
	            // found duplicate!   
	    }
	    
	    return false;
	}
	
	
	private void reversal() {
		int for_Found = 0;
		String tempLine = "";
		String[] forLoopSplit = new String[3];
		String i_value = "";
		String N_value = "";
		String initialValue = "";
		boolean incrementing = true;

		List<String> initilizerConditions = new ArrayList<String>();
		List<String> NormalConditions = new ArrayList<String>();

		inputString = inputTextField.getText();
		String[] lines = inputTextField.getText().split("\\n");

		for (int i = 0; i < (lines.length); i++) {
			tempLine = lines[i].replaceAll("\\s", "");
			if (tempLine.contains("for(")) {
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

			} else if ((for_Found == 1) && (!tempLine.contains("{")) && (!tempLine.contains("}"))) {
				NormalConditions.add(lines[i]);
			} else {
				if ((!tempLine.contains("{")) && (!tempLine.contains("}"))) {
					initilizerConditions.add(lines[i]);
				}
			}
		}

		for (String z : initilizerConditions) {
			System.out.println(z);
		}

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
	}

	private static ArrayList<String> splitLine(String string) {

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

	private static ArrayList<String> getGCDParams_ifNumberOnRightofj(String s) {

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

	private static ArrayList<String> getGCDParams_ifNumberOnLeftofj(String s) {
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

	private static ArrayList<String> getGCDParams_forJ(String s) {

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

	private static boolean test(ArrayList<String> testList, ArrayList<String> forLine) {

		// ArrayList<String> testList = new ArrayList<String>();
		ArrayList<ArrayList<String>> twoDArrayList = new ArrayList<ArrayList<String>>();
		int tru = 0;
		int fals = 0;
		boolean returned;
		//
		// String input1 = "a[2*i+1]=a[7*i+4]+3";
		// String input2 = "b[3*i+5]=a[i+2]+3";
		// String input3 = "c[i+5]=b[4*i+2]+3";
		//
		// testList.add(input1);
		// testList.add(input2);
		// testList.add(input3);
		//
		for (int i = 0; i < testList.size(); i++) {
			twoDArrayList.add(splitLine(testList.get(i)));
		}

		// System.out.println(twoDArrayList);

		for (int i = 0; i < testList.size(); i++) {
			String currentTemp1 = twoDArrayList.get(i).get(0);

			for (int j = 0; j < twoDArrayList.size(); j++) {
				// if (j == i){
				// continue;
				// }

				ArrayList<String> subRow = twoDArrayList.get(j);

				for (int k = 1; k < subRow.size(); k++) {
					// gcd test
					String currentTemp2 = twoDArrayList.get(j).get(k);

					if (currentTemp1.charAt(0) != currentTemp2.charAt(0)) {
						continue;
					}
					returned = (calculate_gcd_dependence(getGCDParams_forI(currentTemp1),
							getGCDParams_forI(currentTemp2)));
					if (returned) {
						long startTime = System.nanoTime();
						boolean r = checkBounds(currentTemp1, currentTemp2, forLine.get(0));
						long endTime = System.nanoTime();

						System.out.println((endTime - startTime)/(double)1000000000); 
						
						tru++;
					} else {
						fals++;
					}
					// System.out.println(currentTemp1 + " " + currentTemp2);
					// System.out.println(calculate_gcd_dependence(getGCDParams_forI(currentTemp1),
					// getGCDParams_forI(currentTemp2)));
				}
			}
		}
		if (tru > 0) {
			return true;
		} else {
			return false;
		}

	}
	private static boolean test2DI(ArrayList<String> testList) {

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
					// gcd test
					String currentTemp2 = twoDArrayList.get(j).get(k);
					if (currentTemp1.charAt(0) != currentTemp2.charAt(0)) {
						continue;
					}
					returned = (calculate_gcd_dependence(getGCDParams_forI(currentTemp1),
							getGCDParams_forI(currentTemp2)));
					if (returned) {
						tru++;
					} else {
						fals++;
					}
				}
			}
		}
		if (tru > 0) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean test2DJ(ArrayList<String> testList) {

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
					// gcd test
					String currentTemp2 = twoDArrayList.get(j).get(k);

					if (currentTemp1.charAt(0) != currentTemp2.charAt(0)) {
						continue;
					}
					returned = (calculate_gcd_dependence(getGCDParams_forJ(currentTemp1),
							getGCDParams_forJ(currentTemp2)));
					if (returned) {
						tru++;
					} else {
						fals++;
					}
				}
			}
		}
		if (tru > 0) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean calculate_gcd_dependence(ArrayList<String> s1, ArrayList<String> s2) {

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

	private static ArrayList<String> getGCDParams_forI(String s) {

		if (s.contains("[i")) {
			return getGCDParams_ifNumberOnRightofi(s);
		} else {
			return getGCDParams_ifNumberOnLeftofi(s);
		}

	}

	private static ArrayList<String> getGCDParams_ifNumberOnRightofi(String s) {

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

	private static ArrayList<String> getGCDParams_ifNumberOnLeftofi(String s) {

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

	private static int gcd(int a, int b) {
		if (a == 0 || b == 0)
			return a + b; // base case
		return gcd(b, a % b);
	}

}
