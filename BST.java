public class BST<K extends Comparable<K>, V> {
    public static int nodeCount = 0;// do not get rid of this!
    Node root;
    int hlvl;
    /* CONSTRUCTOR */
    public BST() {
        root.key = null;
        hlvl=0;
    }

    /* PUBLIC METHODS */

    /***
     * insert a new (key, val) into tree or replace value associated with existing
     * key
     */
    public void put(K key, V val) {
        Node newNode = new Node(key, val);
        nodeCount+=1;
        newNode.height=0;
        if (root.key == null) {
            root = newNode;
            root.height=1;
        } else {
            if (key.compareTo(root.key) > 0) {
                if (root.right().key == null) {
                    root.right(newNode);
                } else {
                    putHelp(root.right(), newNode, 1);
                }
            } else if (key.compareTo(root.key) < 0) {
                if (root.left().key == null) {
                    root.left(newNode);
                } else {
                    putHelp(root.left(), newNode, 1);
                }
            } else if (key.compareTo(root.key) == 0) {
                root.val = val;
            }
        }
    }

    private void putHelp(Node rt, Node comp, int hlvl) {
        if (comp.key.compareTo(rt.key) > 0) {
            if (rt.right().key == null) {
                comp.height = hlvl + 1;
                rt.right(comp);
            } else {
                putHelp(rt.right(), comp, hlvl + 1);
            }
        } else if (comp.key.compareTo(rt.key) < 0) {
            if (rt.left().key == null) {
                comp.height = hlvl + 1;
                rt.left(comp);
            } else {
                putHelp(rt.left(), comp, hlvl + 1);
            }
        } else if (rt.key.compareTo(comp.key) == 0) {
            rt.val = comp.val;
        }
    }

    /***
     * get the value associated with the given key; return null if key doesn't exist
     */
    public V get(K key) {
        if (root.key == null) {
            return null;
        }
        if (key.compareTo(root.key) > 0) {
            return getHelp(root.right(), key).val;
        }
        if (key.compareTo(root.key) < 0) {
            return getHelp(root.left(), key).val;
        } return root.val;
    }

    private Node getHelp(Node rt, K key) {
        if(key.compareTo(rt.key)==0){
            return rt;
        }
        if (key.compareTo(rt.key) >0) {
            getHelp(rt.right(), key);
        } else if (key.compareTo(rt.key) < 0) {
            getHelp(rt.left(), key);
        }
        return null;
    }

    /***
     * return true if there is a value paired with with the given key and false
     * otherwise
     ***/
    public boolean contains(K key) {
        if(key.compareTo(root.key)==0){
            return true;
        }else{
            return getHelp(root, key)!=null;
        }
    }

    /***
     * return true if the tree is empty and false otherwise
     */
    public boolean isEmpty() {
        if(nodeCount==0){
            return true;
        }return false;
    }

    /***
     * return the number of Nodes in the tree
     */
    public int size() {
        return nodeCount;
    }

    /***
     * returns the height of the tree
     */
    public int height() {
      if (isEmpty()){
        return -1;  
      }
      else{
        return hlvl;
      }
    }

    /***
     * returns the height of node with given key in the tree; return -1 if the key
     * does not exist in the tree
     ***/
    public int height(K key) {
        Node temp = getHelp(root, key);
        if(temp!=null){
            return temp.height;
        }
        return -1;
    }

    /***
     * return the depth of the node with the given key in the tree; return -1 if the
     * key does not exist in the tree
     ***/
    public int depth(K key) {
        // to be implemented
    }

    /***
     * return the size of the subtree rooted at the node with the given key return
     * -1 if the key is not in the tree
     ***/
    public int size(K key) {
        // to be implemented
    }

    /***
     * return the minimum key or null if the tree is empty
     ***/
    public K min() {
        // to be implemented
    }

    /***
     * return the maximum key or nul if the tree is empty
     ***/
    public K max() {
        // to be implemented
    }

    /***
     * return the largest key that is less than or equal to the parameter or null if
     * such a key does not exist
     ***/
    public K floor(K key) {
        // to be implemented
        return null;
    }

    /***
     * return the smallest key that is greater than or equal to the parameter or
     * null if such a key does not exist
     ***/
    public K ceil(K key) {
        // to be implemented
        return null;
    }

    /***
     * return the number of keys that are less than the parameter or -1 if the key
     * does not exist
     ***/
    public int rank(K key) {
        // to be implemented
        return -999;
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
            this.left = l;
        }

        // getter for the right child
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