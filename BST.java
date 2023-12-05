import java.lang.Math;

public class BST<K extends Comparable<K>, V> {
	public static int nodeCount = 0;// do not get rid of this!
	Node root;

	/* CONSTRUCTOR */
	public BST() {
		root = null;
	}

	/* PUBLIC METHODS */

	/***
	 * insert a new (key, val) into tree or replace value associated with existing
	 * key
	 */
	public void put(K key, V val) {
		Node newNode = new Node(key, val);
		newNode.height = 0;
		if (root == null) {
			root = newNode;
			root.height = 0;
			return;
		}
		root = putHelp(root, newNode);
	}

	private Node putHelp(Node rt, Node comp) {
		Node tempR = rt.right();
		Node tempL = rt.left();
		if (comp.key.compareTo(rt.key) > 0) {
			if (tempR == null) {
				comp.height = 0;
				rt.right(comp);
				tempR = comp;
			} else {
				putHelp(tempR, comp);
			}
		} else if (comp.key.compareTo(rt.key) < 0) {
			if (tempL == null) {
				comp.height = 0;
				rt.left(comp);
				tempL = comp;
			} else {
				putHelp(tempL, comp);
			}
		} else if (rt.key.compareTo(comp.key) == 0) {
			rt.val = comp.val;
		}
		if (tempR != null && tempL != null) {
			rt.height = Math.max(tempL.height, tempR.height) + 1;
			rt.N++;
			return rt;
		} else if (tempL != null) {
			rt.height = tempL.height + 1;
			rt.N++;
			return rt;
		} else if (tempR != null) {
			rt.height = tempR.height + 1;
			rt.N++;
			return rt;
		}
		return rt;
	}

	/***
	 * get the value associated with the given key; return null if key doesn't exist
	 */
	public V get(K key) {
		if (root.key == null) {
			return null;
		}
		Node retVal = getHelp(root, key);
		if (retVal == null) {
			return null;
		}
		return retVal.val;
	}

	/***
	 * assists get with returning a key
	 */
	private Node getHelp(Node rt, K key) {
		if (rt == null) {
			return null;
		}
		if (key.compareTo(rt.key) == 0) {
			return rt;
		}
		if (key.compareTo(rt.key) > 0) {
			return getHelp(rt.right(), key);
		} else if (key.compareTo(rt.key) < 0) {
			return getHelp(rt.left(), key);
		}
		return null;
	}

	/***
	 * return true if there is a value paired with with the given key and false
	 * otherwise
	 ***/
	public boolean contains(K key) {
		if (key.compareTo(root.key) == 0) {
			return true;
		} else {
			return getHelp(root, key) != null;
		}
	}

	/***
	 * return true if the tree is empty and false otherwise
	 */
	public boolean isEmpty() {
		if (root == null) {
			return true;
		}
		return false;
	}

	/***
	 * return the number of Nodes in the tree
	 */
	public int size() {
		if (root == null) {
			return 0;
		}
		return root.N;
	}

	/***
	 * returns the height of the tree
	 */
	public int height() {
		System.out.println("height testing====" + root.height);
		if (isEmpty()) {
			return -1;
		} else {
			return root.height;
		}
	}

	/***
	 * returns the height of node with given key in the tree; return -1 if the key
	 * does not exist in the tree
	 ***/
	public int height(K key) {
		Node temp = getHelp(root, key);
		if (temp != null) {
			return temp.height;
		}
		return -1;
	}

	/***
	 * return the depth of the node with the given key in the tree; return -1 if the
	 * key does not exist in the tree
	 ***/
	public int depth(K key) {
		int depthcount = 0;
		return depthHelp(root, key, depthcount);

	}

	private int depthHelp(Node rt, K key, int depthCount) {
		if (key == null) {
			return -1;
		}
		if (key.compareTo(rt.key) == 0) {
			return depthCount;
		}
		if (key.compareTo(rt.key) > 0) {
			return depthHelp(rt.right(), key, (depthCount + 1));
		} else if (key.compareTo(rt.key) < 0) {
			return depthHelp(rt.left(), key, (depthCount + 1));
		}
		return -1;
	}

	/***
	 * return the size of the subtree rooted at the node with the given key return
	 * -1 if the key is not in the tree
	 ***/
	public int size(K key) {
		Node temp = getHelp(root, key);
		if (temp != null) {
			return temp.N;
		}
		return -1;
	}

	/***
	 * return the minimum key or null if the tree is empty
	 ***/
	public K min() {
		if (root == null) {
			return null;
		} else {
			return minHelp(root);
		}
	}

