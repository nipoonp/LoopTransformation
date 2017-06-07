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

import javax.swing.SwingUtilities;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					GUI window = new GUI();
					window.frmLoopOptimization.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		
		});

	}

}
