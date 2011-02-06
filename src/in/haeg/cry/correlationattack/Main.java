package in.haeg.cry.correlationattack;

import in.haeg.cry.BitString;

import java.util.ArrayList;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		BitString b1 = new BitString(4);
		BitString b2 = new BitString(4);
		try {
			b1.setIntegerValue(3);
			b2.setIntegerValue(11);
			System.out.println(b1.toString());
			System.out.println(b1.getIntegerValue());
			System.out.println(b2.toString());
			System.out.println(b2.getIntegerValue());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
		try {
			int[] taps = {2, 4};
			LFSR a = new LFSR(4, taps);
			
			for (BitString bs : a.generateBitStrings()) {
				System.out.println(bs.toString());
			}
			
			BitString initialState = new BitString(4);
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