	private K minHelp(Node rt) {
		Node temp = rt.left();
		if (temp != null) {
			return minHelp(temp);
		}
		System.out.println(rt.key);
		if(temp==null) {
		return rt.key;
	}
		return null;
	}

	/***
	 * return the maximum key or nul if the tree is empty
	 ***/
	public K max() {
		if (root == null) {
			return null;
		} else {
			return maxHelp(root);
		}
	}

	private K maxHelp(Node rt) {
		Node temp = rt.right();
		if (temp != null) {
			return maxHelp(temp);
		}
		return rt.key;
	}

	/***
	 * return the largest key that is less than or equal to the parameter or null if
	 * such a key does not exist
	 ***/
	public K floor(K key) {
		Node temp = floorHelp(root, key);
		if (temp != null) {
			return temp.key;
		}
		return null;
	}

	private Node floorHelp(Node rt, K key) {
		if (rt.key == key) {
			return rt;
		}
		if (key.compareTo(rt.key) > 0) {
			Node compR = rt.right();
			if (compR!=null && key.compareTo(compR.key) >= 0) {
				return floorHelp(compR, key);
			} if(compR!=null) {
				Node compL2 = compR.left();
			if(compL2!=null) {
			if(key.compareTo(compL2.key)<0){
				return compL2;
			}
			}
			}
		}
		Node compL = rt.left();
		if (compL!=null&&key.compareTo(rt.key) < 0) {
			return floorHelp(compL, key);
			}
		return rt;
	}

	/***
	 * return the smallest key that is greater than or equal to the parameter or
	 * null if such a key does not exist
	 ***/
	public K ceil(K key) {
		Node temp = ceilHelp(root, key);
		if (temp != null) {
			return temp.key;
		}
		return null;
	}

	private Node ceilHelp(Node rt, K key) {
		Node tempR = rt.right();
		Node tempL = rt.left();
		if (rt.key == key) {
			return rt;
		}
		if (rt.key.compareTo(key) < 0) {
			if (tempL.key.compareTo(key) >= 0) {
				floorHelp(tempL, key);
			} else {
				return rt;
			}
		}
		if (rt.key.compareTo(key) > 0) {
			floorHelp(tempR, key);
		}
		return rt;
	}

	/***
	 * return the number of keys that are less than the parameter or -1 if the key
	 * does not exist
	 ***/
	public int rank(K key) {
		int retVal = rankHelp(root, key, 0);
		return retVal;
	}
	
	
	private int rankHelp(Node rt, K key, int compVal){
		if(rt==null) {
			return -1;
		}
		if(key.compareTo(rt.key)==0) {
			return compVal;
		}
		Node tempR = rt.right();
		Node tempL = rt.left();
		if(key.compareTo(rt.key)>0) {
			if(tempR!=null&&tempL!=null) {	
		int sum1 = tempL.N+1;
		int sum2 = rankHelp(tempR, key, compVal);
		if (sum2==-1) {
			return -1;
		}
		return sum1+sum2+1;
			}
		}
		else if(key.compareTo(rt.key)<0) {
			int sum3 = rankHelp(tempL, key, compVal);
			if(sum3==-1) {
				return -1;
			}
		}
		return -1;
	}

	/***
	 * return the key at the given rank or null if the rank passed in does not make
	 * sense given the tree
	 ***/
	public K select(int rank) {
		// to be implemented
		return null;
	}

	/***
	 * return the number of keys in [lo...hi]
	 ***/
	public int size(K lo, K hi) {
		// to be implemented
		return -1;
	}

	/* NODE CLASS */
	/*** WARNING: DO NOT CHANGE THIS CLASS! ***/
	private class Node {
		K key;// the key
		V val;// the value
		private Node left, right;// left and right children
		int height;// height of the node
		int N;// number of nodes in the subtree

		// constructor
		public Node(K key, V val) {
			this.key = key;
			this.val = val;
			this.N = 1;
		}

		// getter for the left child
		public Node left() {
			nodeCount++;
			return this.left;
		}

		// getter for the right child
		public Node right() {
			nodeCount++;
			return this.right;
		}

		// setter for the left child
		public void left(Node l) {
			nodeCount++;
			this.left = l;;
		}

		// setter for the right child
		public void right(Node r) {
			nodeCount++;
			this.right = r;
		}

		// toString method
		public String toString() {
			return "(" + key + ", " + val + ")";
		}
	}
}