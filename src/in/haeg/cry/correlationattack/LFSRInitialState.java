package in.haeg.cry.correlationattack;

import java.util.ArrayList;
import java.util.BitSet;

public class LFSRInitialState {
	private BitSet tapSequence;
	private ArrayList<Boolean> stream = new ArrayList<Boolean>(Constants.ITERATIONS);
	private BitSet bitString;
	
	public LFSRInitialState(BitSet a_BitString, BitSet a_TapSequence, int a_Iterations) {
		tapSequence = a_TapSequence;
		bitString = a_BitString;
		for (int i = 0; i < a_Iterations; i++) {
			stream.add(outputBit(bitString));
			bitString = iterate(bitString);
		}
	}
	
	public BitSet iterate(BitSet a_BitString) {
		BitSet justTheTaps = a_BitString;
		justTheTaps.xor(tapSequence);
		boolean nextBit = ((justTheTaps.cardinality() % 2) == 0);
		return Constants.shiftRight(a_BitString, nextBit);
	}
	
	public boolean outputBit(BitSet a_BitString) {
		return a_BitString.get(a_BitString.size() - 1);
	}
	
	public ArrayList<Boolean> getStream() {
		return stream;
	}
	
	@Override
	public String toString() {
		return stream.toString();
	}
	
}
