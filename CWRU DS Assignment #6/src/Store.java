
public class Store {

	
	static int vertices;  
	  
	
	int minDistance(int[][] dists) {
		vertices = dists.length;
		boolean []inMST = new boolean[vertices];  //array that contains vertices that are a part of min spanning tree
		   
	    inMST[0] = true; //always include the first vertex in the MST array
	  
	   
	    int edgeCount = 0; //tracks how many edges we have added to inMST
	    int minCost = 0; //keeps track of the total cost
	    while (edgeCount < vertices - 1){ //keep looping until we have added vertices - 1 edges
	     
	  
	        // Find minimum weight valid edge.  
	        int min = Integer.MAX_VALUE;
	        int a = -1; 
	        int b = -1;
	        for (int i = 0; i < vertices; i++) {  //loop through the dists 2d array	      
	            for (int j = 0; j < vertices; j++) {
	                if (dists[i][j] < min && isValidEdge(i, j, inMST)) {   //if a value is less than the minimum, and is a valid edge 
	                    min = dists[i][j]; 
	                    a = i; 
	                    b = j; 
	                    
	                } 
	            } 
	        } 
	        //add vertices to inMST array
	        if (a != -1 && b != -1){  
	            edgeCount++;
	            minCost = minCost + min; //add value to min
	            inMST[b] = inMST[a] = true; //add the edge to the inMST array (both vertices locations are now true)
	        } 
	    } 
	    System.out.println("Minimum cost = " + minCost);
		return minCost; 
	}
	
	//method that returns if exactly one of the two vertices of an edge are in the inMST array
	static boolean isValidEdge(int u, int v, boolean[] inMST) { 
	    if (u == v) //if u = v, then it is not possible (they are the same vertex)
	        return false; 
	    if (inMST[u] == false && inMST[v] == false)  // if both vertices are not in MST, then it cannot happen
	        return false; 
	    else if (inMST[u] == true && inMST[v] == true)  //if both vertices are in the MST, then it also cannot happen
	        return false; 
	    return true; 
	} 
	   
	  
	
	public static void main(String[] args) { 
	   
	    int dists[][] = {{ 0, 1, 2, 6 }, 
	                    { 1, 0, 3, 4 }, 
	                    { 2, 3, 0, 3 }, 
	                    { 6, 4, 3, 0 }}; 
	  
	    // Print the solution 
	    Store store = new Store();
	    store.minDistance(dists); 
	} 
	
}
