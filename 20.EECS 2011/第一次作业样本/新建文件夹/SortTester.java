import java.util.Arrays;
import java.util.Collections;

public class SortTester {

	public static void main(String[] args) {
		
	  for(int N=10;N<=1000000;N*=10){
		 System.out.println("N = "+N+":");
		Number a [] = new Number[N];
		Number b [] = new Number[N];
		Number ar [] = new Number[N];
		Number br [] = new Number[N];
		Tree <Number> tree = new A2BSTree<>();
		Tree <Number> treeavl = new A2AVLTree();
		for (int i = 0; i < a.length; i++) a[i] = i;
		for (int i = a.length-1; i>=0; i--){
			ar[a.length-i-1]=i;
			br[a.length-i-1]=i;
		}
		//System.out.printf("Initial%n%s%n",Arrays.toString(a));
		
		Collections.shuffle(Arrays.asList(a));
		//System.out.printf("Shuffled%n%s%n",Arrays.toString(a));
		for (int i = 0; i < a.length; i++) b[i] = a[i];
		long startTime = System.currentTimeMillis();
		TreeSort.sort(tree, a);
		
		long endTime = System.currentTimeMillis();
		System.out.println("BST "+(endTime-startTime)+"ms");
		//System.out.printf("Sorted%n%s%n",Arrays.toString(a));
		
		startTime = System.currentTimeMillis();
		TreeSort.sort(treeavl, b);
	    endTime = System.currentTimeMillis();
		System.out.println("AVL "+(endTime-startTime)+"ms");
		//TreeSort.sort(a);
		//System.out.printf("Sorted%n%s%n",Arrays.toString(b));
		
		tree = new A2BSTree<>();
		startTime = System.currentTimeMillis();
		TreeSort.sort(tree, ar);
	    endTime = System.currentTimeMillis();
		System.out.println("BST(rev-sorted) "+(endTime-startTime)+"ms");
		//System.out.printf("Sorted%n%s%n",Arrays.toString(ar));
		
		treeavl = new A2AVLTree();
		startTime = System.currentTimeMillis();
		TreeSort.sort(treeavl, br);
	    endTime = System.currentTimeMillis();
		System.out.println("AVL(rev-sorted) "+(endTime-startTime)+"ms");
		//System.out.printf("Sorted%n%s%n",Arrays.toString(br));
		System.out.println("");
		
	  }
	}
}
