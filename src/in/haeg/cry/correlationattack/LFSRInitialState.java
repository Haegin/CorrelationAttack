package in.haeg.cry.correlationattack;

import java.util.ArrayList;

public class LFSRInitialState {
	private int tapSequence;
	private ArrayList<Integer> stream = new ArrayList<Integer>(Constants.ITERATIONS);
	private int bitString;
	private final int initialState;
	private int length;
	private int lengthMask;
	
	public LFSRInitialState(int a_BitString, int a_Length, int a_TapSequence, int a_Iterations) {
		tapSequence = a_TapSequence;
		bitString = a_BitString;
		length = a_Length;
		initialState = bitString;
		
		lengthMask = 0x01;
		for (int i = length - 1; i > 0; i--) {
			lengthMask <<= 1;
		}
		
		int nextBit;
		for (int i = 0; i < a_Iterations; i++) {
			
			stream.add(bitString % 2);
			nextBit = nextBit();
			bitString >>>= 1;
			if (nextBit == 1) {
				bitString = bitString | lengthMask;
			}
		}
	}
	
	private int nextBit() {
		int numbBitsSet = 0;
		int bitStr = bitString & tapSequence;
		while (bitStr > 0) {

			if (bitStr % 2 == 1) {
				numbBitsSet++;
			}
			bitStr >>>= 1;
		}
		return (numbBitsSet % 2);
	}
	
	public ArrayList<Integer> getStream() {
		return stream;
	}
	
	@Override
	public String toString() {
		return stream.toString();
	}
	
	public int getInitialState() {
		return initialState;
	}
	
}
