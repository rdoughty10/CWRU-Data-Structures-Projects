
public class BinarySearchTree {
	
	private Node rootNode; //named it rootnode to differentiate between this variable and the 'root' of many of the methods
	private int count; //going to increment this variable while doing an inorder traversal for kthInt method and once it equals k, we will print out the nod
	private int size; //used this variable to keep the size fo the tree (number of nodes)
	
	private class Node{
		
		private int key;
		private Node right;
		private Node left;
		
		Node(int key){
			this.key = key;
			this.right = null;
			this.left = null;
		}
		
	}
	
	BinarySearchTree(){
		rootNode = null;
		count = 0;
		size = 0;
	}
	
	//inserts a node in the BST --> worst case running time = O(h) (height of tree)
	public void insert(Node root, int key) {
		Node parent = null;
		Node trav = root;
		
		while (trav != null) { //simple traversal to find trav (the location of where to insert) and it's parent
			parent = trav;
			if (key < trav.key) {
				trav = trav.left;
			}else if (key > trav.key) {
				trav = trav.right;
			}
		}
		
		if(parent==null) {	//dependent on the key, insert the new Node to the right or left of the parent
			rootNode = new Node(key);
		}else if (key < parent.key) {
			parent.left = new Node(key);
		}else if (key > parent.key) {
			parent.right = new Node(key);
		}
		
		size++; //must increment the size of the tree

	}
	
	//search a node with a specific key in BST ---> worst case running time = O(h) (height of tree)
	public Node search(Node root, int key) {
		Node trav = root; 
		while(trav != null) { //simple traversal of the tree 
			if(key == trav.key) {
				return trav; //return trav once key is equal to trav.key
			}else if(key > trav.key) {
				trav = trav.right;
			}else if(key < trav.key) {
				trav = trav.left;
			}
		}
		return null;
		
	}
	
	//deletes a node from BST ---> worst case running time = O(h) 
	public Node delete(Node root, int key) {
		if (root == null) //check to make sure there is actually a tree (aka the root is not null)
            return root;

        if (key < root.key) {	//traverse the tree recursively to find the node
            root.left = delete(root.left, key);
        } else if (key > root.key) {
            root.right = delete(root.right, key);
        } else { //once found, see if the node has left or right as null and return the other if so

            if (root.left == null) {
                return root.right;
            } else if (root.right == null)
                return root.left;
            
            int minimum = root.right.key; //if it has two children, then traverse the inner tree for the lowest value and switch it with the current node
            while(root.right.left != null) {
            	minimum = root.right.left.key;
            	root.right = root.right.left;
            }
            
            root.key = minimum;
            root.right = delete(root.right, root.key);
            size--; //decrement the size of the tree

        }

        return root; //this also covers if the key is not found and nothing will be removed
	}

	//find the k'th smallest element in BST  --> worst case running time = O(n) (size of tree)
	public Node kthSmallest(Node root, int k) {
		if (k <= 0 || k > size) {
			throw new IllegalArgumentException("K must be greater than 0 and less than the size of the tree");
		}
		count = 0; //set count equal to 0 so that it will 
		return getkthSmallest(root, k); //call the method that actually gets the Kth smallest number and returns the node
		
		
		
	}
	
	//helper method for kthSmallest because I needed to set count = 0 before each call and could not do that with a single recursive method
	private Node getkthSmallest(Node root, int k) { //I decided to use a helper method so that I can use an external count variable without interference for recursion
		if(root == null) {
			return root;
		}
		
		Node left = getkthSmallest(root.left, k); //traverse the left root recursively
		
		if(left!=null) { //once it is null, return the left (that is the lowest of the subtree)
			return left;
		}
		count++; //increase the count
		if(count == k) { //if the count now equals k, then we can just return that node
			return root;
		}
		
		return(getkthSmallest(root.right, k)); //otherwise, we traverse the right root looking for the kth smallest
		
	}
	
	//find inorder traversal --> worst case running time = O(n) (size of tree)
	public void inorderRec(Node root) throws Exception {
		if (root == null) {
			throw new Exception("Tree is empty"); //throw exception if root is empty
		}
		if(root.left != null) { //otherwise, traverse the tree recursively to the left
			inorderRec(root.left);
		}
		System.out.print(root.key + " "); //print out the root
		if(root.right != null) { //traverse the tree recursively to the right
			inorderRec(root.right);
		}
	}
	
	
	//getters and setters
	public Node getRoot() {
		return rootNode;
	}
	
	public void setRoot(Node root) {
		this.rootNode = root;
	}
	
	public int getKey(Node root) { //need this to get the specific key to print out for some methods in the test file
		return root.key;
	}
	
	public int getSize() {
		return size;
	}
	
}
