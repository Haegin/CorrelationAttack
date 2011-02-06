package in.haeg.cry.correlationattack;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;

public class LFSR {
	int bits;
	BitSet tapSequence;
	HashMap<BitSet, LFSRInitialState> possibleInitialStates;

	public LFSR(int a_Bits, int[] a_TapSequence) {
		possibleInitialStates = new HashMap<BitSet, LFSRInitialState>();
		tapSequence = new BitSet(((Integer) bits).SIZE);
		bits = a_Bits;
		for (int tap : a_TapSequence) {
			tapSequence.set(tap - 1);
		}
		for (BitSet bitString : generateBitStrings()) {
			possibleInitialStates.put(bitString, new LFSRInitialState(bitString, tapSequence, Constants.ITERATIONS));
		}
	}
	
	public LFSRInitialState getState(BitSet a_InitialSequence) {
		return possibleInitialStates.get(a_InitialSequence);
	}
	
	public ArrayList<BitSet> generateBitStrings() {
		ArrayList<BitSet> result = new ArrayList<BitSet>();
		BitSet bitset;
		int index;
		int value;
		int max = (int) Math.pow(2, bits);
		for (int i = 0; i < max; i++) {
			value = i;
			bitset = new BitSet(((Integer) max).SIZE);
			index = 0;
			while (value != 0) {
				if (value % 2 != 0) {
					bitset.set(index);
				}
				++index;
				value = value >>> 1;
			}
			result.add(bitset);
		}
		return result;
	}
}
