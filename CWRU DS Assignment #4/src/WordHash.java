//Ryan Doughty
//CSDS 233
//WordHash --> this class houses a wordHash() method, which takes in a string containing words and parses them using a hash table and returns each word and the frequency of each
public class WordHash {

	class Entry {
		//entry class variables
		private Entry next;
		private String word;
		private int frequency;
		
		//entry constructor
		public Entry(String word) {
			next = null;
			this.word = word;
			frequency = 1;
		}
	}
	
	
	//wordhash class variables
	private Entry[] wordHash;
	private int tableSize;
	private int entries;
	private double loadFactor;
	
	//wordhash constructor
	public WordHash(int size) {
		wordHash = new Entry[size];
		tableSize = size;
		entries = 0;
		loadFactor = (double)entries/tableSize;
	}
	
	
	
	//main method takes a string as an input and prints out all the words encountered in that input, along with their number of occurrences. 
	public static void wordCount(String str){
		//create the hash table
		WordHash mainHash = new WordHash(5); //I initiated my hash to have a size of 5, which is fairly small, but it demonstrates my rehashing works 
		
		
		//parse the incoming string into an array
		String[] words = str.split("\\P{Alpha}+");
		
		
		//loop through the list and search for the word
		for(int i = 0; i < words.length; i++) {
			String word = words[i].toLowerCase();
			int h = mainHash.hash(word);
			if(mainHash.search(word, h) == true) { //search for the word, and if found, update the frequency
				mainHash.updateFrequency(word, h);
			}else {//if we do not find the word, insert it into the hash table
				mainHash.insert(word, h, 1);
				mainHash.entries++;
			}
			//test the load factor after every new entry
			mainHash.loadFactor = (double)mainHash.entries/(double)mainHash.tableSize;
			if(mainHash.loadFactor >= 1) { //if load factor is equal to or above 1, then rehash the table
				mainHash.wordHash = mainHash.rehash();
			}
		}
		
		
		//print out the results
		System.out.println("\nFinal Results");
		mainHash.printHash();
		
	}

	//simple method to print out the hash and the frequency (as well as it's location in the hash)
	private void printHash() {
		for(int i = 0; i < wordHash.length; i++) {
			Entry trav = wordHash[i];
			while(trav != null) {
				System.out.println("'" + trav.word + "' occurs " + trav.frequency + " times. (Hash Location:" + i +")");
				trav = trav.next;
			}
		}
	}


	//method that updates the frequency of a word if already in the hash table
	private void updateFrequency(String str, int h) {
		Entry trav = wordHash[h];
		while(trav != null) {
			if(trav.word.equals(str)) {
				trav.frequency++;
			}
			trav = trav.next;
		}
	}



	//method to do the hash function
	private int hash(String str) {
		int h = Math.abs(str.hashCode()) % tableSize; //hash function
		return h; //return the value of the hash function
	}
	
	//inserting a new node into the hash table
	private void insert(String str, int h, int frequency) {
		Entry newEntry = new Entry(str);//create the new entry and set next to null and frequency to 1 (if not from rehash method) or the current frequency
		newEntry.next = null;
		newEntry.frequency = frequency;
		
		if(wordHash[h] == null) {
			wordHash[h] = newEntry;
		}else {
			Entry head = wordHash[h]; //add it as the head of the list (minimizes looping)
			wordHash[h] = newEntry;	//simple three variable switch
			wordHash[h].next = head;
			
		}
	}
	
	//simple loop that returns true if the word is found
	private boolean search(String str, int h) {
		Entry trav = wordHash[h];
		while(trav != null) {
			if(trav.word.equals(str)) {
				return true;
			}
			trav = trav.next;
		}
		return false;
		
	}
	
	//method to double the size of the array and rehash all currenty values
	private Entry[] rehash() {
		int newSize = tableSize*2;
		
		Entry[] temp = wordHash; //save the current hash to a temp
		wordHash = new Entry[newSize];//update the hash to a empty hash, double the size
		
		tableSize = newSize;//update 
		
		//rehashing
		for(int i = 0; i < temp.length; i++) { //loop through old array and the chains and add them to the new array
			Entry trav = temp[i];
			while(trav!=null) {
				int frequency = trav.frequency; //important to keep the same frequency
				int h = hash(trav.word); //rehash the different words
				insert(trav.word, h, frequency); //re-insert the values into the new array
				trav = trav.next;
			}
		}
		
		return wordHash;
		
	}
	
	//for testing purposes
	public static void main(String[] args) {
		wordCount("hello,to,//the/&TA//grading//this**project!!I,hope you;;are,,,having99a9good9day!");
	}
}
