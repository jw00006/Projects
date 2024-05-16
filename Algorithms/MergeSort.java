package Algorithms;

import java.util.Random;
/**
 * Code for using merge sort on an array
 */
public class MergeSort {

	/**
	 * Main method
	 * 
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		// Create random number generator
		var random = new Random();
		
		// Create new array of random numbers
		var numbers = new Integer[20];
		
		// Populate random number array
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = random.nextInt(100);
		}
		
		// Print values before sorting
		System.out.println("VALUES BEFORE SORTING:");
		for (var number : numbers) {
			System.out.println(number);
		}
		System.out.println("---");
		
		// Call sort method here
		MergeSort.recursiveMergeSort(numbers);
		
		// Print values before sorting
		System.out.println("VALUES AFTER SORTING:");
		for (var number : numbers) {
			System.out.println(number);
		}

	}
	
	/**
	 * Merges two sorted halves of an array
	 * @param <T> Type  of data being stored
	 * @param sortArray Array containing values being merged
	 * @param leftIndex Left most index where merged data will go (inclusive)
	 * @param middleIndex Midpoint (right end of left half, inclusive)
	 * @param rightIndex Right most index where merged data will go (inclusive)
	 */
	protected static <T extends Comparable<? super T>> void merge(T[] sortArray, int leftIndex, int middleIndex, int rightIndex) {
		//create temporary sub-arrays of each half
		T[] leftHalf = java.util.Arrays.copyOfRange(sortArray, leftIndex, middleIndex+1);
		T[] rightHalf = java.util.Arrays.copyOfRange(sortArray, middleIndex+1, rightIndex+1);
		
		//declare variables for tracking positions
		int leftHalfIndex = 0;
		int rightHalfIndex = 0;
		int mergedIndex = leftIndex;
		
		//perform merge on left and right arrays
		while((leftHalfIndex < leftHalf.length) && (rightHalfIndex < rightHalf.length)){
			//compare values to determine which goes next
			if(java.util.Objects.compare(leftHalf[leftHalfIndex], rightHalf[rightHalfIndex], java.util.Comparator.nullsFirst(java.util.Comparator.naturalOrder())) < 0) {
				// if left is smaller than right, copy value 
				sortArray[mergedIndex] = leftHalf[leftHalfIndex];
				leftHalfIndex++;
			}else {
				//right half is smaller or they are equal
				sortArray[mergedIndex] = rightHalf[rightHalfIndex];
				rightHalfIndex++;
			}
			//increment position in merged  array
			mergedIndex++;
		
		} 
		
		//copy anything remaining from left array
		while(leftHalfIndex < leftHalf.length) {
			//copy value
			sortArray[mergedIndex] = leftHalf[leftHalfIndex];
			
			//increment counters
			leftHalfIndex++;
			mergedIndex++;
			
		}
		
		//copy anything remaining from right array
		while(rightHalfIndex < rightHalf.length){
			//copy value
			sortArray[mergedIndex] = rightHalf[rightHalfIndex];
			
			//increment counters
			rightHalfIndex++;
			mergedIndex++;
		}
	}
	
	/**
	 * Performs merge sort on an array 
	 * @param <T> Type of data being sorted
	 * @param sortArray Data to be sorted
	 */
	public static <T extends Comparable<? super T>> void recursiveMergeSort(T[] sortArray) {
		// call recursive method
		MergeSort.recursiveMergeSort(sortArray, 0, sortArray.length-1);
	}
	

	/**
	 * Performs merge sort on an array 
	 * @param <T> Type of data being sorted
	 * @param sortArray Data to be sorted
	 * @param leftIndex Left-most index to sort (inclusive)
	 * @param rightIndex Right-most index to sort (inclusive)
	 */
	protected static <T extends Comparable<? super T>>  void recursiveMergeSort(T[] sortArray, int leftIndex, int rightIndex) {
			//base case: one element being sorted
			if(leftIndex >= rightIndex) {
				return;
			}
			
			//determine where middle of array is
			var middleIndex = leftIndex + (rightIndex - leftIndex) / 2;
			
			//sort each half
			MergeSort.recursiveMergeSort(sortArray, leftIndex, middleIndex);
			MergeSort.recursiveMergeSort(sortArray, middleIndex+1, rightIndex);
			
			//merge sorted halves
			MergeSort.merge(sortArray, leftIndex, middleIndex, rightIndex);			
		}
}
