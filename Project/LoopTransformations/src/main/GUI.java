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
import java.util.concurrent.ExecutionException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

public class GUI {

	JFrame frmLoopOptimization;
	private JTextArea inputTextField;
	private JTextArea outputTextField;
	private String inputString;
	boolean ArrayFound = false;
	private JavaSyntaxChecker checker;
	private ArrayList<ArrayList<String>> stringsArrayList = new ArrayList<ArrayList<String>>();

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLoopOptimization = new JFrame();
		frmLoopOptimization.setMaximumSize(new Dimension(1200, 700));
		frmLoopOptimization.setMinimumSize(new Dimension(1200, 700));
		frmLoopOptimization.setTitle("Loop Optimization");
		frmLoopOptimization.setResizable(false);
		frmLoopOptimization.getContentPane().setFont(new Font("Arial Black", Font.BOLD, 11));
		frmLoopOptimization.getContentPane().setForeground(new Color(0, 0, 0));
		frmLoopOptimization.setBounds(100, 100, 856, 477);
		frmLoopOptimization.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoopOptimization.getContentPane().setLayout(null);

		inputTextField = new JTextArea();
		inputTextField.setBounds(10, 38, 440, 622);
		frmLoopOptimization.getContentPane().add(inputTextField);
		inputTextField.setColumns(10);
		// ====================================================================================================================

		// ====================================================================================================================

		outputTextField = new JTextArea();
		outputTextField.setBounds(744, 38, 440, 622);
		frmLoopOptimization.getContentPane().add(outputTextField);
		outputTextField.setColumns(10);

		JLabel inputTitle = new JLabel("INPUT CODE");
		inputTitle.setForeground(Color.BLACK);
		inputTitle.setFont(new Font("Arial Black", Font.BOLD, 15));
		inputTitle.setBounds(159, 13, 125, 14);
		frmLoopOptimization.getContentPane().add(inputTitle);

		JLabel outputTitle = new JLabel("TRASFORMED RESULT");
		outputTitle.setForeground(Color.BLACK);
		outputTitle.setFont(new Font("Arial Black", Font.BOLD, 15));
		outputTitle.setBounds(887, 13, 222, 14);
		frmLoopOptimization.getContentPane().add(outputTitle);

		JButton Unroll_info = new JButton("?");
		Unroll_info.setForeground(Color.BLUE);
		Unroll_info.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Unroll_info.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		Unroll_info.setBounds(660, 65, 50, 23);
		frmLoopOptimization.getContentPane().add(Unroll_info);

