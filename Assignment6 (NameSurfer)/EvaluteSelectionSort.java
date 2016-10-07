/**
 * This program will show you a common way evaluates the execution time of an operation of 
 * a algorithm in Java
 * 
 */
import java.lang.*;

import acm.program.*;

public class EvaluteSelectionSort extends ConsoleProgram {

	public void run() {
		int[] array = {31, 41, 59, 26, 53, 58, 97, 25, 93};
		long start = System.currentTimeMillis();

		// some operations to evaluate
		for(int i=0; i<10000; i++) {
			selectionSort(array);
		}
		
		long delta = System.currentTimeMillis() - start;
		
		println(delta);
		for(int i=0; i<array.length; i++)
			println(array[i]);
	}
	
	private void selectionSort(int[] arr) {		
		for(int i=0; i<arr.length; i++) {
			int lh = i;
			int rh = smallestItems(arr, lh);
			swap(arr, rh, lh);
		}
	}
	
	
	private int smallestItems(int[] arr, int lh) {
		int smallest = lh;
		
		for(int i=lh+1; i<arr.length; i++) {
			if(arr[i] < arr[smallest]) smallest = i;
		}
		
		return smallest;
	}
	
	private void swap(int[] arr, int rh, int lh) {
		int tmp = arr[rh];
		arr[rh] = arr[lh];
		arr[lh] = tmp;
	}
	
}
