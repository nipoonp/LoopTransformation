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

import java.util.ArrayList;

public class Interchange extends Generic  {

	public Interchange() {
		// TODO Auto-generated constructor stub
		System.out.println("New interchange!");
	}
	
	public ArrayList<ArrayList<String>> compute(ArrayList<ArrayList<String>> stringsArrayList, String inputString){
		
		ArrayList<ArrayList<String>> outputArrayList = new ArrayList<ArrayList<String>>();

		if (test2DI(stringsArrayList.get(4),stringsArrayList.get(1)) == false) {
			ArrayList<String> temp = stringsArrayList.get(1);
			stringsArrayList.set(1, stringsArrayList.get(3));
			stringsArrayList.set(3, temp);
			System.out.println("Interchanging...");
		} else {
			ArrayList<String> o = new ArrayList<String>();
			o.add("Cannot Interchange!");
			stringsArrayList = new ArrayList<ArrayList<String>>();
			stringsArrayList.add(o);
			return stringsArrayList;
		}

		System.out.println(stringsArrayList);
		// test with 2D
		// find if there is dependence in the i loops

		// if there is no dependence in the i loops then apply the thing below

	
		
		return stringsArrayList;
	}
}
