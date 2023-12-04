public class RBT<K extends Comparable<K>, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;
    private int n;

    /* CONSTRUCTOR */
    public RBT() {
        root = null;
    }

    /* PUBLIC METHODS */

    /***
     *insert a new (key, val) into tree
     *or replace value of existing key
     */
    public void put(K key, V val) {
        Node newNode = new Node(key, val);
	    if(root == null){
            newNode.color=BLACK;
            root = newNode;
    }else{
        newNode.color=RED;
        if(key.compareTo(root.key)>0){
            putHelp(root, newNode);
        }
    }
    }

    private void putHelp(Node rt, Node comp){

    }

    /***
     * get the value associated with the given key;
     * return null if key doesn't exist
     */
    public V get(K key) {
        // to be implemented
    }

    /***
     * get the color of a node;
     * return "RED" if it is red,
     * "BLACK" if it is black,
     * and null if it does not exist
     ***/
    public String getColor(K key) {
        // to be implemented
        return null;
    }

    /***
     * return true if the tree
     * is empty and false
     * otherwise
     */
    public boolean isEmpty() {
        // to be implemented
    }

    /***
     * return the number of Nodes
     * in the tree
     */
    public int size() {
        // to be implemented
    }

    /***
     * returns the height of the tree
     */
    public int height() {
        // to be implemented
    }

    /***
     * returns the height of node
     * with given key in the tree;
     * return -1 if the key does
     * not exist in the tree
     */
    public int height(K key) {
        // to be implemented
    }

    /***
     * return the depth of the node
     * with the given key in the tree;
     * return -1 if the key does not exist
     * in the tree
     ***/
    public int depth(K key) {
        // to be implemented
    }

    /***
     * return true if the key exists in the tree
     * and false otherwise
     ***/
    public boolean contains(K key) {
        // to be implemented
    }

    /***
     * return the number of nodes in the subtree
     * that is rooted at the node with the given key
     * or -1 if the key does not exist
     ***/
    public int size(K key) {
        // to be implemented
    }

    /* PRIVATE METHODS */

    /***
     * performs the rotate left operation
     ***/
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    /***
     * performs the rotate right operation
     ***/
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    /***
     * performs the color flip operation
     ***/
    private void colorFlip(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
        if (h == root)
            h.color = BLACK;
    }

    /* NODE CLASS */
    /*** WARNING: DO NOT CHANGE THIS CLASS! ***/
    public class Node {
        K key;
        V val;
        Node left, right;
        int height;
        int N;
        boolean color;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
            this.N = 1;
            this.color = RED;
        }

        public String toString() {
            return "(" + key + ", " + val + ")";
        }
    }
}
