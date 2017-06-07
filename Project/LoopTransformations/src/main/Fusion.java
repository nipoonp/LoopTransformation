package main;

import java.util.ArrayList;

public class Fusion extends Generic {

	public Fusion() {
		// TODO Auto-generated constructor stub
		System.out.println("New fusion!");
	}
	
	public ArrayList<ArrayList<String>> compute(ArrayList<ArrayList<String>> stringsArrayList, String inputString){
		
		ArrayList<ArrayList<String>> outputArrayList = new ArrayList<ArrayList<String>>();
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

//		System.out.println(stringsArrayList);
		ArrayList<String> brc = new ArrayList<String>();
		brc.add("}");
		stringsArrayList.add(brc);
	
		
		return stringsArrayList;
	}
}
