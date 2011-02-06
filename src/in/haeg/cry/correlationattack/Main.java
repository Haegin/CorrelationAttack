package in.haeg.cry.correlationattack;

import java.util.ArrayList;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LFSR a = new LFSR(7, 65);
			LFSR b = new LFSR(11, 513);
			LFSR c = new LFSR(13, 5633);
			
			ArrayList<Integer> aStream;
			ArrayList<Integer> bStream;
			ArrayList<Integer> cStream;
			
			ArrayList<Integer> outStream = new ArrayList<Integer>();
			for (String s : streamText) {
				outStream.add(Integer.parseInt(s));
			}
			
/*			for (LFSRInitialState ai : a.getStates()) {
				for (LFSRInitialState bi : b.getStates()) {
					for (LFSRInitialState ci : c.getStates()) {
						aStream = ai.getStream();
						bStream = bi.getStream();
						cStream = ci.getStream();
						
						ArrayList<String> outStream = new ArrayList<String>();
						for (int i = 0; i < Constants.ITERATIONS; i++) {
							outStream.add(calculateStreamBit(aStream.get(i), bStream.get(i), cStream.get(i)).toString());
							if (outStream.equals(streamText)) {
								System.out.println("Success");
							}
						}
					}
				}
			}*/
			
			double highest = 0;
			double curr;
			LFSRInitialState highestState = null;
			for (LFSRInitialState bi : b.getStates()) {
				curr = similarity(bi.getStream(), outStream);
				if (curr > highest) {
					highest = curr;
					highestState = bi;
				}
			}
			System.out.println("Highest correlation = " + highest);
			
			LFSRInitialState bState = highestState;
			
			highest = 0;
			for (LFSRInitialState ci : c.getStates()) {
				curr = similarity(ci.getStream(), outStream); 
				if (curr > highest) {
					highest = curr;
					highestState = ci;
				}
			}
			System.out.println("Highest correlation = " + highest);
			
			LFSRInitialState cState = highestState;
			
			highest = 0;
			for (LFSRInitialState ai : a.getStates()) {
				aStream = ai.getStream();
				bStream = bState.getStream();
				cStream = cState.getStream();
				ArrayList<Integer> wholeStream = new ArrayList<Integer>();
				for (int i = 0; i < aStream.size(); i++) {
					wholeStream.add(calculateStreamBit(aStream.get(i), bStream.get(i), cStream.get(i)));
				}
				curr = similarity(wholeStream, outStream);
				if (curr > highest) {
					highest = curr;
					highestState = ai;
				}
			}
			System.out.println("Highest correlation = " + highest);
			LFSRInitialState aState = highestState;
			
			System.out.println("Inital register states:\n\tA - " + aState.getInitialState() + "\n\tB - " + bState.getInitialState() + "\n\tC - " + cState.getInitialState());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Integer calculateStreamBit(int a, int b, int c) {
		if (a == 0) {
			return c;
		} else {
			return b;
		}
	}
	
	public static double similarity(ArrayList<Integer> a, ArrayList<Integer> b) {
		double sameCount = 0;
		for (int i = 0; i < a.size(); i++) {
			if (a.get(i) == b.get(i)) {
				sameCount++;
			}
		}
		return sameCount / a.size();
	}
	
	public static String[] streamText = "1 0 1 0 1 1 1 0 0 0 0 1 1 1 1 0 0 1 1 0 0 0 1 0 1 1 1 1 1 1 0 1 1 0 1 0 1 1 0 0 0 1 0 1 1 0 0 1 1 1 1 0 1 0 1 0 1 1 0 1 1 0 1 1 1 1 1 0 0 1 1 1 1 0 0 1 0 0 0 0 1 0 1 1 1 0 0 1 1 0 0 1 0 1 1 0 0 0 1 1 1 0 1 0 0 1 0 0 1 1 0 0 0 0 0 1 1 1 1 1 1 0 1 1 0 1 0 0 1 1 1 1 1 0 0 0 1 0 1 0 0 1 1 0 0 0 1 1 0 1 0 0 0 1 0 1 0 1 1 0 1 0 1 1 1 0 1 1 1 0 1 1 1 1 1 1 1 0 0 0 0 1 0 0 1 1 1 0 0 1 0 1 0 0 1 0 1 1 0 0 1 0 0 1 1 0 1 0 1 0 1 1 1 1 1 0 0 1 0 0 0 1 1 1 0 0 1 0 0 0 1 1 0 1 1 1 0 0 1 0 0 1 0 1 1 1 1 0 1 1 1 0 0 1 1 0 0 0 0 1 1 0 0 0 0 1 0 0 0 0 1 0 1 0 1 1 1 1 1 1 1 0 1 0 0 0 0 0 0 0 0 1 1 1 1 1 0 0 1 0 1 0 1 0 0 1 1 0 1 0 1 1 0 0 0 0 0 1 0 0 1 1 1 0 0 1 1 1 1 1 0 1 1 0 1 1 1 1 0 0 1 1 0 1 0 1 1 0 1 0 1 1 1 0 0 0 1 0 0 1 1 0 1 1 1 0 0 0 1 0 1 0 1 0 1 0 1 1 0 1 0 0 0 1 1 0 1 0 0 0 1 1 1 0 1 1 1 1 1 1 1 1 1 1 1 0 1 1 1 1 1 1 0 1 0 1 1 1 1 1 1 1 0 0 0 1 1 0 0 0 1 0 1 1 0 1 0 0 0 1 1 1 0 1 1 1 0 0 1 1 0 1 1 1 0 0 1 0 0 0 1 1 1 0 1 0 1 0 1 0 0 0 0 1 1 1 1 1 0 1 0 1 1 0 0 0 1 0 0 0 0 0 1 0 0 0 0 1 0 0 1 1 1 1 1 0 1 0 0 0 0 0 0 1 1 0 1 1 1 1 1 0 1 1 1 0 1 1 0 0 1 0 1 0 0 0 0 0 1 1 1 1 1 1 0 1 0 0 0 0 0 0 0 0 1 0 0 0 0 1 1 1 1 1 1 1 1 0 1 1 1 1 0 1 0 0 0 0 1 0 0 1 1 1 1 0 0 0 0 1 0 0 1 1 1 0 1 1 0 0 1 1 0 0 0 0 1 1 0 1 1 1 0 1 0 1 0 1 1 1 0 0 1 1 0 0 0 0 0 0 0 0 0 1 0 1 0 1 0 1 0 1 0 0 1 0 0 0 0 0 1 0 1 0 1 0 1 1 0 0 1 1 1 1 0 0 0 0 1 1 0 0 0 1 0 1 0 0 1 0 0 1 1 1 0 0 0 1 1 0 0 0 0 0 1 1 0 0 0 1 0 1 1 1 0 1 0 1 0 1 1 1 0 1 1 1 1 1 0 1 1 0 1 1 0 1 1 1 0 0 1 0 1 1 1 1 1 0 1 1 1 1 0 1 1 0 1 1 1 1 1 1 1 1 1 0 0 1 1 1 1 0 0 1 1 1 0 0 0 1 1 0 1 0 1 0 0 0 1 1 1 1 1 1 1 1 0 1 0 1 1 0 0 1 1 0 0 1 1 1 1 1 1 0 1 0 0 1 1 1 0 0 1 1 1 1 1 1 0 1 1 0 1 0 0 1 1 1 0 0 1 0 1 0 0 0 0 1 1 1 0 0 0 0 0 1 0 1 0 0 0 1 0 1 0 1 0 1 1 0 1 1 0 1 1 0 1 0 1 1 0 0 1 1 1 1 0 0 1 0 0 1 0 1 1 1 1 0 0 1 0 1 1 0 1 0 0 1 0 0 1 0 1 1 1 1 0 1 1 1 0 0 1 1 1 0 1 1 0 0 1 0 0 1 1 1 0 0 1 1 1 0 1 1 1 0 1 1 0 1 0 0 1 1 1 0 0 0 0 0 0 1 0 0 0 0 0 1 0 1 0 0 1 1 1 1 0 1 1 0 1 1 1 0 1 1 1 0 1 0 0 0 1 0 1 0 0 1 1 0 0 0 1 1 1 0 0 1 1 0 1 0 0 1 0 0 0 0 1 1 1 0 1 1 0 1 0 1 0 1 1 0 1 1 1 1 1 1 0 0 1 0 1 1 0 0 1 0 0 1 1 1 1 0 0 0 0 1 0 1 1 1 1 0 0 1 0 1 1 0 1 0 1 1 0 1 1 1 0 1 1 1 1 1 1 0 1 1 1 1 0 1 0 0 0 1 1 0 0 1 1 1 1 0 0 0 1 0 0 1 1 0 0 0 1 0 0 1 0 0 0 1 0 1 1 0 1 1 1 1 1 1 0 0 0 0 0 0 1 1 0 1 1 1 0 0 0 0 0 1 0 1 0 0 1 0 1 0 1 1 0 1 1 0 1 0 0 0 1 0 0 0 0 1 0 0 1 0 1 1 0 0 1 0 1 1 0 0 1 0 1 1 0 1 1 1 0 0 0 1 1 1 1 0 1 1 1 1 0 1 1 0 1 0 1 0 0 1 1 1 0 0 1 0 0 0 0 1 1 1 0 1 0 1 1 1 0 1 1 1 1 1 1 0 1 1 1 0 0 1 0 1 0 0 1 0 1 1 1 1 0 1 1 0 1 1 0 0 0 1 1 1 1 0 1 0 0 1 0 0 1 1 0 0 1 0 0 0 1 0 1 1 0 0 1 1 1 0 1 1 1 0 0 1 1 1 0 0 0 0 1 0 0 0 0 1 0 0 0 1 0 1 1 1 1 0 1 1 0 0 0 0 0 0 1 1 1 1 1 0 1 0 0 1 0 1 1 0 0 0 0 0 0 0 0 0 1 1 0 1 0 1 0 1 0 1 0 0 0 1 0 1 1 1 0 1 1 1 1 0 0 0 1 1 0 1 1 0 1 1 0 0 0 1 1 0 0 1 0 1 1 0 0 1 1 0 0 0 1 0 1 1 1 0 0 1 0 1 0 0 0 0 1 1 0 0 1 1 0 1 1 1 0 0 1 0 1 0 1 0 0 1 0 1 0 0 1 1 0 0 1 0 0 1 1 1 1 1 0 0 0 1 1 1 1 0 0 1 1 0 0 0 1 1 0 0 0 0 1 0 1 1 1 1 0 1 0 1 1 0 1 1 0 0 0 0 0 0 0 0 1 0 0 1 0 1 0 1 0 0 0 0 0 1 0 1 1 0 0 1 1 1 0 1 0 1 0 0 1 1 1 0 0 1 1 1 0 0 1 0 0 1 1 1 1 0 1 0 0 0 1 0 1 0 1 1 1 1 0 1 0 1 1 0 0 0 1 0 1 1 1 1 0 0 1 0 0 0 1 1 0 0 0 1 0 1 0 0 1 0 0 0 0 0 0 0 0 1 0 0 1 1 0 1 0 1 0 1 0 1 1 1 0 1 1 1 0 1 1 1 0 0 1 0 0 0 1 1 0 0 0 0 1 1 1 1 0 0 1 0 1 0 0 0 0 1 1 0 0 1 1 1 0 1 0 0 1 1 1 0 0 1 1 1 1 1 1 1 0 1 0 0 1 1 0 0 1 0 1 1 0 0 1 0 1 0 0 1 1 0 1 0 1 0 1 1 0 0 1 1 0 0 0 1 0 1 1 0 0 1 1 0 0 1 1 0 0 1 0 0 1 0 1 1 0 0 0 1 0 0 1 0 0 0 1 1 1 0 1 1 1 0 1 0 1 1 0 0 1 1 0 0 0 1 0 0 1 1 1 0 0 0 0 0 1 1 1 1 1 0 1 0 0 0 0 0 1 0 0 1 1 1 0 1 0 0 1 1 1 1 0 0 0 1 0 1 1 0 0 1 0 1 0 1 1 0 0 1 0 1 0 1 0 0 1 1 1 0 0 1 1 0 1 1 0 1 0 0 1 0 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 0 1 1 0 1 0 1 0 1 1 1 0 1 0 0 1 0 0 0 1 0 1 0 1 0 0 1 0 0 1 1 0 1 0 1 1 1 1 1 0 1 0 1 1 0 0 0 1 1 0 0 0 0 0 0 1 0 0 1 0 0 0 1 0 0 1 0 0 1 0 1 0 1 0 1 0 0 0 1 1 0 0 1 1 1 0 0 0 0 0 0 0 1 1 1 1 1 0 1 0 0 1 1 1 1 1 0 1 0 0 1 1 0 0 0 1 1 0 1 0 0 1 1 0 1 1 0 0 1 1 0 0 0 0 0 1 1 0 1 1 0 1 1 1 0 1 0 0 1 1 1 0 0 1 1 1 1 0 0 1 1 1 1 0 0 ".split(" ");

}
