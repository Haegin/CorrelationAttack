package in.haeg.cry.correlationattack;

import java.util.ArrayList;
import java.util.TreeMap;
import in.haeg.cry.BitString;

public class LFSR {
	int bits;
	BitString tapSequence;
	TreeMap<BitString, LFSRInitialState> possibleInitialStates;

	public LFSR(int a_Bits, int[] a_TapSequence) {
		possibleInitialStates = new TreeMap<BitString, LFSRInitialState>();
		bits = a_Bits;
		tapSequence = new BitString(bits);
		for (int tap : a_TapSequence) {
			tapSequence.set(tap - 1);
		}
		for (BitString bitString : generateBitStrings()) {
			possibleInitialStates.put(bitString, new LFSRInitialState(bitString, tapSequence, Constants.ITERATIONS));
		}
	}
	
	public LFSRInitialState getState(BitString a_InitialSequence) {
		return possibleInitialStates.get(a_InitialSequence);
	}
	
	public ArrayList<BitString> generateBitStrings() {
		ArrayList<BitString> result = new ArrayList<BitString>();
		BitString bitstr;
		int value;
		int max = (int) Math.pow(2, bits);
		for (int i = 0; i < max; i++) {
			value = i;
			bitstr = new BitString(bits);
			try {
				bitstr.setIntegerValue(value);
			} catch (Exception ex) {
				ex.printStackTrace();
				System.exit(1);
			}
			result.add(bitstr);
		}
		return result;
	}
}
