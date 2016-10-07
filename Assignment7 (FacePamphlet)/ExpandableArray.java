/**
 * This class provides methods for working with an array that expands to include
 * any positive index value supplied by the caller.
 */
public class ExpandableArray {
	/**
	 * Creates a new expandable array with no elements.
	 */
	public ExpandableArray() {

	}

	/**
	 * Sets the element at the given index position to the specified. value. If
	 * the internal array is not large enough to contain that element, the
	 * implementation expands the array to make room.
	 */
	public void set(int index, Object value) {

		// the right portion of values of the array -> room is not available
		if(index >= arr.length) {
			Object[] tmpArr = new Object[index+1]; // keep all values of the variable arr
			
			// keep all items of arr
			for(int i=0; i<arr.length; i++) {
				tmpArr[i] = arr[i];
			}
			
			arr = tmpArr; // exchange addresses
 		}	
		
		arr[index] = value;
		
	}

	/**
	 * Returns the element at the specified index position, or null if no such
	 * element exists. Note that this method never throws an out-of-bounds
	 * exception; if the index is outside the bounds of the array, the return
	 * value is simply null.
	 */
	public Object get(int index) {
		if(index <= arr.length-1) return arr[index];
		return null;
	}
	
	
	private Object[] arr = {};
}