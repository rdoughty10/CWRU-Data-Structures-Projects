import java.util.LinkedList;
import java.util.Queue;

public class Park {
	
	private int[] depthPath;
	private boolean[][] depthMatrix;
	int depthCount; //counts where to add a value to depthPath
	
	Queue<Integer> q = new LinkedList<>(); //I used the queue from Java for the breadth traversal because it is easier and you didn't say we couldn't
	//also, we already did this in an earlier assignment I believe so I have already done it
	private int[] breadthPath;
	private boolean[][] breadthMatrix;
	int breadthCount;
    

	private int[] depthFirst(boolean[][] dists) {
		depthMatrix = dists; //set dists to depthMatrix double array, which is what will be used
		depthPath = new int[depthMatrix.length]; //initialize depth path to be the same size as dists
		depthPath[0] = 0; //can do this because always starting with origin at 0
		depthCount = 1; //initialize counter at 1 because we know the first value is 0
		myDepthFirstTrav(0); //start recursion
		
		
		for(int i = 0; i < depthMatrix.length; i++) { //print out results
			System.out.print(depthPath[i] + " ");
		}
		return depthPath;
	}
	
	public void myDepthFirstTrav(int current){
		for(int i = 0; i < depthPath.length; i++) {
			if(depthMatrix[current][i] == true) {
				depthMatrix[current][i] = false; //set both the locations of the array to false so they do not get visited again
				depthMatrix[i][current] = false;
				
				depthPath[depthCount] = i;
				depthCount+=1;
				myDepthFirstTrav(i);
			}
		}
	}


	private int[] breadthFirst(boolean[][] dists) {
		breadthMatrix = dists; 
		for(int i = 0; i < breadthMatrix.length; i++) {
			breadthMatrix[i][0] = false;
		}
		breadthPath = new int[breadthMatrix.length]; //initialize depth path to be the same size as dists
		breadthPath[0] = 0; //can do this because always starting with origin at 0
		breadthCount = 1; //initialize counter at 1 because we know the first value is 0
		myBreadthFirstTrav(0); //start recursion
		
		
		for(int i = 0; i < breadthMatrix.length; i++) { //print out results
			System.out.print(breadthPath[i] + " ");
		}
		return depthPath;
	}
	
	private void myBreadthFirstTrav(int current) {
		
		for(int i = 0; i < breadthPath.length; i++) {
			if(breadthMatrix[current][i] == true) {
				for(int j = 0; j < breadthPath.length; j++) {
				
					breadthMatrix[j][i] = false;
				}
				q.add(i);
			}
		}
		for(int i = 0; i < q.size(); i++) {
			int value = q.remove();
			breadthPath[breadthCount] = value;
			breadthCount++;
		
			myBreadthFirstTrav(value);
				
		}
	}

	public static void main(String[] args) {
		boolean[][] matrix = {	{false, true, false, true},
								{true, false, true, false},
								{false, true, false, false},
								{true, false, false, false},
							};
		boolean[][] matrix2 = matrix;
		
		Park park = new Park();

		
		park.depthFirst(matrix);
		System.out.println();
		park.breadthFirst(matrix2);
	}
}
