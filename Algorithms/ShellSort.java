package Algorithms;

import java.util.Random;
/**
 * Code for using Shell sort on an array
 */
public class ShellSort {

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
		ShellSort.iterativeArrayShellSort(numbers);
		
		// Print values before sorting
		System.out.println("VALUES AFTER SORTING:");
		for (var number : numbers) {
			System.out.println(number);
		}

	}
	
	/**
	 * Performs shell sort on an array
	 * @param <T> Type of data being used
	 * @param sortArray Data to be stored
	 */
	public static <T extends Comparable<? super T>> void iterativeArrayShellSort(T[] sortArray) {
		//determine number of passes required, assuming at least 1
		int passes= 1;
		while(((Math.pow(3, passes) -1) /2) < sortArray.length) {
			//increment passes
			passes++;
		}
		
		//loop to shell sort, adjusting for pass over count from final loop
		for(int pass = passes -1; pass >= 1; pass--) {
			//calculate gap(h) size for pass
			int gap = (int)Math.min((Math.pow(3, pass) -1) /2 , Math.ceil((sortArray.length)/3.00));
			
			//use insertion sort with current gap size
			for(int offset = 0; offset < gap; offset++) {
				//loop through elements in current pass
				for(int currentIndex = offset+gap; currentIndex < sortArray.length; currentIndex++) {
					//calculate index of item immediately before current size
					int prevIndex = currentIndex - gap;
					
					//store value we are inserting in temporary variable
					var insertValue = sortArray[currentIndex];
					
					//loop shuffling items until find right space for item
					while((prevIndex >= 0) && (java.util.Objects.compare(insertValue, sortArray[prevIndex], java.util.Comparator.nullsFirst(java.util.Comparator.naturalOrder())) < 0)){
						//move existing values to right one position
						sortArray[prevIndex+gap] = sortArray[prevIndex];
						
						//move next position to left
						prevIndex = prevIndex - gap;
					}
					
					//store value in correct location
					sortArray[prevIndex+gap]=insertValue;
				}
			}
		}
	}
}
