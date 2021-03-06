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

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.Border;
import javax.swing.JProgressBar;

public class GUI extends JPanel{

	JFrame frmLoopOptimization;
	JFrame unrollHelp;
	JFrame interchangeHelp;
	JFrame fusionHelp;
	JFrame fissionHelp;
	JFrame skewingHelp;
	JFrame inversionHelp;
	JFrame reversalHelp;
	private JTextArea inputTextField;
	private JTextArea outputTextField;
	private JProgressBar progressBar;
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
		
		
		interchangeHelp = new JFrame();
		ImageIcon InterchangeIcon = new ImageIcon(this.getClass().getResource("/images/Use.jpg"));
		interchangeHelp.setTitle("Interchage Help");
		interchangeHelp.setResizable(true);
		interchangeHelp.setBounds(0,0, 800, 600);
		interchangeHelp.setLocationRelativeTo(null);
		interchangeHelp.setVisible( true ); 
		interchangeHelp.setAlwaysOnTop(true);
		interchangeHelp.add(new JLabel(InterchangeIcon));
		
		
		
		frmLoopOptimization = new JFrame();
		frmLoopOptimization.getContentPane().setBackground(Color.ORANGE);
		frmLoopOptimization.setMaximumSize(new Dimension(1200, 700));
		frmLoopOptimization.setMinimumSize(new Dimension(1200, 700));
		frmLoopOptimization.setTitle("Loop Optimization");
		frmLoopOptimization.setResizable(false);
		frmLoopOptimization.getContentPane().setFont(new Font("Arial Black", Font.BOLD, 11));
		frmLoopOptimization.getContentPane().setForeground(new Color(0, 0, 0));
		frmLoopOptimization.setBounds(10, 10, 856, 477);
		frmLoopOptimization.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoopOptimization.getContentPane().setLayout(null);
		frmLoopOptimization.setLocationRelativeTo(null);
		inputTextField = new JTextArea();
		inputTextField.setBounds(25, 38, 440, 622);
		inputTextField.setFont(new Font("Arial", Font.PLAIN, 24));
		frmLoopOptimization.getContentPane().add(inputTextField);
		inputTextField.setColumns(10);
		Border inputBorder = BorderFactory.createLineBorder(Color.BLACK, 5);
		inputTextField.setBorder(inputBorder);
		frmLoopOptimization.getContentPane().add(inputTextField);


		outputTextField = new JTextArea();
		outputTextField.setBounds(730, 38, 440, 622);
		outputTextField.setFont(new Font("Arial", Font.PLAIN, 24));
		frmLoopOptimization.getContentPane().add(outputTextField);
		outputTextField.setColumns(10);
		Border OutputBorder = BorderFactory.createLineBorder(Color.BLACK, 5);
		outputTextField.setBorder(OutputBorder);
		frmLoopOptimization.getContentPane().add(outputTextField);

		JLabel inputTitle = new JLabel("INPUT CODE");
		inputTitle.setForeground(Color.BLACK);
		inputTitle.setFont(new Font("Arial Black", Font.BOLD, 15));
		inputTitle.setBounds(194, 13, 125, 14);
		frmLoopOptimization.getContentPane().add(inputTitle);

		JLabel outputTitle = new JLabel("TRASFORMED RESULT");
		outputTitle.setForeground(Color.BLACK);
		outputTitle.setFont(new Font("Arial Black", Font.BOLD, 15));
		outputTitle.setBounds(853, 13, 222, 14);
		frmLoopOptimization.getContentPane().add(outputTitle);

  
		
