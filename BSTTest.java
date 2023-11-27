import java.util.Arrays;
public class BSTTest {
    //these are for the first test on a tree with integer keys and values
    private static int[] tree1 = new int[]{10,4,2,0,12,8,6,14,20,16};
    private static int[] putCounts1 = new int[]{0,3,5,7,3,5,7,5,7,9};
    private static int[] getCounts1 = new int[]{0,1,2,3,1,2,3,2,3,4};
    private static int[] heights1 = new int[]{4,2,1,0,3,1,0,2,1,0};
    private static int[] heightCounts1 = new int[]{0,1,2,3,1,2,3,2,3,4};
    private static int[] depths1 = new int[]{0,1,2,3,1,2,3,2,3,4};
    private static int[] depthCounts1 = new int[]{0,1,2,3,1,2,3,2,3,4};
    private static int[] sizes1 = new int[]{10,5,2,1,4,2,1,3,2,1};
    private static int[] sizeCounts1 = new int[]{0,1,2,3,1,2,3,2,3,4};
    private static int[] fcKeys1 = new int[]{0,1,3,5,7,9,11,12,15,17};
    private static int[] floors1 = new int[]{0,0,2,4,6,8,10,12,14,16};
    private static int[] floorCounts1 = new int[]{4,4,4,4,4,3,2,1,5,5};
    private static int[] ceils1 = new int[]{0,2,4,6,8,10,12,12,16,20};
    private static int[] ceilCounts1 = new int[]{3,4,3,4,4,3,2,1,5,5};
    private static int[] ranks1 = new int[]{5,2,1,0,6,4,3,7,9,8};
    private static int[] rankCounts1 = new int[]{1,3,5,7,4,6,8,7,10,12};
    private static int[] selectCounts1 = new int[]{1,4,7,10,3,6,9,5,7,10};

    //these are for the second test on a tree with String keys and integer values
    private static String[] tree2 = new String[]{"C","L","I","F","O","R","Z","D","G","W","Y","K","B","H","M","Q","S","U","V","X"};
    private static int[] putCounts2 = new int[]{0,3,5,7,5,7,9,9,9,11,13,7,3,11,7,9,13,15,17,15};
    private static String[] testKeys = new String[]{"C","L","G","Q","V","O","B","A","N","S"};
    private static Integer[] getRes = new Integer[]{0,1,8,15,18,4,12,null,null,16};
    private static int[] getCounts2 = new int[]{0,1,4,4,8,2,1,2,4,6};
    private static boolean[] containsRes = new boolean[]{true,true,true,true,true,true,true,false,false,true};
    private static int[] heights2 = new int[]{8,7,1,0,0,6,0,-1,-1,2};
    private static int[] depths2 = new int[]{0,1,4,4,8,2,1,-1,-1,6};
    private static int[] sizes2 = new int[]{20,18,2,1,1,11,1,-1,-1,3};
    private static String[] fcKeys2 = new String[]{"S","A","N","D","E","J","P","T","Z","V"};
    private static String[] floors2 = new String[]{"S",null,"M","D","D","I","O","S","Z","V"};
    private static int[] fcCounts2 = new int[]{6,2,4,4,5,4,5,8,4,8};
    private static String[] ceils2 = new String[]{"S","B","O","D","F","K","Q","U","Z","V"};
    private static int[] ranks2 = new int[]{1,8,4,11,15,10,0,-1,21,13};
    private static int[] rankCounts2 = new int[]{1,4,11,12,23,7,3,4,11,17};
    private static String[] selectKeys = new String[]{"C","L","G","Q","V","O","B",null,null,"S"};
    private static int[] selectCounts2 = new int[]{1,3,11,10,19,5,4,0,0,15};
    
    
    public static void main(String[] args) {
	double score = 0.0;
	//testing tree 1
	score += testInt(tree1, 1);
	score += testString();
	System.out.println("\nTotal Expected Score: " + (int)(score+0.5));
    }

