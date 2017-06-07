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

public class Fusion extends Generic {

	public Fusion() {
		// TODO Auto-generated constructor stub
		System.out.println("New fusion!");
	}
	/**
	 * This method computs the fuson on the set of inputs.
	 * 
	 * @param stringsArrayList
	 *            The arraylist containing the input code which was typed and we want to do the transformation on
	 * @param inputString
	 *            The string containing the input code which was typed and we want to do the transformation on
	 * @return ArrayList<ArrayList<String>> This returns the first iteration value used in this for loop..
	 */
	public ArrayList<ArrayList<String>> compute(ArrayList<ArrayList<String>> stringsArrayList, String inputString){
		
		ArrayList<ArrayList<String>> outputArrayList = new ArrayList<ArrayList<String>>();
		for (int i = 1; i < stringsArrayList.size(); i += 4) {
			for (int j = i + 4; j < stringsArrayList.size(); j += 4) {

				if ((stringsArrayList.get(i).isEmpty()) || (stringsArrayList.get(j).isEmpty())) {
					continue;
				}
				if (checkIfEqual(stringsArrayList.get(i).get(0), (stringsArrayList.get(j)).get(0))) {

					ArrayList<String> testArrayList = new ArrayList<String>();
					testArrayList.add(stringsArrayList.get(i + 1).get(0));
					testArrayList.add(stringsArrayList.get(j + 1).get(0));
					if (test(testArrayList, stringsArrayList.get(i))) {
						continue;
					}
					String stringToAdd = stringsArrayList.get(j + 1).get(0);
					stringsArrayList.get(i + 1).add(0, stringToAdd);

					stringsArrayList.get(j).clear();
					stringsArrayList.get(j + 1).clear();
					stringsArrayList.get(j + 2).clear();
				}

			}
		}
		ArrayList<String> brc = new ArrayList<String>();
		brc.add("}");
		stringsArrayList.add(brc);
	
		
		return stringsArrayList;
	}
}
