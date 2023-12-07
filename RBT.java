public class RBT<K extends Comparable<K>, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;
    /* CONSTRUCTOR */
    public RBT() {
        root = null;
    }

    /* PUBLIC METHODS */

    /***
     * insert a new (key, val) into tree
     * or replace value of existing key
     */
    public void put(K key, V val) {
        Node newNode = new Node(key, val);
        if (root == null) {
            newNode.color = BLACK;
            root = newNode;
        } else {
            newNode.color = RED;
            root = putHelp(root, newNode);

        }
    }

    private Node putHelp(Node rt, Node comp) {
        if (comp.key.compareTo(rt.key) == 0) {
            rt.val = comp.val;
        } else if (comp.key.compareTo(rt.key) < 0) {
            if (rt.left == null) {
                comp.height = 0;
                rt.left = comp;
            } else {
                rt.left = putHelp(rt.left, comp);
            }

        } else if (comp.key.compareTo(rt.key) > 0) {
            if (rt.right == null) {
                comp.height = 0;
                rt.right = comp;
            } else {
                rt.right = putHelp(rt.right, comp);
            }
        }
        if (rt.right != null && rt.left != null) {
            rt.height = Math.max(rt.left.height, rt.right.height) + 1;
            rt.N++;
            return rt;
        } else if (rt.left != null) {
            rt.height = rt.left.height + 1;
            rt.N++;
            return rt;
        } else if (rt.right != null) {
            rt.height = rt.right.height + 1;
            rt.N++;
            return rt;
        }

        if (rt.left != null && rt.left.color) {
            rt = rotateRight(rt);
            fixHeight(rt);
        }
        if (rt.right != null && rt.right.color && rt.right.right != null && rt.right.right.color) {
            rt = rotateLeft(rt);
            fixHeight(rt);
        }
        if (rt.left != null && rt.left.color && rt.right != null && rt.right.color) {
            colorFlip(rt);
        }
        return rt;
    }

    private int fixHeight(Node rt) {
        int leftH = 0;
        int rightH = 0;
        if (rt.left != null) {
            leftH = fixHeight(rt.left);
        }
        if (rt.right != null) {
            rightH = fixHeight(rt.right);
        }
        if (rt.right == null && rt.left == null) {
            return Math.max(leftH, rightH);
        }

        else {
            return (1 + Math.max(leftH, rightH));
        }
    }

    /***
     * get the value associated with the given key;
     * return null if key doesn't exist
     */
    public V get(K key) {
        Node temp = getHelp(root, key);
        if (isEmpty()) {
            return null;
        }
        if(temp== null){
            return null;
        }
        return temp.val;
    }
    //helps the get by cycling down
    private Node getHelp(Node rt, K key) {
        if (key.compareTo(rt.key) == 0) {
            return root;
        }
        if (key.compareTo(rt.key) < 0) {
            if (rt.left == null) {
                return null;
            } else {
                return getHelp(rt.left, key);
            }
        }
        if (key.compareTo(rt.key) > 0) {
            if (rt.right == null) {
                return null;
            } else {
                return getHelp(rt.right, key);
            }
        }
        return null;
    }

    /***
     * get the color of a node;
     * return "RED" if it is red,
     * "BLACK" if it is black,
     * and null if it does not exist
     ***/
    public String getColor(K key) {
        Node temp = getHelp(root, key);
        if (temp != null) {
            if (temp.color) {
                return "RED";
            } else {
                return "Black";
            }
        }
        return null;
    }

    /***
     * return true if the tree
     * is empty and false
     * otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }

    /***
     * return the number of Nodes
     * in the tree
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
        return root.height;
    }

    /***
     * returns the height of node
     * with given key in the tree;
     * return -1 if the key does
     * not exist in the tree
     */
    public int height(K key) {
        if (isEmpty()) {
            return -1;
        }
        Node temp = getHelp(root, key);
        if(temp==null){
            return -1;
        }
        return temp.height;
    }

    /***
     * return the depth of the node
     * with the given key in the tree;
     * return -1 if the key does not exist
     * in the tree
     ***/
    public int depth(K key) {
        int depthcount = 0;
        return depthHelp(root, key, depthcount);

    }

    private int depthHelp(Node rt, K key, int depthCount) {
        if (rt == null) {
            return -1;
        }
        if (key.compareTo(rt.key) == 0) {
            return depthCount;
        }
        if (key.compareTo(rt.key) > 0) {
            return depthHelp(rt.right, key, (depthCount + 1));
        } else if (key.compareTo(rt.key) < 0) {
            return depthHelp(rt.left, key, (depthCount + 1));
        }
        return -1;
    }

    /***
     * return true if the key exists in the tree
     * and false otherwise
     ***/
    public boolean contains(K key) {
        if (getHelp(root, key) != null) {
            return true;
        }
        return false;
    }

    /***
     * return the number of nodes in the subtree
     * that is rooted at the node with the given key
     * or -1 if the key does not exist
     ***/
    public int size(K key) {
        Node temp = getHelp(root, key);
        if (temp != null) {
            return temp.N;
        }
        return -1;
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