		JButton Unroll_info = new JButton("?");
		Unroll_info.setForeground(Color.BLUE);
		Unroll_info.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Unroll_info.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				unrollHelp = new JFrame();
				ImageIcon image = new ImageIcon(this.getClass().getResource("/images/Unrolling.jpg"));
				unrollHelp.setTitle("Unrolling Help");
				unrollHelp.setResizable(true);
				unrollHelp.setBounds(0,0, 900, 800);
				unrollHelp.setVisible( true ); 
				unrollHelp.add(new JLabel(image));
			}
		});
		Unroll_info.setBounds(660, 221, 50, 23);
		frmLoopOptimization.getContentPane().add(Unroll_info);

		

		JButton UnrollBtn = new JButton("Unroll");
		UnrollBtn.setFont(new Font("Arial Black", Font.BOLD, 11));
		UnrollBtn.setMinimumSize(new Dimension(100, 25));
		UnrollBtn.setMaximumSize(new Dimension(100, 25));
		UnrollBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startTransform(2);
			}
		});
		UnrollBtn.setForeground(Color.BLACK);
		UnrollBtn.setBounds(492, 210, 125, 43);
		frmLoopOptimization.getContentPane().add(UnrollBtn);
		Border UnrollBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
		UnrollBtn.setBorder(UnrollBorder);
		frmLoopOptimization.getContentPane().add(UnrollBtn);

		JButton InterchangeBtn = new JButton("Interchange");
		InterchangeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startTransform(3);
			}
		});
		InterchangeBtn.setMinimumSize(new Dimension(100, 25));
		InterchangeBtn.setMaximumSize(new Dimension(100, 25));
		InterchangeBtn.setForeground(Color.BLACK);
		InterchangeBtn.setFont(new Font("Arial Black", Font.BOLD, 11));
		InterchangeBtn.setBounds(492, 302, 125, 43);
		frmLoopOptimization.getContentPane().add(InterchangeBtn);
		Border InterchangeBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
		InterchangeBtn.setBorder(InterchangeBorder);
		frmLoopOptimization.getContentPane().add(InterchangeBtn);

		JButton Interchange_info = new JButton("?");
		Interchange_info.setForeground(Color.BLUE);
		Interchange_info.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Interchange_info.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				interchangeHelp = new JFrame();
				ImageIcon InterchangeIcon = new ImageIcon(this.getClass().getResource("/images/Interchange.jpg"));
				interchangeHelp.setTitle("Interchage Help");
				interchangeHelp.setResizable(true);
				interchangeHelp.setBounds(0,0, 900, 800);
				interchangeHelp.setVisible( true ); 
				interchangeHelp.add(new JLabel(InterchangeIcon));
			}
		});
		Interchange_info.setBounds(660, 313, 50, 23);
		frmLoopOptimization.getContentPane().add(Interchange_info);

		JButton FissionBtn = new JButton("Fission");
		FissionBtn.setFont(new Font("Arial Black", Font.BOLD, 11));
		FissionBtn.setForeground(Color.BLACK);
		FissionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startTransform(0);
			}
		});
		FissionBtn.setBounds(492, 43, 125, 43);
		frmLoopOptimization.getContentPane().add(FissionBtn);
		Border FissionBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
		FissionBtn.setBorder(FissionBorder);
		frmLoopOptimization.getContentPane().add(FissionBtn);

		JButton Fission_info = new JButton("?");
		Fission_info.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Fission_info.setForeground(Color.BLUE);
		Fission_info.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fissionHelp = new JFrame();
				ImageIcon fissionIcon = new ImageIcon(this.getClass().getResource("/images/Fission.jpg"));
				fissionHelp.setTitle("Fission Help");
				fissionHelp.setResizable(true);
				fissionHelp.setBounds(0,0, 900, 800);
				fissionHelp.setVisible( true ); 
				fissionHelp.add(new JLabel(fissionIcon));
			}
		});
		Fission_info.setBounds(660, 54, 50, 23);
		frmLoopOptimization.getContentPane().add(Fission_info);

		JButton FusionBtn = new JButton("Fusion");
		FusionBtn.setFont(new Font("Arial Black", Font.BOLD, 11));
		FusionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startTransform(1);
			}
		});
		FusionBtn.setForeground(Color.BLACK);
		FusionBtn.setBounds(492, 128, 125, 43);
		frmLoopOptimization.getContentPane().add(FusionBtn);
		Border FusionBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
		FusionBtn.setBorder(FusionBorder);
		frmLoopOptimization.getContentPane().add(FusionBtn);

		JButton Fusion_info = new JButton("?");
		Fusion_info.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Fusion_info.setForeground(Color.BLUE);
		Fusion_info.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fusionHelp = new JFrame();
				ImageIcon InterchangeIcon = new ImageIcon(this.getClass().getResource("/images/Fusion.jpg"));
				fusionHelp.setTitle("Interchage Help");
				fusionHelp.setResizable(true);
				fusionHelp.setBounds(0,0, 900, 800);
				fusionHelp.setVisible( true ); 
				fusionHelp.add(new JLabel(InterchangeIcon));
			}
		});
		Fusion_info.setBounds(660, 139, 50, 23);
		frmLoopOptimization.getContentPane().add(Fusion_info);

		JButton SkewingBtn = new JButton("Skewing");
		SkewingBtn.setFont(new Font("Arial Black", Font.BOLD, 11));
		SkewingBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startTransform(4);
			}
		});
		SkewingBtn.setForeground(Color.BLACK);
		SkewingBtn.setBounds(492, 392, 125, 43);
		frmLoopOptimization.getContentPane().add(SkewingBtn);
		Border SkewingBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
		SkewingBtn.setBorder(SkewingBorder);
		frmLoopOptimization.getContentPane().add(SkewingBtn);

		JButton Skewing_info = new JButton("?");
		Skewing_info.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Skewing_info.setForeground(Color.BLUE);
		Skewing_info.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				skewingHelp = new JFrame();
				ImageIcon InterchangeIcon = new ImageIcon(this.getClass().getResource("/images/LoopSkewing.jpg"));
				skewingHelp.setTitle("Interchage Help");
				skewingHelp.setResizable(true);
				skewingHelp.setBounds(0,0, 900, 800);
				skewingHelp.setVisible( true ); 
				skewingHelp.add(new JLabel(InterchangeIcon));
			}
		});
		Skewing_info.setBounds(660, 403, 50, 23);
		frmLoopOptimization.getContentPane().add(Skewing_info);

		JButton InversionBtn = new JButton("Inversion");
		InversionBtn.setFont(new Font("Arial Black", Font.BOLD, 11));
		InversionBtn.setForeground(Color.BLACK);
		InversionBtn.setBounds(492, 475, 125, 43);
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
				inversionHelp = new JFrame();
				ImageIcon InterchangeIcon = new ImageIcon(this.getClass().getResource("/images/Inversion.jpg"));
				inversionHelp.setTitle("Interchage Help");
				inversionHelp.setResizable(true);
				inversionHelp.setBounds(0,0, 900, 800);
				inversionHelp.setVisible( true ); 
				inversionHelp.add(new JLabel(InterchangeIcon));
			}
		});
		Inversion_info.setBounds(660, 486, 50, 23);
		frmLoopOptimization.getContentPane().add(Inversion_info);

		JButton ReversalBtn = new JButton("Reversal");
		ReversalBtn.setFont(new Font("Arial Black", Font.BOLD, 11));
		ReversalBtn.setForeground(Color.BLACK);
		ReversalBtn.setBounds(492, 563, 125, 43);
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
				reversalHelp = new JFrame();
				ImageIcon InterchangeIcon = new ImageIcon(this.getClass().getResource("/images/Reversal.jpg"));
				reversalHelp.setTitle("Interchage Help");
				reversalHelp.setResizable(true);
				reversalHelp.setBounds(0,0, 900, 800);
				reversalHelp.setVisible( true ); 
				reversalHelp.add(new JLabel(InterchangeIcon));
			}
		});
		Reversal_info.setBounds(660, 574, 50, 23);
		frmLoopOptimization.getContentPane().add(Reversal_info);
		Border ReversalBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
		ReversalBtn.setBorder(ReversalBorder);
		frmLoopOptimization.getContentPane().add(ReversalBtn);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(520, 631, 157, 14);
		frmLoopOptimization.getContentPane().add(progressBar);
		
		JLabel lblProgressBar = new JLabel("Progress Bar");
		lblProgressBar.setBounds(566, 646, 91, 14);
		frmLoopOptimization.getContentPane().add(lblProgressBar);
	}
	
	private void startTransform(int x) {
		// TODO Auto-generated method stub
		SwingWorker<String, String> worker = new SwingWorker<String, String>(){

			@Override
			protected String doInBackground() throws Exception {
				// TODO Auto-generated method stub
				
				if(inputTextField.getText().isEmpty()){
					return "Please type in a input.";
				}
				
				ArrayList<ArrayList<String>> returnStringArrayList = new ArrayList<ArrayList<String>>();
				String returnString = "";
				
				write_to_file();
				
				switch(x){    
				case 0:    
					 //code to be executed;   
					Fission fission = new Fission();
					publish("xoxo");
					returnStringArrayList = (fission.compute(stringsArrayList, inputString));
					
				break;  //optional  
				case 1:    
					 //code to be executed;   
					Fusion fusion = new Fusion();
					publish("xoxo");
					returnStringArrayList = (fusion.compute(stringsArrayList, inputString));
				break;  //optional  
				case 2:    
						 //code to be executed;  
					Unrolling unrolling = new Unrolling();
					publish("xoxo");
					returnStringArrayList = (unrolling.compute(stringsArrayList, inputString));
				break;  //optional  
				case 3:    
						 //code to be executed; 
					Interchange interchange = new Interchange();
					publish("xoxo");
					returnStringArrayList = (interchange.compute(stringsArrayList, inputString));
				break;  //optional  
				case 4:    
							 //code to be executed;    
					Skewing skewing = new Skewing();
					publish("xoxo");
					returnStringArrayList = (skewing.compute(stringsArrayList, inputString));
				break;  //optional  
				case 5:    
							 //code to be executed;    
					Inversion inversion = new Inversion();
					publish("xoxo");
					returnStringArrayList = (inversion.compute(stringsArrayList, inputString));
				break;  //optional  
				case 6:    
								 //code to be executed;  
					Reversal reversal = new Reversal();
					publish("xoxo");
					returnStringArrayList = (reversal.compute(stringsArrayList, inputString));
				break;  //optional  								    
				default:      
				}    
				
				System.out.println(returnStringArrayList);
				
//				for(int i = 0; i < 3; i++){
//					Thread.sleep(1000);
//					//System.out.println("Hello: " + i);
//					publish(i);
//				}
				
				for (int ii = 0; ii < returnStringArrayList.size(); ii++){
//					returnStringArrayList.get(ii);
					for (int jj = 0; jj < returnStringArrayList.get(ii).size(); jj++){
						returnString += returnStringArrayList.get(ii).get(jj);
						returnString += "\n";
					}
				}
				
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
				
			}			
			
			@Override
			protected void done(){
				try {
					String status = get();
					outputTextField.setText(status);
					progressBar.setValue(100);
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
		
		stringsArrayList = new ArrayList<ArrayList<String>>();

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


	}

	private void write_to_file() {

		inputString = inputTextField.getText();
		System.out.println("=================");
		System.out.println(inputString);
		System.out.println("=================");
		processString(inputString);

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
