package in.haeg.cry.correlationattack;

import java.util.ArrayList;
import in.haeg.cry.BitString;

public class LFSRInitialState {
	private BitString tapSequence;
	private ArrayList<Boolean> stream = new ArrayList<Boolean>(Constants.ITERATIONS);
	private BitString bitString;
	
	public LFSRInitialState(BitString a_BitString, BitString a_TapSequence, int a_Iterations) {
		tapSequence = a_TapSequence;
		bitString = a_BitString;
		for (int i = 0; i < a_Iterations; i++) {
			stream.add(outputBit(bitString));
			iterate();
		}
	}
	
	public void iterate() {
		BitString justTheTaps = bitString;
		justTheTaps.and(tapSequence);
		boolean nextBit = ((justTheTaps.cardinality() % 2) == 0);
		bitString.rightShift();
		bitString.set(0, nextBit);
	}
	
	public boolean outputBit(BitString a_BitString) {
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