    private static double testInt(int[] keys, int test) {
	print("****************************************************************************" +
	      "****Test 2: Testing the methods on a tree with Integer keys****\n" +
	      "**********************************************************************************");
	double score = 0.0;

	//build the tree
	BST.nodeCount = 0;
	BST<Integer,Integer> tree = new BST<Integer,Integer>();
	//check that the tree is empty
	print("Checking that the tree is empty...");
	if(tree.isEmpty())
	    score += 0.1;
	else
	    failed();
	//check that the size is 0
	print("Checking that the size is 0...");
	if(tree.size() == 0)
	    score += 0.1;
	else
	    failed();
	//build the tree
	print("Building the tree...");
	for(int i = 0; i < keys.length; i++) {
	    print("\tinserting " + keys[i]);
	    tree.nodeCount = 0;
	    tree.put(keys[i], i);
	    if(checkCount(tree.nodeCount, putCounts1[i]))
		score += 0.1;
	}
	//check that the tree is no longer empty
	print("Checking that the tree is not empty...");
	if(!tree.isEmpty())
	    score += 0.1;
	else
	    failed();
	//check the size
	print("Checking the size...");
	if(tree.size() == 10)
	    score += 0.1;
	else
	    failed();
	//testing get method
	print("Testing the get method...");
	for(int i = 0; i < keys.length; i++) {
	    print("\tsearching for " + keys[i]);
	    tree.nodeCount = 0;
	    int val = tree.get(keys[i]);
	    if(val == i)
		score += 0.1;
	    else
		failed();
	    if(checkCount(tree.nodeCount, getCounts1[i]))
		score += 0.1;
	}
	print("\tsearching for 11");
	if(tree.get(11) == null)
	    score += 0.1;
	else
	    failed();
	//testing contains method
	print("Testing contains method...");
	for(int i = 0; i < keys.length; i++) {
	    print("\ttesting contains for " + keys[i]);
	    if(tree.contains(keys[i]))
		score += 0.1;
	    else
		failed();
	}
	print("\ttesting contains for 11");
	if(!tree.contains(11))
	    score += 0.1;
	else
	    failed();
	//testing height of tree
	print("Testing height of tree...");
        tree.nodeCount = 0;
	if(tree.height() == 4)
	    score += 0.1;
	else
	    failed();
	if(checkCount(tree.nodeCount, 0))
	    score += 0.1;
	//testing height of nodes
	print("Testing height of nodes...");
        for(int i = 0; i < keys.length; i++) {
	    print("\ttesting height of " + keys[i]);
	    tree.nodeCount = 0;
	    int h = tree.height(keys[i]);
	    if(h == heights1[i]) 
		score += 0.1;
	    else
		failed();
	    if(checkCount(tree.nodeCount,heightCounts1[i]))
		score += 0.1;
	}
	//testing depth of nodes
	print("Testing depth of nodes...");
	for(int i = 0; i < keys.length; i++) {
	    print("\ttesting depth of " + keys[i]);
	    tree.nodeCount = 0;
	    int d = tree.depth(keys[i]);
	    if(d == depths1[i])
		score += 0.1;
	    else
		failed();
	    if(checkCount(tree.nodeCount, depthCounts1[i]))
		score += 0.1;
	}
	//testing size of node subtrees
	print("Testing node subtree size...");
	for(int i = 0; i < keys.length; i++) {
	    print("\ttesting size of subtree at node " + keys[i]);
	    tree.nodeCount = 0;
	    int n = tree.size(keys[i]);
	    if(n == sizes1[i])
		score += 0.1;
	    else
		failed();
	    if(checkCount(tree.nodeCount, sizeCounts1[i]))
		score += 0.1;
	}
	//testing min method
	print("Testing min method...");
	tree.nodeCount = 0;
	int min = tree.min();
	if(min == 0)
	    score += 0.1;
	else
	    failed();
	if(checkCount(tree.nodeCount, 4))
	    score += 0.1;
	//testing max method
	print("Testing max method...");
	tree.nodeCount = 0;
	int max = tree.max();
	if(max == 20)
	    score += 0.1;
	else
	    failed();
	if(checkCount(tree.nodeCount, 4))
	    score += 0.1;
	//testing floor method
	print("Testing floor method...");
	for(int i = 0; i < fcKeys1.length; i++) {
	    print("\ttesting floor of " + fcKeys1[i]);
	    tree.nodeCount = 0;
	    int f = tree.floor(fcKeys1[i]);
	    if(f == floors1[i])
		score += 0.1;
	    else
		failed();
	    if(checkCount(tree.nodeCount, floorCounts1[i]))
		score += 0.1;
	}
	print("\ttesting floor of -1");
	if(tree.floor(-1) == null)
	    score += 0.1;
	else
	    failed();
	//testing ceil method
	print("Testing ceil method...");
	for(int i = 0; i < fcKeys1.length; i++) {
	    print("\ttesting ceil of " + fcKeys1[i]);
	    tree.nodeCount = 0;
	    int c = tree.ceil(fcKeys1[i]);
	    if(c == ceils1[i])
		score += 0.1;
	    else
		failed();
	    if(checkCount(tree.nodeCount, ceilCounts1[i]))
		score += 0.1;
	}
	print("\ttesting ceil of 21");
	if(tree.ceil(21) == null)
	    score += 0.1;
	else
	    failed();
	//testing rank method
	print("Testing rank method...");
	for(int i = 0; i < keys.length; i++) {
	    print("\trank of " + keys[i]);
	    tree.nodeCount = 0;
	    int r = tree.rank(keys[i]);
	    if(r == ranks1[i])
		score += 0.1;
	    else
		failed();
	    if(checkCount(tree.nodeCount, rankCounts1[i]))
		score += 0.1;
	}
	print("\trank of 21");
	if(tree.rank(21) == -1)
	    score += 0.1;
	else
	    failed();
	//testing select method
	print("Testing select method...");
	for(int i = 0; i < ranks1.length; i++) {
	    print("\tselect for rank " + ranks1[i]);
	    tree.nodeCount = 0;
	    int k = tree.select(ranks1[i]);
	    if(k == keys[i])
		score += 0.1;
	    else
		failed();
	    if(checkCount(tree.nodeCount, selectCounts1[i]))
		score += 0.1;
	}
	//testing size(lo...hi) method
	print("Testing size method for [lo...hi]...");
	tree.nodeCount = 0;
	if(tree.size(2,12) == 6)
	    score += 0.1;
	if(checkCount(tree.nodeCount, 9))
	    score += 0.1;
	tree.nodeCount = 0;
	if(tree.size(6,16) == 7)
	    score += 0.1;
	if(checkCount(tree.nodeCount, 20))
	    score += 0.1;

	return score;
    }

