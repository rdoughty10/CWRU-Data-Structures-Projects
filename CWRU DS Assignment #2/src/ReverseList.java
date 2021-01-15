import java.util.Random;

public class ReverseList {
	
	//class variables --> a place to store the first node in the linked list and the tail
	private Node head = null;
	private Node tail = null;
	
	//sub class Node which has it's data and the next Node
	private class Node{
		private int data;
		private Node next;
		
		Node(int data){
			this.data = data;
			next = null;
		}
		
	}
	
	//Method to add a node --> worst case running time: O(n)
	public void addNode(int data) {
		Node newNode = new Node(data); //create the new node with the data
		if(head == null) { //set it equal to the head and tail if the list is empty
			head = newNode;
			tail = newNode;
		}else {//otherwise add a pointer for the tail node to this new node and then set the new node equal to the tail
			tail.next = newNode;
			tail = newNode;
		}
	}
	
	//main reverse method --> worst case running time: O(n), but constant space O(3)==O(1) because only 3 variables created
	public Node reverseList(Node node) {
		Node trav = node; //set three temporary node variables, main traversal, the previous, and the next
		Node prev = null;
		Node next = null;
		while(trav!=null) { //start at the beginning and loop through until the end
			next = trav.next; //set next equal to the node after the current
			trav.next = prev; //set the pointer for the current node to the previous
			prev = trav; //move up in the line by setting the trav to the next and the previous to the current
			trav = next;
		} 
		return prev; //return the final node, which will become the head
		
	}
	
	//helper method to print out the list --> navigate through the list using basic traversal. --> worst case running time: O(n)
	private void printList() {
		Node trav = head;
		if(head == null) {
			System.out.println("List is empty");
		}else { //simple traversal through the linked list
			while(trav != null) {
				System.out.print(trav.data + " ");
				trav = trav.next;
			}
		}
	}

	
	
	
	
	
	public static void main(String[] args) {
		ReverseList list = new ReverseList();
		
		Random random = new Random();
		
		//create a linked list of size 20 and add nodes of random integer 0-99 to it
		for (int i = 0; i < 20;i++) {
			int randomData = random.nextInt(99)+1;
			list.addNode(randomData);
		}
		
		//print out the list before
		System.out.println("Before:");
		list.printList();
		
		//reverse the list and print out after
		list.head = list.reverseList(list.head); //must set the head of the list equal to the node returned by the reverse method (the final node)
		System.out.println("\nAfter:");
		list.printList();
		
		
		
		
	}
}
