package main;

import java.util.ArrayList;

public class Skewing extends Generic  {

	public Skewing() {
		// TODO Auto-generated constructor stub
		System.out.println("New skewing!");
	}
	
	public String compute(ArrayList<ArrayList<String>> stringsArrayList, String inputString){
		


		if ((test2DI(stringsArrayList.get(4)) == true) && (test2DJ(stringsArrayList.get(4)) == true)) {
			System.out.println("skewing...");
		} else {
			System.out.println("cannot skew!");
			return "cannot skew!";
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

	
		
		return "skewing";
	}

}
