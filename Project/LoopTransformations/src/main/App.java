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
