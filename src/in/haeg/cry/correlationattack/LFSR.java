package in.haeg.cry.correlationattack;

import java.util.ArrayList;
import java.util.TreeMap;

public class LFSR {
	int bits;
	int tapSequence;
	TreeMap<Integer, LFSRInitialState> possibleInitialStates;

	public LFSR(int a_Bits, int a_TapSequence) {
		tapSequence = a_TapSequence;
		bits = a_Bits;
		possibleInitialStates = new TreeMap<Integer, LFSRInitialState>();
		for (int bitString : generateBitStrings()) {
			possibleInitialStates.put(bitString, new LFSRInitialState(bitString, bits, tapSequence, Constants.ITERATIONS));
		}
	}
	
	public LFSRInitialState getState(int a_InitialSequence) {
		return possibleInitialStates.get(a_InitialSequence);
	}
	
	public ArrayList<LFSRInitialState> getStates() {
		return new ArrayList<LFSRInitialState>(possibleInitialStates.values());
	}
	
	private ArrayList<Integer> generateBitStrings() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		int max = (int) Math.pow(2, bits);
		for (int i = 0; i < max; i++) {
			result.add(i);
		}
		return result;
	}
	
}
