package in.haeg.cry;

import java.util.BitSet;

public class BitString extends BitSet implements Comparable {
	private int numbBits;
	
	public BitString() {
		super();
	}
	
	public BitString(int nbits) {
		super(nbits);
		numbBits = nbits;
	}
	
	@Override
	public int size() {
		return numbBits;
	}
	
	/**
	 * 
	 * @param val the integer value to set this bitstring to.
	 * @throws InsufficientBitsException if the value passed in is too large to store in this bitstring
	 * @throws NegativeValueException if the value passed in is negative.
	 */
	public void setIntegerValue(int val) throws InsufficientBitsException, NegativeValueException {
		if (val > (Math.pow(numbBits, 2) - 1)) {
			throw new InsufficientBitsException();
		} else if (val < 0) {
			throw new NegativeValueException();
		}
		int index = 0;
		while (val > 0) {
			if (val % 2 == 1) {
				set(index);
			}
			index++;
			val = val >>> 1;
		}
	}
	
	/**
	 * Gets the value of this bitset as an integer. This assumes the first bit is the most significant and that it represents a simple unsigned integer.
	 * @return the integer representation of this bitset.
	 */
	public int getIntegerValue() {
		int val = 0;
		boolean bit;
		for (int i = 0; i < this.length(); i++) {
			bit = get(i);
			if (bit) {
				val = val | 0x01;
			}
			val = val << 1;
		}
		return val >>> 1;
	}
	
	/**
	 * Shifts the bit array to the right by one bit and fills in the new bits with zeros.
	 */
	public void rightShift() {
		for (int i = length(); i > 0; i--) {
			set(i, get(i - 1));
		}
		set(0, Boolean.FALSE);
	}
	
	class InsufficientBitsException extends Exception {
		private static final long serialVersionUID = 7482672029067543599L;}
	class NegativeValueException extends Exception {
		private static final long serialVersionUID = 8473968141680297981L;}
	
	@Override
	public int compareTo(Object anotherBitString) {
		if (!(anotherBitString instanceof BitString)) {
			throw new ClassCastException("A BitString object was expected");
		}
		return this.getIntegerValue() - ((BitString)anotherBitString).getIntegerValue();
	}

}
