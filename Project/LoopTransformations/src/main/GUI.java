package main;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.BorderFactory;
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
import javax.swing.border.Border;

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
		frmLoopOptimization.getContentPane().setBackground(Color.ORANGE);
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
		inputTextField.setBounds(25, 38, 440, 622);
		frmLoopOptimization.getContentPane().add(inputTextField);
		inputTextField.setColumns(10);
		Border inputBorder = BorderFactory.createLineBorder(Color.BLACK, 5);
		inputTextField.setBorder(inputBorder);
		frmLoopOptimization.getContentPane().add(inputTextField);


		outputTextField = new JTextArea();
		outputTextField.setBounds(730, 38, 440, 622);
		frmLoopOptimization.getContentPane().add(outputTextField);
		outputTextField.setColumns(10);
		Border OutputBorder = BorderFactory.createLineBorder(Color.BLACK, 5);
		outputTextField.setBorder(OutputBorder);
		frmLoopOptimization.getContentPane().add(outputTextField);

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
		UnrollBtn.setForeground(Color.BLACK);
		UnrollBtn.setBounds(492, 54, 125, 43);
		frmLoopOptimization.getContentPane().add(UnrollBtn);
		Border UnrollBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
		UnrollBtn.setBorder(UnrollBorder);
		frmLoopOptimization.getContentPane().add(UnrollBtn);

		JButton InterchangeBtn = new JButton("Interchange");
		InterchangeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startTransform(1);
			}
		});
		InterchangeBtn.setMinimumSize(new Dimension(100, 25));
		InterchangeBtn.setMaximumSize(new Dimension(100, 25));
		InterchangeBtn.setForeground(Color.BLACK);
		InterchangeBtn.setFont(new Font("Arial Black", Font.BOLD, 11));
		InterchangeBtn.setBounds(492, 142, 125, 43);
		frmLoopOptimization.getContentPane().add(InterchangeBtn);
		Border InterchangeBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
		InterchangeBtn.setBorder(InterchangeBorder);
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
		FissionBtn.setForeground(Color.BLACK);
		FissionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startTransform(2);
			}
		});
		FissionBtn.setBounds(492, 235, 125, 43);
		frmLoopOptimization.getContentPane().add(FissionBtn);
		Border FissionBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
		FissionBtn.setBorder(FissionBorder);
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
		FusionBtn.setForeground(Color.BLACK);
		FusionBtn.setBounds(492, 335, 125, 43);
		frmLoopOptimization.getContentPane().add(FusionBtn);
		Border FusionBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
		FusionBtn.setBorder(FusionBorder);
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
		SkewingBtn.setForeground(Color.BLACK);
		SkewingBtn.setBounds(492, 431, 125, 43);
		frmLoopOptimization.getContentPane().add(SkewingBtn);
		Border SkewingBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
		SkewingBtn.setBorder(SkewingBorder);
		frmLoopOptimization.getContentPane().add(SkewingBtn);

		JButton Skewing_info = new JButton("?");
		Skewing_info.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Skewing_info.setForeground(Color.BLUE);
		Skewing_info.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Skewing_info.setBounds(659, 442, 50, 23);
		frmLoopOptimization.getContentPane().add(Skewing_info);

		JButton InversionBtn = new JButton("Inversion");
		InversionBtn.setFont(new Font("Arial Black", Font.BOLD, 11));
		InversionBtn.setForeground(Color.BLACK);
		InversionBtn.setBounds(492, 524, 125, 43);
		InversionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startTransform(5);
			}
		});
		frmLoopOptimization.getContentPane().add(InversionBtn);
		Border InversionBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
		InversionBtn.setBorder(InversionBorder);
		frmLoopOptimization.getContentPane().add(InversionBtn);

		JButton Inversion_info = new JButton("?");
		Inversion_info.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Inversion_info.setForeground(Color.BLUE);
		Inversion_info.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Inversion_info.setBounds(660, 534, 50, 23);
		frmLoopOptimization.getContentPane().add(Inversion_info);

		JButton ReversalBtn = new JButton("Reversal");
		ReversalBtn.setFont(new Font("Arial Black", Font.BOLD, 11));
		ReversalBtn.setForeground(Color.BLACK);
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
		Border ReversalBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
		ReversalBtn.setBorder(ReversalBorder);
		frmLoopOptimization.getContentPane().add(ReversalBtn);
	}

	private void startTransform(int x) {
		// TODO Auto-generated method stub
		SwingWorker<String, String> worker = new SwingWorker<String, String>(){

			@Override
			protected String doInBackground() throws Exception {
				// TODO Auto-generated method stub
				
				String returnString = "";
				
				write_to_file();
				
				switch(x){    
				case 0:    
					 //code to be executed;   
					Fission fission = new Fission();
					publish("xoxo");
					returnString = (fission.compute(stringsArrayList, inputString)).toString();
					
				break;  //optional  
				case 1:    
					 //code to be executed;   
					Fusion fusion = new Fusion();
					publish(fusion.compute(stringsArrayList, inputString));
				break;  //optional  
				case 2:    
						 //code to be executed;  
					Unrolling unrolling = new Unrolling();
					publish(unrolling.compute(stringsArrayList, inputString));
				break;  //optional  
				case 3:    
						 //code to be executed; 
					Interchange interchange = new Interchange();
					publish(interchange.compute(stringsArrayList, inputString));
				break;  //optional  
				case 4:    
							 //code to be executed;    
					Skewing skewing = new Skewing();
					publish(skewing.compute(stringsArrayList, inputString));
				break;  //optional  
				case 5:    
							 //code to be executed;    
					Inversion inversion = new Inversion();
					publish(inversion.compute(stringsArrayList, inputString));
					System.out.println("I AM HERE");
				break;  //optional  
				case 6:    
								 //code to be executed;  
					Reversal reversal = new Reversal();
					publish(reversal.compute(stringsArrayList, inputString));
				break;  //optional  								    
				default:      
				}    
				
				
				
//				for(int i = 0; i < 3; i++){
//					Thread.sleep(1000);
//					//System.out.println("Hello: " + i);
//					publish(i);
//				}
				
				return returnString;
			}
			
//			@Override
//			protected void process(List<Integer> chunks){
//				int value = chunks.get(chunks.size() -1);
//				
//				outputTextField.setText("CurrentValue: " + value);
//			}
			
			@Override
			protected void process(List<String> chunks){
				String value = chunks.get(chunks.size() -1);
				
//				outputTextField.setText(value);
			}			
			
			@Override
			protected void done(){
				try {
					String status = get();
					outputTextField.setText(status);
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
//		reversal();
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

	

}