      private static double testString() {
	print("****************************************************************************" +
	      "****Test 2: Testing the methods on a tree with String keys****\n" +
	      "**********************************************************************************");
	double score = 0.0;

	//build the tree
	BST.nodeCount = 0;
	BST<String,Integer> tree = new BST<String,Integer>();
	//check that the tree is empty
	print("Checking that the tree is empty...");
	if(tree.isEmpty())
	    score += 0.1;
	else
	    failed();
	//check that the size is 0
	print("Checking that the size is 0...");
	if(tree.size() == 0)
	    score += 0.1;
	else
	    failed();
	//build the tree
	print("Building the tree...");
	for(int i = 0; i < tree2.length; i++) {
	    print("\tinserting " + tree2[i]);
	    tree.nodeCount = 0;
	    tree.put(tree2[i], i);
	    if(checkCount(tree.nodeCount, putCounts2[i]))
		score += 0.1;
	}
	//check that the tree is no longer empty
	print("Checking that the tree is not empty...");
	if(!tree.isEmpty())
	    score += 0.1;
	else
	    failed();
	//check the size
	print("Checking the size...");
	if(tree.size() == 20)
	    score += 0.1;
	else
	    failed();
	//testing get method
	print("Testing the get method...");
	for(int i = 0; i < testKeys.length; i++) {
	    print("\tsearching for " + testKeys[i]);
	    tree.nodeCount = 0;
	    Integer val = tree.get(testKeys[i]);
	    if(val == getRes[i])
		score += 0.1;
	    else
		failed();
	    if(checkCount(tree.nodeCount, getCounts2[i]))
		score += 0.1;
	}

	//testing contains method
	print("Testing contains method...");
	for(int i = 0; i < testKeys.length; i++) {
	    print("\ttesting contains for " + testKeys[i]);
	    if(tree.contains(testKeys[i])==containsRes[i])
		score += 0.1;
	    else
		failed();
	}

	//testing height of tree
	print("Testing height of tree...");
        tree.nodeCount = 0;
	if(tree.height() == 8)
	    score += 0.1;
	else
	    failed();
	if(checkCount(tree.nodeCount, 0))
	    score += 0.1;
	//testing height of nodes
	print("Testing height of nodes...");
        for(int i = 0; i < testKeys.length; i++) {
	    print("\ttesting height of " + testKeys[i]);
	    tree.nodeCount = 0;
	    int h = tree.height(testKeys[i]);
	    if(h == heights2[i]) 
		score += 0.1;
	    else
		failed();
	    if(checkCount(tree.nodeCount,getCounts2[i]))
		score += 0.1;
	}
	//testing depth of nodes
	print("Testing depth of nodes...");
	for(int i = 0; i < testKeys.length; i++) {
	    print("\ttesting depth of " + testKeys[i]);
	    tree.nodeCount = 0;
	    int d = tree.depth(testKeys[i]);
	    if(d == depths2[i])
		score += 0.1;
	    else
		failed();
	    if(checkCount(tree.nodeCount, getCounts2[i]))
		score += 0.1;
	}
	//testing size of node subtrees
	print("Testing node subtree size...");
	for(int i = 0; i < testKeys.length; i++) {
	    print("\ttesting size of subtree at node " + testKeys[i]);
	    tree.nodeCount = 0;
	    int n = tree.size(testKeys[i]);
	    if(n == sizes2[i])
		score += 0.1;
	    else
		failed();
	    if(checkCount(tree.nodeCount, getCounts2[i]))
		score += 0.1;
	}
	//testing min method
	print("Testing min method...");
	tree.nodeCount = 0;
	String min = tree.min();
	if(min.equals("B"))
	    score += 0.1;
	else
	    failed();
	if(checkCount(tree.nodeCount, 4))
	    score += 0.1;
	//testing max method
	print("Testing max method...");
	tree.nodeCount = 0;
	String max = tree.max();
	if(max.equals("Z"))
	    score += 0.1;
	else
	    failed();
	if(checkCount(tree.nodeCount, 4))
	    score += 0.1;
	//testing floor method
	print("Testing floor method...");
	for(int i = 0; i < fcKeys2.length; i++) {
	    print("\ttesting floor of " + fcKeys2[i]);
	    tree.nodeCount = 0;
	    String f = tree.floor(fcKeys2[i]);
	    if(f == null && floors2[i] == null || f.equals(floors2[i]))
		score += 0.1;
	    else
		failed();
	    if(checkCount(tree.nodeCount, fcCounts2[i]))
		score += 0.1;
	}
	//testing ceil method
	print("Testing ceil method...");
	for(int i = 0; i < fcKeys2.length; i++) {
	    print("\ttesting ceil of " + fcKeys2[i]);
	    tree.nodeCount = 0;
	    String c = tree.ceil(fcKeys2[i]);
	    if(c == null && ceils2[i] == null || c.equals(ceils2[i]))
		score += 0.1;
	    else
		failed();
	    if(checkCount(tree.nodeCount, fcCounts2[i]))
		score += 0.1;
	}
	//testing rank method
	print("Testing rank method...");
	for(int i = 0; i < testKeys.length; i++) {
	    print("\trank of " + testKeys[i]);
	    tree.nodeCount = 0;
	    int r = tree.rank(testKeys[i]);
	    if(r == ranks2[i])
		score += 0.1;
	    else
		failed();
	    if(checkCount(tree.nodeCount, rankCounts2[i]))
		score += 0.1;
	}
	//testing select method
	print("Testing select method...");
	for(int i = 0; i < ranks2.length; i++) {
	    print("\tselect for rank " + ranks2[i]);
	    tree.nodeCount = 0;
	    String k = tree.select(ranks2[i]);
	    if(k == null && selectKeys[i] == null || k.equals(selectKeys[i]))
		score += 0.1;
	    else
		failed();
	    if(checkCount(tree.nodeCount, selectCounts2[i]))
		score += 0.1;
	}
	//testing size(lo...hi) method
	print("Testing size method for [lo...hi]...");
	tree.nodeCount = 0;
	if(tree.size("I","S") == 8)
	    score += 0.1;
	if(checkCount(tree.nodeCount, 23))
	    score += 0.1;
	tree.nodeCount = 0;
	if(tree.size("D","R") == 11)
	    score += 0.1;
	if(checkCount(tree.nodeCount, 20))
	    score += 0.1;

	return score;
    }

    private static void failed() {
	System.out.println("\nTest failed.\n");
    }

    private static void print(String msg) {
	System.out.println();
	System.out.println(msg);
    }

    private static boolean checkCount(int act, int exp) {
	if(act >= exp/2 && act <= exp*5/4)
	    return true;
	System.out.println("\nNode count is not right.");
	System.out.println("Expected: " + exp);
	System.out.println("Actual: " + act + "\n");
	return false;
    }
}
	
