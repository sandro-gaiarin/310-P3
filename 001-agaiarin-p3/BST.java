/**
 *  This class implements a BST.
 *  
 *  @param <T> the type of the key.
 *
 *  @author W. Masri and Alessandro Gaiarin
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
		String returnString = inStringNode(root, "");
		return returnString.trim(); //return the TRIMMED version of returnString to get rid of any trailing spaces.
		//didn't know trim() was part of java.lang! that's nice.
	}

	/**
	 * Private method for inorderToString().
	 * @param node
	 * @param returnString
	 * @return
	 */
	private String inStringNode(Node<T> node, String returnString) {
		if (node != null) {
			returnString = inStringNode(node.left, returnString); //travel down the left subtree
			returnString = returnString + "\"" + node + "\" "; //display the root
			returnString = inStringNode(node.right, returnString); //travel down the right subtree
		}
		return returnString; //return the completed string
	}
	
	/**
	 *  This method inserts a node in the BST. You can implement it iteratively or recursively.
	 *  Note: you can create private helper methods
	 *  @param key to insert
	 */
	public void insert(T key) {
		root = insertNode(root, key);
	}

	/**
	 * Private helper function for insert().
	 * @param node root, starting node of the BST.
	 * @param key key that we want to add.
	 * @return the newly added node with the new key.
	 */
	private Node<T> insertNode(Node<T> node, T key) {
		if (node == null) { //creates new node at empty spot
			node = new Node<T>(key);
			return node;
		}
		//compareTo() reminder: (i literally do this every time lmao)
		//	x.compareTo(y) returns:
		//		- 0 if x == y
		//		- value < 0 if x < y
		//		- value > 0 if x > y
		else if (key.compareTo(node.key) < 0) { //if the key is less than the current key's value...
			node.left = insertNode(node.left, key); //it moves to the left
		}
		else if (key.compareTo(node.key) > 0){ //if the key is greater than the current key's value...
			node.right = insertNode(node.right, key); //it moves to the right
		}
		return node; //finally, the node is returned

	}
	
	/**
	 *  This method finds and returns a node in the BST. You can implement it iteratively or recursively.
	 *  It should return null if not match is found.
	 *  Note: you can create private helper methods
	 *  @param key to find
	 *  @return the node associated with the key.
	 */
	public Node<T> find(T key)	{
		return findNode(root, key);
	}

	/**
	 * Private helper function for find().
	 * @param node
	 * @param key
	 * @return
	 */
	private Node<T> findNode(Node<T> node, T key) {
		if (node == null) {
			return null;
		}
		if (key.compareTo(node.key) == 0) {
			return node;
		}

		else if (key.compareTo(node.key) < 0) {
			return findNode(node.left, key);
		}
		else {
			return findNode(node.right, key);
		}
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

		System.out.println("Current tree: " + tree1.inorderToString());
		System.out.println("Current root: " + tree1.root);

		if (tree1.find(70) != null) {
			System.out.println("Yay1");
		}
		if (tree1.find(90) == null) {
			System.out.println("Yay2");
		}
		System.out.println(tree1.toString());
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
