
public class Test {
	
	public static void main(String[] args) throws Exception {
		BinarySearchTree bst = new BinarySearchTree();
		
		
		//i) Show the results of inserting 2,1,4,5,9,3,6,7,10,12,11 into an empty bst
		System.out.println("i) Show the results of inserting 2,1,4,5,9,3,6,7,10,12,11 into an empty bst");
		bst.insert(bst.getRoot(), 2);
		bst.insert(bst.getRoot(), 1);
		bst.insert(bst.getRoot(), 4);
		bst.insert(bst.getRoot(), 5);
		bst.insert(bst.getRoot(), 9);
		bst.insert(bst.getRoot(), 3);
		bst.insert(bst.getRoot(), 6);
		bst.insert(bst.getRoot(), 7);
		bst.insert(bst.getRoot(), 10);
		bst.insert(bst.getRoot(), 12);
		bst.insert(bst.getRoot(), 11);
		
		System.out.println("Inorder of tree after adding numbers:");
		bst.inorderRec(bst.getRoot());
		
		//ii) Start with the tree in (i) and delete 4 then delete 9
		System.out.println("\n\nii) Start with the tree in (i) and delete 4 then delete 9");
		bst.setRoot(bst.delete(bst.getRoot(), 4));
		System.out.println("Inorder of tree after removing 4:");
		bst.inorderRec(bst.getRoot());
		
		bst.setRoot(bst.delete(bst.getRoot(), 9));
		System.out.println("\nInorder of tree after removing 9:");
		bst.inorderRec(bst.getRoot());
		
		//iii) With the tree from (ii) search 12 and search 4
		System.out.println("\n\niii) With the tree from (ii) search 12 and search 4");
		System.out.println("Searching for Node with key 12:");
		if(bst.search(bst.getRoot(), 12) == null) {
			System.out.println("Node not found");
		}else {
			System.out.println("Node found!");
		}
		System.out.println("\nSearching for Node with key 4:");
		if(bst.search(bst.getRoot(), 4) == null) {
			System.out.println("Node not found");
		}else {
			System.out.println("Node found!");
		}
		
		
		//iv) With the tree from (iii) find the 3rd minimum element in the tree
		System.out.println("\n\niv) With the tree from (iii) find the 3rd minimum element in the tree");
		System.out.println("Current Inorder Traversal:");
		bst.inorderRec(bst.getRoot());
		System.out.println("\n3rd smallest: " + bst.getKey(bst.kthSmallest(bst.getRoot(), 3)));


		
	}
	
}
