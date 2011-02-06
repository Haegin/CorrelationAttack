package in.haeg.cry.correlationattack;

import java.util.ArrayList;
import java.util.BitSet;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			int[] taps = {2, 3};
			LFSR a = new LFSR(4, taps);
			
			for (BitSet bs : a.generateBitStrings()) {
				System.out.println(bs.toString());
			}
			
			BitSet initialState = new BitSet(4);
			initialState.set(1);
			initialState.set(2);
			System.out.println("Initial state: " + initialState);
			ArrayList<Boolean> stream = a.getState(initialState).getStream();
			for (Boolean bit : stream) {
				if (bit) {
					System.out.print(1);
				} else {
					System.out.print(0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
