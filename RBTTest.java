import java.util.Arrays;
public class RBTTest {
    //these are for the first test on a tree with integer keys and values
    private static int[] tree1 = new int[]{10,4,2,0,12,8,6,14,20,16};
    private static String[] colors1 = new String[]{"BLACK","BLACK","RED","BLACK","BLACK","RED","BLACK","BLACK","RED","BLACK"};
    private static int[] heights1 = new int[]{3,2,0,1,0,0,1,2,0,1};
    private static int[] depths1 = new int[]{0,1,3,2,2,3,2,1,3,2};
    private static int[] sizes1 = new int[]{10,5,1,2,1,1,2,4,1,2};

    //these are for the second test on a tree with String keys and integer values
    private static String[] tree2 = new String[]{"C","L","I","F","O","R","Z","D","G","W","Y","K","B","H","M","Q","S","U","V","X"};
    private static String[] testKeys = new String[]{"C","L","G","Q","V","O","B","A","N","S"};
    private static Integer[] getRes = new Integer[]{0,1,8,15,18,4,12,null,null,16};
    private static boolean[] containsRes = new boolean[]{true,true,true,true,true,true,true,false,false,true};
    private static int[] heights2 = new int[]{0,1,1,0,0,4,1,-1,-1,0};
    private static int[] depths2 = new int[]{4,2,3,3,3,0,3,-1,-1,3};
    private static int[] sizes2 = new int[]{1,3,3,1,1,20,2,-1,-1,1};
    private static String[] colors2 = new String[]{"RED","BLACK","RED","BLACK","BLACK","BLACK","BLACK",null,null,"BLACK"}; 
    
    
    public static void main(String[] args) {
	double score = 0.0;
	//testing tree 1
	score += testInt(tree1, 1);
	//testing tree 2
	score += testString();
	System.out.println("\nTotal Expected Score: " + (int)(score+0.5));
    }

    private static double testInt(int[] keys, int test) {
	print("****************************************************************************" +
	      "****Test 1: Testing the methods on a tree with Integer keys****\n" +
	      "**********************************************************************************");
	double score = 0.0;

	//build the tree
	RBT<Integer,Integer> tree = new RBT<Integer,Integer>();
	//check that the tree is empty
	print("Checking that the tree is empty...");
	if(tree.isEmpty())
	    score += 0.25;
	else
	    failed();
	//check that the size is 0
	print("Checking that the size is 0...");
	if(tree.size() == 0)
	    score += 0.25;
	else
	    failed();
	//build the tree
	print("Building the tree...");
	for(int i = 0; i < keys.length; i++) {
	    print("\tinserting " + keys[i]);
	    tree.put(keys[i], i);
	}
	//check that the tree is no longer empty
	print("Checking that the tree is not empty...");
	if(!tree.isEmpty())
	    score += 0.25;
	else
	    failed();
	//check the size
	print("Checking the size...");
	if(tree.size() == 10)
	    score += 0.25;
	else
	    failed();
	//testing get method
	print("Testing the get method...");
	for(int i = 0; i < keys.length; i++) {
	    print("\tsearching for " + keys[i]);
	    Integer val = tree.get(keys[i]);
	    if(val == i)
		score += 0.25;
	    else
		failed();
	}
	print("\tsearching for 11");
	if(tree.get(11) == null)
	    score += 0.25;
	else
	    failed();
	//testing contains method
	print("Testing contains method...");
	for(int i = 0; i < keys.length; i++) {
	    print("\ttesting contains for " + keys[i]);
	    if(tree.contains(keys[i]))
		score += 0.25;
	    else
		failed();
	}
	print("\ttesting contains for 11");
	if(!tree.contains(11))
	    score += 0.25;
	else
	    failed();
	//testing getColor method
	print("Testing getColor method...");
	for(int i = 0; i < keys.length; i++) {
	    print("\ttesting getColor for " + keys[i]);
	    if(colors1[i].equals(tree.getColor(keys[i])))
		score += 0.25;
	    else
		failed();
	}
	print("\ttesting getColor for 11");
	if(tree.getColor(11) == null)
	    score += 0.25;
	else
	    failed();
	//testing height of tree
	print("Testing height of tree...");
	if(tree.height() == 3)
	    score += 0.25;
	else
	    failed();
	//testing height of nodes
	print("Testing height of nodes...");
        for(int i = 0; i < keys.length; i++) {
	    print("\ttesting height of " + keys[i]);
	    int h = tree.height(keys[i]);
	    if(h == heights1[i]) 
		score += 0.25;
	    else
		failed();
	}
	//testing depth of nodes
	print("Testing depth of nodes...");
	for(int i = 0; i < keys.length; i++) {
	    print("\ttesting depth of " + keys[i]);
	    int d = tree.depth(keys[i]);
	    if(d == depths1[i])
		score += 0.25;
	    else
		failed();
	}
	//testing size of node subtrees
	print("Testing node subtree size...");
	for(int i = 0; i < keys.length; i++) {
	    print("\ttesting size of subtree at node " + keys[i]);
	    int n = tree.size(keys[i]);
	    if(n == sizes1[i])
		score += 0.25;
	    else
		failed();
	}

	return score;
    }

