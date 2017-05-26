package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import java.awt.Font;
import javax.swing.SwingConstants;

public class GUI {

	private JFrame frame;
	private JTextArea inputTextField;
	private JTextArea outputTextField;
	private String inputString;
	private JavaSyntaxChecker checker;
	/**
	 * Launch the application.
	 */
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
	
	private void write_to_file(){
		
		inputString = inputTextField.getText();
		outputTextField.setText(inputString);
		
		try(FileWriter fw = new FileWriter("Loop_to_check.java", false);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println("package main;");
			    out.println("public class Loop_to_check {");
			    out.println("public class CheckCode {");
			    out.println("public void main (String [] args) {");
			    
			    out.println("\n");
			    out.println("\n");
			    
			    out.println(inputString);
			    
			    out.println("\n");
			    out.println("\n");
			    
			    out.println("}");
			    out.println("}");
			    out.println("}");

			    
			    //more code
			    
			    //more code
			} catch (IOException e1) {
			    //exception handling left as an exercise for the reader
			}
		
	    System.out.println(JavaSyntaxChecker.check("Loop_to_check.java"));
	}
}
