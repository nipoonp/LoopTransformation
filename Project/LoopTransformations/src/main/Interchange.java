package main;

import java.util.ArrayList;

public class Interchange extends Generic  {

	public Interchange() {
		// TODO Auto-generated constructor stub
		System.out.println("New interchange!");
	}
	
	public String compute(ArrayList<ArrayList<String>> stringsArrayList, String inputString){
		


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

	
		
		return "interchange";
	}
}
