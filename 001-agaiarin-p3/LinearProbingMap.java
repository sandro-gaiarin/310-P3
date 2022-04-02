import java.util.Set;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * No need for description.
 * @param <K> no need for description.
 * @param <V> no need for description.
 */	
public class LinearProbingMap<K, V> implements Map<K, V>
{
	//TODO
	// Nothing! Code complete.

	/**
	 * No need for description.
	 * @param <K> no need for description.
	 * @param <V> no need for description.
	 */	
	class Pair<K,V> {
		/**
		* No need for a description.
		*/
		private K key;
		/**
		* No need for a description.
		*/	
		private V value;
		/**
		* No need for a description.
		* @param key no need for a description
		* @param value no need for a description
		*/	
		public Pair(K key, V value){
			this.key = key;
			this.value = value;
		}
		/**
		* No need for a description.
		*/	
		public Pair(){
			this.key = null;
			this.value = null;
		}
		public K getKey(){ return key; }
		public V getValue(){ return value; }
		public void setKey(K key){ this.key = key; }
		public void setValue(V value){ this.value = value; }
		@Override public int hashCode() {  
			return key.hashCode(); 
		}
		@Override public boolean equals(Object obj) {  
			if (obj == null) return false;
			if (!(obj instanceof Pair)) return false;
			Pair pair = (Pair)obj;
			return pair.key.equals(key); 
		}
	}

	/**
	 * No need for a description.
	 */	
	private static final int DEFAULT_CAPACITY = 400000;
	/**
	 * No need for a description.
	 */	
	private int size;
	/**
	 * No need for a description.
	 */	
	private int capacity; 
	/**
	 * No need for a description.
	 */	
	private Pair<K, V>[] table;
	/**
	 * No need for a description.
	 */	
	private Pair<K, V> tombstone;  // has no impact since we are not implementing remove()
	
	/**
	 * No need for a description.
	 * @param capacity no need for a description.
	 */	
	@SuppressWarnings("unchecked")
	public LinearProbingMap(int capacity)
	{
		this.capacity = capacity;
		size = 0;
		table = (Pair<K, V>[])new Pair[capacity];
		tombstone = (Pair<K, V>)new Pair();	// has no impact since we are not implementing remove()
	}
	
	/**
	 * No need for a description.
	 */	
	public LinearProbingMap() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * No need for a description.
	 * @return no need for a description.
	 */	
	public int size() { 
		return size; 
	}

	/**
	 * No need for a description.
	 * @return no need for a description.
	 */		
	public boolean isEmpty() { 
		return size == 0; 
	}

	/**
	 * No need for a description.
	 */	
	public void clear() {
		size = 0;
		for (int i = 0; i < capacity; i++) {
			table[i] = null;
		}
	}

	////////////*****ADD YOUR CODE HERE******////////////////////

	/**
	 * Returns the value of the given key.
	 * @param key Key of the value we're looking for.
	 * @return value of given key.
	 */			
	public V get(Object key) {
		try {
			return table[computeHash(key)].value;
		} catch (NullPointerException e) { //null if the key doesn't have anything there
			return null;
		}
	}
	
	/**
	 * Places the specified value with the specified key in this map.
	 * @param key Key that will be associated with the new value.
	 * @param value Value to be added to the map.
	 * @return The previous value of the specified key, otherwise null.
	 */	
	public V put(K key, V value) {
		V returnValue = get(key); //old value of the key, or null if it didn't have one
		if (returnValue == null) {
			//increase size if returnValue was null (i.e., this key was empty before)
			size += 1;
		}
		Pair<K,V> putPair = new Pair<>(key, value); //create a new pair
		table[computeHash(key)] = putPair; //place pair in the table at the index of the key's hash
		return returnValue;
	}

	////////////***********////////////////////

	/**
	 * No need for a description.
	 * @param key no need for a description.
	 * @return no need for a description.
	 */	
	public V remove(Object key) {
		return null; // DO NOT IMPLEMENT
	}
	
	/**
	 * No need for a description.
	 * @param key no need for a description.
	 * @return no need for a description.
	 */	
	private int computeHash(Object key)
	{
		int hash = Math.abs(key.hashCode()) % capacity;
		return hash;
	}

	/**
	 * No need for a description.
	 * @return no need for a description.
	 */	
	public String toString()
	{
		StringBuilder st = new StringBuilder();
		for (int i = 0; i < capacity; i++) {
			if (table[i] != null) {
				st.append("(" + table[i].key + ", " + table[i].value + ")");
			}
		}
		return st.toString();
	}

	/**
	 * No need for a description.
	 * @param key no need for a description.
	 * @return no need for a description.
	 */	
	public boolean containsKey(Object key) {
		for (int i = 0; i < capacity; i++) {
			if (table[i] != tombstone && table[i] != null) {
				if (table[i].key.equals(key)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * No need for a description.
	 * @param value no need for a description.
	 * @return no need for a description.
	 */	
	public boolean containsValue(Object value) {
		for (int i = 0; i < capacity; i++) {
			if (table[i] != tombstone && table[i] != null) {
				if (table[i].value.equals(value)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * No need for a description.
	 * @return no need for a description.
	 */	
	public Set<K> keySet() {
		HashSet<K> set = new HashSet<K>();
		for (int i = 0; i < capacity; i++) {
			if (table[i] != tombstone && table[i] != null) {
				set.add(table[i].key);
			}
		}
		return set;
	}	
	
	/**
	 * No need for a description.
	 * @return no need for a description.
	 */	
	public Collection<V> values() {
		ArrayList<V> list = new ArrayList<V>();
		for (int i = 0; i < capacity; i++) {
			if (table[i] != tombstone && table[i] != null) {
				list.add(table[i].value);
			}
		}
		return list;
	}

	/**
	 * No need for a description.
	 * @return no need for a description.
	 */		
	public Set<Map.Entry<K,V>>	entrySet() {
		return null;
	}

	/**
	 * No need for a description.
	 * @param m no need for a description.
	 */	
	public void putAll(Map<? extends K,? extends V> m) {
	}
	
	/**
	 *  Main Method For Your Testing -- Edit all you want.
	 *  
	 *  @param args not used
	 */
	public static void main(String[] args)
	{
		int n = 10;
		LinearProbingMap<String, Integer> dict = new LinearProbingMap<>(n*2);
		
		for (int i = 1; i <= n; i++) {
			dict.put(""+i, i);
		}
		System.out.println(dict.size());
		if (dict.size() == 10) {
			System.out.println("Yay1");
		}
		if (dict.get("5").equals(5)) {
			System.out.println("Yay2");
		}
		
		dict.put("6", 60);
		dict.put("10", 100);
		dict.put("20", 200);
		if (dict.get("6") == 60) {
			System.out.println("Yay3");
		}
		if (dict.get("10") == 100) {
			System.out.println("Yay4");
		}
		if (dict.get("20") == 200) {
			System.out.println("Yay5");
		}
		System.out.println(dict.size);
		if (dict.size() == 11) {
			System.out.println("Yay6");
		}
		
		if (dict.get("200") == null) {
			System.out.println("Yay7");
		}	
	}
}