      private static double testString() {
	print("****************************************************************************" +
	      "****Test 2: Testing the methods on a tree with String keys****\n" +
	      "**********************************************************************************");
	double score = 0.0;

	//build the tree
	RBT<String,Integer> tree = new RBT<String,Integer>();
	//check that the tree is empty
	print("Checking that the tree is empty...");
	if(tree.isEmpty())
	    score += 0.25;
	else
	    failed();
	//check that the size is 0
	print("Checking that the size is 0...");
	if(tree.size() == 0)
	    score += 0.25;
	else
	    failed();
	//build the tree
	print("Building the tree...");
	for(int i = 0; i < tree2.length; i++) {
	    print("\tinserting " + tree2[i]);
	    tree.put(tree2[i], i);
	}
	//check that the tree is no longer empty
	print("Checking that the tree is not empty...");
	if(!tree.isEmpty())
	    score += 0.25;
	else
	    failed();
	//check the size
	print("Checking the size...");
	if(tree.size() == 20)
	    score += 0.25;
	else
	    failed();
	//testing get method
	print("Testing the get method...");
	for(int i = 0; i < testKeys.length; i++) {
	    print("\tsearching for " + testKeys[i]);
	    Integer val = tree.get(testKeys[i]);
	    if(val == getRes[i])
		score += 0.25;
	    else
		failed();
	}

	//testing getColor method
	print("Testing getColor method...");
	for(int i = 0; i < testKeys.length; i++) {
	    print("\ttesting getColor for " + testKeys[i]);
	    if(colors2[i] == null && tree.getColor(testKeys[i]) == null ||
	       colors2[i].equals(tree.getColor(testKeys[i])))
		score += 0.25;
	    else
		failed();
	}

	//testing contains method
	print("Testing contains method...");
	for(int i = 0; i < testKeys.length; i++) {
	    print("\ttesting contains for " + testKeys[i]);
	    if(tree.contains(testKeys[i])==containsRes[i])
		score += 0.25;
	    else
		failed();
	}

	//testing height of tree
	print("Testing height of tree...");
 	if(tree.height() == 4)
	    score += 0.25;
	else
	    failed();
	//testing height of nodes
	print("Testing height of nodes...");
        for(int i = 0; i < testKeys.length; i++) {
	    print("\ttesting height of " + testKeys[i]);
	    int h = tree.height(testKeys[i]);
	    if(h == heights2[i]) 
		score += 0.25;
	    else
		failed();
	}
	//testing depth of nodes
	print("Testing depth of nodes...");
	for(int i = 0; i < testKeys.length; i++) {
	    print("\ttesting depth of " + testKeys[i]);
	    int d = tree.depth(testKeys[i]);
	    if(d == depths2[i])
		score += 0.25;
	    else
		failed();
	}
	//testing size of node subtrees
	print("Testing node subtree size...");
	for(int i = 0; i < testKeys.length; i++) {
	    print("\ttesting size of subtree at node " + testKeys[i]);
	    int n = tree.size(testKeys[i]);
	    if(n == sizes2[i])
		score += 0.25;
	    else
		failed();
	}

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
	
