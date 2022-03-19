/**
 *  This class implements a BST.
 *  
 *  @param <T> the type of the key.
 *
 *  @author W. Masri and YOUR_NAME_HERE
 */
class BST<T extends Comparable<T>> {
	// **************//
	// DO NO CHANGE
	
	/**
	 *  Node class.
	 *  @param <T> the type of the key.
	 */
	class Node<T extends Comparable<T>> 
	{
		/**
		*  key that uniquely identifies the node.
		*/
		T key;
		/**
		*  references to the left and right nodes.
		*/
		Node<T> left, right;
		public Node(T item) {  key = item; left = right = null; }
		public String toString() { return "" + key; }
	}
	
	/**
	 *  The root of the BST.
	 */
	Node<T> root;
	public BST() { root = null; }
	public String toString() { return inorderToString(); }
	// DO NO CHANGE
	// **************//
	
	
	/**
	 *  This method returns a string in which the elements are listed in an inorder fashion. 
	 *  Your implementation must be recursive.
	 *  Note: you can create private helper methods
	 *  @return string in which the elements are listed in an inorder fashion
	 */
	public String inorderToString() {
		// YOUR CODE GOES HERE
		return "";
	}
	
	/**
	 *  This method inserts a node in the BST. You can implement it iteratively or recursively.
	 *  Note: you can create private helper methods
	 *  @param key to insert
	 */
	public void insert(T key) {
		// YOUR CODE GOES HERE 
	}
	
	/**
	 *  This method finds and returns a node in the BST. You can implement it iteratively or recursively.
	 *  It should return null if not match is found.
	 *  Note: you can create private helper methods
	 *  @param key to find
	 *  @return the node associated with the key.
	 */
	public Node<T> find(T key)	{ 					
		// YOUR CODE GOES HERE
		return null;
	}	 
	

	/**
	 *  Main Method For Your Testing -- Edit all you want.
	 *  
	 *  @param args not used
	 */
	public static void main(String[] args) {
		/*
							 50
						  /	      \
						30    	  70
	                 /     \    /     \
	                20     40  60     80   
		*/
		
		
		BST<Integer> tree1 = new BST<>();
		tree1.insert(50); tree1.insert(30); tree1.insert(20); tree1.insert(40);
		tree1.insert(70); tree1.insert(60); tree1.insert(80);
		
		if (tree1.find(70) != null) {
			System.out.println("Yay1");
		}
		if (tree1.find(90) == null) {
			System.out.println("Yay2");
		}
		if (tree1.toString().equals("\"20\" \"30\" \"40\" \"50\" \"60\" \"70\" \"80\"") == true) {
			System.out.println("Yay3");
		}
		
		
		BST<String> tree2 = new BST<>();
		tree2.insert("50"); tree2.insert("30"); tree2.insert("20"); tree2.insert("40");
		tree2.insert("70"); tree2.insert("60"); tree2.insert("80");
		
		if (tree2.find("70") != null) {
			System.out.println("Yay4");
		}
		if (tree2.find("90") == null) {
			System.out.println("Yay5");
		}
		if (tree2.toString().equals("\"20\" \"30\" \"40\" \"50\" \"60\" \"70\" \"80\"") == true) {
			System.out.println("Yay6");
		}
	}
	
}
