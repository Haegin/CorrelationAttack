package in.haeg.cry.correlationattack;

import java.util.BitSet;

public class Constants {
	
	public static final int ITERATIONS = 16;
	
	public static BitSet shiftRight(BitSet a_BitString, boolean a_NewValue) {
		for (int i = a_BitString.length(); i > 0; i--) {
			a_BitString.set(i, a_BitString.get(i - 1));
		}
		a_BitString.set(0, a_NewValue);
		return a_BitString;
	}

}
