import java.util.Arrays;
import java.util.Iterator;

public class TreeSort{
	/** Sorts an array using TreeSort with a balanced BST implementation 
	 * @param a an array to sort
	 */
	public static <E> void sort( E[] a){
		Tree <E> tree = new A2AVLTree<>();
		sort(tree, a);
		
	}
	
	/**
	 * Sorts an array using TreeSort with a specified BST
	 * @param tree tree to use
	 * @param a an array to sort
	 */
	public static <E> void sort(Tree <E> tree, E[] a){
		//to do
		
		for(int i=0;i<a.length;i++){
			
			  tree.add(a[i]);
		}
	
		int index=0;
		Iterator<E> it=tree.iterator();
		while(it.hasNext()){
			E b=it.next();
			//System.out.print(b+" ");
			//index++;
			
			a[index++]=b;
		}
		
		//System.out.println("");
		//System.out.println("index="+index);
		
		
	}
}