		JButton UnrollBtn = new JButton("Unroll");
		UnrollBtn.setFont(new Font("Arial Black", Font.BOLD, 11));
		UnrollBtn.setMinimumSize(new Dimension(100, 25));
		UnrollBtn.setMaximumSize(new Dimension(100, 25));
		UnrollBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startTransform(0);
			}
		});
		UnrollBtn.setForeground(Color.BLUE);
		UnrollBtn.setBounds(492, 54, 125, 43);
		frmLoopOptimization.getContentPane().add(UnrollBtn);

		JButton InterchangeBtn = new JButton("Interchange");
		InterchangeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startTransform(1);
			}
		});
		InterchangeBtn.setMinimumSize(new Dimension(100, 25));
		InterchangeBtn.setMaximumSize(new Dimension(100, 25));
		InterchangeBtn.setForeground(Color.BLUE);
		InterchangeBtn.setFont(new Font("Arial Black", Font.BOLD, 11));
		InterchangeBtn.setBounds(492, 142, 125, 43);
		frmLoopOptimization.getContentPane().add(InterchangeBtn);

		JButton Interchange_info = new JButton("?");
		Interchange_info.setForeground(Color.BLUE);
		Interchange_info.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Interchange_info.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Interchange_info.setBounds(660, 152, 50, 23);
		frmLoopOptimization.getContentPane().add(Interchange_info);

		JButton FissionBtn = new JButton("Fission");
		FissionBtn.setFont(new Font("Arial Black", Font.BOLD, 11));
		FissionBtn.setForeground(Color.BLUE);
		FissionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startTransform(2);
			}
		});
		FissionBtn.setBounds(492, 235, 125, 43);
		frmLoopOptimization.getContentPane().add(FissionBtn);

		JButton Fission_info = new JButton("?");
		Fission_info.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Fission_info.setForeground(Color.BLUE);
		Fission_info.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Fission_info.setBounds(660, 245, 50, 23);
		frmLoopOptimization.getContentPane().add(Fission_info);

		JButton FusionBtn = new JButton("Fusion");
		FusionBtn.setFont(new Font("Arial Black", Font.BOLD, 11));
		FusionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startTransform(3);
			}
		});
		FusionBtn.setForeground(Color.BLUE);
		FusionBtn.setBounds(492, 335, 125, 43);
		frmLoopOptimization.getContentPane().add(FusionBtn);

		JButton Fusion_info = new JButton("?");
		Fusion_info.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Fusion_info.setForeground(Color.BLUE);
		Fusion_info.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Fusion_info.setBounds(660, 345, 50, 23);
		frmLoopOptimization.getContentPane().add(Fusion_info);

		JButton SkewingBtn = new JButton("Skewing");
		SkewingBtn.setFont(new Font("Arial Black", Font.BOLD, 11));
		SkewingBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startTransform(4);
			}
		});
		SkewingBtn.setForeground(Color.BLUE);
		SkewingBtn.setBounds(492, 431, 125, 43);
		frmLoopOptimization.getContentPane().add(SkewingBtn);

		JButton btnNewButton_5 = new JButton("?");
		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_5.setForeground(Color.BLUE);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_5.setBounds(660, 441, 50, 23);
		frmLoopOptimization.getContentPane().add(btnNewButton_5);

		JButton InversionBtn = new JButton("Inversion");
		InversionBtn.setFont(new Font("Arial Black", Font.BOLD, 11));
		InversionBtn.setForeground(Color.BLUE);
		InversionBtn.setBounds(492, 524, 125, 43);
		InversionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startTransform(5);
			}
		});
		frmLoopOptimization.getContentPane().add(InversionBtn);

		JButton Inversion_info = new JButton("?");
		Inversion_info.setForeground(Color.BLUE);
		Inversion_info.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Inversion_info.setBounds(660, 534, 50, 23);
		frmLoopOptimization.getContentPane().add(Inversion_info);

		JButton ReversalBtn = new JButton("Reversal");
		ReversalBtn.setFont(new Font("Arial Black", Font.BOLD, 11));
		ReversalBtn.setForeground(Color.BLUE);
		ReversalBtn.setBounds(492, 612, 125, 43);
		ReversalBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startTransform(6);
			}
		});
		frmLoopOptimization.getContentPane().add(ReversalBtn);

		JButton Reversal_info = new JButton("?");
		Reversal_info.setForeground(Color.BLUE);
		Reversal_info.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Reversal_info.setBounds(660, 622, 50, 23);
		frmLoopOptimization.getContentPane().add(Reversal_info);
	}

	private void startTransform(int x) {
		// TODO Auto-generated method stub
		System.out.println("start!");
		SwingWorker<Boolean, Integer> worker = new SwingWorker<Boolean, Integer>(){

			@Override
			protected Boolean doInBackground() throws Exception {
				// TODO Auto-generated method stub
				
				switch(x){    
				case 0:    
					 //code to be executed;   
					Fission fission = new Fission();
				break;  //optional  
				case 1:    
					 //code to be executed;   
					Fusion fusion = new Fusion();
				break;  //optional  
				case 2:    
						 //code to be executed;  
					Unrolling unrolling = new Unrolling();
				break;  //optional  
				case 3:    
						 //code to be executed; 
					Interchange interchange = new Interchange();
				break;  //optional  
				case 4:    
							 //code to be executed;    
					Skewing skewing = new Skewing();
				break;  //optional  
				case 5:    
							 //code to be executed;    
					Inversion inversion = new Inversion();
				break;  //optional  
				case 6:    
								 //code to be executed;  
					Reversal reversal = new Reversal();
				break;  //optional  								    
				default:      
				}    
				
				
				
				for(int i = 0; i < 3; i++){
					Thread.sleep(1000);
					//System.out.println("Hello: " + i);
					publish(i);
				}
				
				return true;
			}
			
			@Override
			protected void process(List<Integer> chunks){
				int value = chunks.get(chunks.size() -1);
				
				outputTextField.setText("CurrentValue: " + value);
			}
			
			@Override
			protected void done(){
				try {
					Boolean status = get();
					outputTextField.setText("Completed! "+ x + " " + status);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
						
			}
			
		};
		worker.execute();
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

		// fission();
		// fusion();
		// interchange();
		// inversion();
		// unrolling();
		// skewing();
		reversal();
	}

	public void fission() {
		int for_Found = 0;
		String tempLine = "";

		if (test(stringsArrayList.get(2), stringsArrayList.get(1))) {
			System.out.println("cannot fission!");
			return;
		}

		List<String> initilizerConditions = new ArrayList<String>();
		List<String> NormalConditions = new ArrayList<String>();
		String mainForLoop = "";

		// Reading the text form the input text box.
		inputString = inputTextField.getText();
		// Divide the user input textbox by each line and storing each line in
		// the String array called lines.
		String[] lines = inputTextField.getText().split("\\n");

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
			System.out.println(z);
		}
		if (mainForLoop.contains("{")) {
			System.out.println(mainForLoop);
		} else {
			System.out.println(mainForLoop);
			System.out.println("{");
		}
		// for each condition , print it in a seperate for loop.
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

		// ArrayList<String> add = new ArrayList<String>();
		// add.add("}");
		// stringsArrayList.add(add);

		for (int i = 1; i < stringsArrayList.size(); i += 4) {
			for (int j = i + 4; j < stringsArrayList.size(); j += 4) {

				if ((stringsArrayList.get(i).isEmpty()) || (stringsArrayList.get(j).isEmpty())) {
					continue;
				}
				if (checkIfEqual(stringsArrayList.get(i).get(0), (stringsArrayList.get(j)).get(0))) {
					// System.out.println(stringsArrayList.get(i) + " " +
					// (stringsArrayList.get(j)));

					ArrayList<String> testArrayList = new ArrayList<String>();
					testArrayList.add(stringsArrayList.get(i + 1).get(0));
					testArrayList.add(stringsArrayList.get(j + 1).get(0));
					// System.out.println(testArrayList);

					// System.out.println(test(testArrayList,
					// stringsArrayList.get(i)));
					if (test(testArrayList, stringsArrayList.get(i))) {
						continue;
					}

					// if(){
					// stringsArrayList.set(i+1, stringsArrayList.get(i+1) +
					// stringsArrayList.get(j+1));
					// System.out.println(stringsArrayList);
					String stringToAdd = stringsArrayList.get(j + 1).get(0);
					stringsArrayList.get(i + 1).add(0, stringToAdd);

					stringsArrayList.get(j).clear();
					stringsArrayList.get(j + 1).clear();
					stringsArrayList.get(j + 2).clear();
					// System.out.println(stringsArrayList);

					// }
				}

			}
		}

		System.out.println(stringsArrayList);

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

		System.out.println(stringsArrayList);
		// test with 2D
		// find if there is dependence in the i loops

		// if there is no dependence in the i loops then apply the thing below

	}

	private void inversion() {
		String whileLoopContents = "";
		String[] forLoopSplit = new String[3];
		int for_Found = 0;
		int while_Found = 0;
		List<String> NormalConditions = new ArrayList<String>();
		List<String> initilizerConditions = new ArrayList<String>();
		String tempLine = "";

		// Reading the text form the input text box.
		inputString = inputTextField.getText();
		// Divide the user input textbox by each line and storing each line in
		// the String array called lines.
		String[] lines = inputTextField.getText().split("\\n");

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
			System.out.println(z);
		}

		System.out.println("\n");
		if (while_Found == 1) {
			System.out.println("if(" + whileLoopContents + "){");
		} else if (for_Found == 1) {
			System.out.println("if(" + forLoopSplit[1] + "){");
		}
		System.out.println("do{");
		for (String z : NormalConditions) {
			System.out.println(z);
		}

		if (while_Found == 1) {
			System.out.println("} while (" + whileLoopContents + ") ;");
		} else if (for_Found == 1) {
			System.out.println("} while (" + forLoopSplit[1] + ") ;");
		}
		System.out.println("}");

	}

	private void unrolling() {
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
		inputString = inputTextField.getText();
		// Divide the user input textbox by each line and storing each line in
		// the String array called lines.
		String[] lines = inputTextField.getText().split("\\n");

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
					System.out.println("Check your For loop's syntax. Increment to N value must either be < , <= , = ");
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
			System.out.println(z);
		}

		System.out.println(ForLoopSegments);
		System.out.println("{");
		for (String z : normalConditions) {
			System.out.println(z);
		}
		System.out.println("}");
		for (String z : remainderConditions) {
			System.out.println(z);
		}

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

		// Reading the text form the input text box.
		inputString = inputTextField.getText();
		// Divide the user input textbox by each line and storing each line in
		// the String array called lines.
		String[] lines = inputTextField.getText().split("\\n");

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

	public static String getIterationIncrementOnly(String input) {
		input = getIterationIncrement(input);
		return input.substring(input.indexOf(";") + 1);
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
			out.println("int i=0;");
			out.println("int j=0;");
			out.println("int k=0;");
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
			JOptionPane.showMessageDialog(frmLoopOptimization, errorMessages);
		}

	}

	private static boolean checkBounds(String currentTemp1, String currentTemp2, String forLine) {

		// String input = getIterationIncrement(forLine);
		// input = input.substring(input.indexOf(";") + 1);
		// System.out.println(input);
		//
		// for(int i = 0; i < 10; i = i/1){
		//
		// }

		// System.out.println(currentTemp1 + " " + currentTemp2);
		currentTemp1 = currentTemp1.substring(currentTemp1.indexOf("[") + 1, currentTemp1.indexOf("]"));
		currentTemp2 = currentTemp2.substring(currentTemp2.indexOf("[") + 1, currentTemp2.indexOf("]"));

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

	private static boolean findDupes(int[] a, int[] b) {

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
					return true;
					// }
				}
			}
		}

		// HashSet<Integer> map = new HashSet<Integer>();
		// for (int i : a)
		// map.add(i);
		// for (int i : b) {
		// if (map.contains(i))
		// // System.out.println("leanign....")s;
		// return true;
		// // found duplicate!
		// }

		return false;
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
						if (checkBounds(currentTemp1, currentTemp2, forLine.get(0))) {
							return true;
						}
						// tru++;
					} else {
						// fals++;
					}
					// System.out.println(currentTemp1 + " " + currentTemp2);
					// System.out.println(calculate_gcd_dependence(getGCDParams_forI(currentTemp1),
					// getGCDParams_forI(currentTemp2)));
				}
			}
		}

		// if (tru > 0) {
		// return true;
		// } else {
		// return false;
		// }

		return false;
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
