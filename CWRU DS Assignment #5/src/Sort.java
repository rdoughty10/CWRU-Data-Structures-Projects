import java.util.Random;

public class Sort {

	void MergeSort(int[] A) {
		myMergeSort(A, 0, A.length-1);
	}
	
	private static void myMergeSort(int[] arr, int start, int end) {
		if(start >= end) {
			return;
		}
		//recursively split the arr into 2 and keep doing it until it is a size of 1
		int middle = (start+end)/2;
		myMergeSort(arr, start, middle);
		myMergeSort(arr, middle+1, end);
		
		//sort the sorted halves
		merge(arr, start, middle, end);
		
	}
	
	private static void merge(int[] A, int leftStart, int middle, int rightEnd) {
		
		int l = middle-leftStart+1; //creating temp arrays of correct size
		int r = rightEnd-middle;
		int[] leftTemp = new int[l];
		int[] rightTemp = new int[r];
		
		
		for(int i = 0; i < l; i++) { //copy data to the two temp arrays
			leftTemp[i] = A[leftStart+i];
		}
		for(int i = 0; i < r; i++) {
			rightTemp[i] = A[middle+1+i];
		}
		
		int i = 0;
		int j = 0;
		int k = leftStart;
		while(i < l && j < r) { //compare the values of the temp arrays, and set the smaller one to a point in the array, then increment and check again
			if(leftTemp[i] <= rightTemp[j]) {
				A[k] = leftTemp[i]; //adding lefttemp value since smaller
				i++; //incrementing location of leftTemp
			}else {
				A[k] = rightTemp[j]; //adding right temp value since smaller
				j++; //incrementing location of rightTemp
			}
			k++; //increment location of A[k] so that next one is added in next spot
		}
		
		//these next too just transfer the remaining values in the temp arrays into the actual array
		while(i < l) {
			A[k] = leftTemp[i];
			i++;
			k++;
		}
		while(j < r) {
			A[k] = rightTemp[j];
			j++;
			k++;
		}
		
	}
	
	void QuickSort(int[] A) {
		myQuickSort(A, 0, A.length-1);
	}
	
	private void myQuickSort(int[] A, int first, int last) {
		if(first >= last) return; //given to us by slides
		int split = partition(A, first, last);
		myQuickSort(A, first, split-1);
		myQuickSort(A, split+1, last);
	}

	private int partition(int[] A, int first, int last) {
		int pivot = A[last]; //I chose to pivot with the final value, because I found that simpler than doing do whiles if you partition through the middle
		int i = first - 1;
		for(int j = first; j < last; j++) { //simple loop from beginning to end 
			if(A[j] <= pivot) { //for every value, if it is less than the pivot...
				i++; // increment i every time we swap a value
				
				int swapTemp = A[i]; //swap A[i] with A[j]
				A[i] = A[j];
				A[j] = swapTemp;
			}
		}
		
		int swapTemp = A[i+1];
		A[i+1] = A[last];
		A[last] = swapTemp;
		
		
		return i+1;
	
	}

	void InsertionSort(int[] A) {
		for(int i = 1; i < A.length; i++){ //loop through the loop
			int toInsert = A[i]; //determine the current value that will be 'inserted'
			int j = i - 1; //start from j and while j is positive and the value of J is greater than toInsert, move down the list
			while(j >= 0 && A[j] > toInsert) {
				A[j+1] = A[j]; //slide values to the right
				j--;
			}
			A[j+1] = toInsert; //once found, insert the toInsert value into the array
		}
	}
	
	static int[] RandomArray(int n, int a, int b) {
		if(n < 0) {
			throw new IllegalArgumentException("n must be greater than 0");
		}
		Random rand = new Random();
		int[] randArray = new int[n];
		for(int i = 0; i < n; i++) {
			int num = rand.nextInt(b-a+1)+a;
			randArray[i] = num;
		}
		return randArray;
	}
	
	static void print(int[] A) {
		for(int i = 0; i < A.length; i++) {
			System.out.print(A[i] + " ");
		}
	}
	
	public static void main(String[] args) {
		int[] arr = RandomArray(8000, -10000, 10000); //create the array
		Sort sort = new Sort(); //create the object
		
		print(arr); //print the array before
		System.out.println();
		
		long start = System.nanoTime(); //time before
		sort.InsertionSort(arr); //sort array
		long end = System.nanoTime(); //time after
		print(arr);
		
		System.out.println("\nRuntime: " + (end-start));

		
	}
}
