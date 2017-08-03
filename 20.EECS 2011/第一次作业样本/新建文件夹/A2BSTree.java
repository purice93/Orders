import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Stack;


public class A2BSTree <E> implements Tree<E>{

	private DataTree<E> tree;

	public A2BSTree(){
		tree = new DataTree<E>();
	}

	@Override
	public void add(E e) {
		tree.add(e);	
		
	}

	@Override
	public void addAll(Collection<? extends E> c) {
		tree.addAll(c);
	}

	@Override
	public boolean remove(Object o) {
		return tree.remove(o);
	}

	@Override
	public Iterator<E> iterator() {
		return tree.iterator();
	}

	@Override
	public int height() {
		return tree.height();
	}

	@Override
	public int size() {
		return tree.size();
	}
	public class DataTree<E> implements Tree<E>{
		Comparator<Number> ncmp=new Comparator<Number>() {
			
			@Override
			public int compare(Number o1, Number o2) {
				if(o1.intValue()>o2.intValue()){
					return 1;
				}
				else if(o1.intValue()<o2.intValue()){
					return -1;
				}
				return 0;
			}
		};
		/**
		 * the root node
		 */
		private BSTNode<E> mRoot; 
		/**
		 * the size of all elements
		 */
	    private int size;
	    public DataTree(){
	    	mRoot=null;
	    	size=0;
	    }
	    public class BSTNode<E> {
	        E key;                
	        BSTNode<E> left;      
	        BSTNode<E> right;    
	        BSTNode<E> parent;   
	        /**
	         * initialize members
	         * @param key key value
	         * @param parent parent node
	         * @param left   left child
	         * @param right  right child
	         */
	        public BSTNode(E key, BSTNode<E> parent, BSTNode<E> left, BSTNode<E> right) {
	            this.key = key;
	            this.parent = parent;
	            this.left = left;
	            this.right = right;
	        }
	        /**
	         * get the key
	         * @return
	         */
			public E getKey() {
				return key;
			}
	        /**
	         * set the key
	         * @param key key value
	         */
			public void setKey(E key) {
				this.key = key;
			}
	        /**
	         * get the left child
	         * @return
	         */
			public BSTNode<E> getLeft() {
				return left;
			}
	        /**
	         * set the left child
	         * @param left
	         */
			public void setLeft(BSTNode<E> left) {
				this.left = left;
			}
	        /**
	         * get the right child
	         * @return
	         */
			public BSTNode<E> getRight() {
				return right;
			}
	        /**
	         * set the right child
	         */
			public void setRight(BSTNode<E> right) {
				this.right = right;
			}
	        /**
	         * get the parent node
	         * @return
	         */
			public BSTNode<E> getParent() {
				return parent;
			}
	        /**
	         * set the parent node
	         * @param parent
	         */
			public void setParent(BSTNode<E> parent) {
				this.parent = parent;
			}
	        
	    }
	    /**
	     * add a node
	     */
		@Override
		public void add(E key) {
			if(mRoot==null){
				mRoot=new BSTNode<E>(key, null, null, null);
				size+=1;
				return;
			}
			@SuppressWarnings("unchecked")
			Comparable<? super E> k = (Comparable<? super E>)key;
			BSTNode<E> tempRoot=mRoot;
			if(k==null){
				if(!(key instanceof Number)){
					return;
				}	
			}
			while(true){
				int cmp=0;
				if(k!=null){
					cmp=k.compareTo(tempRoot.key);
				}
				else{
					cmp=ncmp.compare((Number)k, (Number)tempRoot.key);
				}
				if(cmp>=0){// into right
					if(tempRoot.right==null){
						BSTNode<E> node=new BSTNode<E>(key, tempRoot, null, null);
						tempRoot.right=node;
						size+=1;
						break;
					}
					else{
						tempRoot=tempRoot.right;
					}
				}
				else{ //into left
					if(tempRoot.left==null){
						BSTNode<E> node=new BSTNode<E>(key, tempRoot, null, null);
						tempRoot.left=node;
						size+=1;
						break;
					}
					else{
						tempRoot=tempRoot.left;
					}
				}
				
			}
			
		}
	    /**
	     * add all elements into the tree from c
	     */
		@Override
		public void addAll(Collection<? extends E> c) {
			if(c==null)return;
			for (E e : c)
	            add(e);
			
		}
	    /**
	     * remove o from the tree
	     */
		@Override
		public boolean remove(Object o) {
			if(o==null){
				throw new IllegalArgumentException();
			}
			if(mRoot==null){
				return false;
			}
			BSTNode<E> parent=null;
			BSTNode<E> temproot=mRoot;
			@SuppressWarnings("unchecked")
			BSTNode<E> bo=(BSTNode<E>)o;
			@SuppressWarnings("unchecked")
			Comparable<? super E> k = (Comparable<? super E>)bo.key;
			if(k==null){
				if(!(bo.key instanceof Number)){
					return false;
				}	
			}
			
			while(temproot!=null){
				if(temproot==bo){//find
					break;
				}
				int cmp;
				if(k!=null){
					cmp=k.compareTo(temproot.key);
				}
				else{
					cmp=ncmp.compare((Number)k, (Number)temproot.key);
				}
				if(cmp>=0){//into right child
				   parent=temproot;
				   temproot=temproot.right;
					 
				}
				else{//into left child
					parent=temproot;
				   temproot=temproot.left;
					
				}
			}
			if(temproot==null){
				return false;
			}
			//delete the node and make the binary tree whole
			if(temproot.right==null&&temproot.left==null){
				if(mRoot==temproot){
					mRoot=null;
				}
				else{
					if(parent.right==temproot){
						parent.right=null;
					}
					else{
						parent.left=null;
					}
				}
			}
			else if(temproot.left!=null){
				BSTNode<E> leftChild=temproot.left;
				BSTNode<E> tempParent=null;
				while(leftChild.right!=null){
					tempParent=leftChild;
					leftChild=leftChild.right;
				}
				if(tempParent==null){
					leftChild.right=temproot.right;
					if(parent==null){
						mRoot=leftChild;
					}
					else{
						if(parent.right==temproot){
							parent.right=leftChild;
						}
						else{
							parent.left=leftChild;
						}
					}
					
				}
				else{
					tempParent.right=null;
					leftChild.right=temproot.right;
					leftChild.left=temproot.left;
					if(parent==null){
						mRoot=leftChild;
					}
					else{
						if(parent.right==temproot){
							parent.right=leftChild;
						}
						else{
							parent.left=leftChild;
						}
					}
					
				}
			}
			else{
				BSTNode<E> rightChild=temproot.right;
				BSTNode<E> tempParent=null;
				while(rightChild.left!=null){
					tempParent=rightChild;
					rightChild=rightChild.left;
				}
				if(tempParent==null){
					rightChild.left=temproot.left;
					if(parent==null){
						mRoot=rightChild;
					}
					else{
						if(parent.right==temproot){
							parent.right=rightChild;
						}
						else{
							parent.left=rightChild;
						}
					}
					
				}
				else{
					tempParent.left=null;
					rightChild.right=temproot.right;
					rightChild.left=temproot.left;
					if(parent==null){
						mRoot=rightChild;
					}
					else{
						if(parent.right==temproot){
							parent.right=rightChild;
						}
						else{
							parent.left=rightChild;
						}
					}
					
					
				}
				
			}
			size-=1;
			return true;
		}
		/**
		 * 
		 * Inorder iterator
		 *
		 */
		private class InorderIterator implements Iterator<E>  
	    {  
	        private Stack<BSTNode<E>> nodeStack;  
	        private BSTNode<E> currentNode;  
	        public InorderIterator()  
	        {  
	            nodeStack=new Stack<BSTNode<E>>();  
	            currentNode=mRoot;  
	        }  
	        @Override  
	        public boolean hasNext() {  
	            // TODO Auto-generated method stub  
	            return (currentNode!=null||!nodeStack.empty());  
	        }  
	  
	        @Override  
	        public E next() {  
	            // TODO Auto-generated method stub  
	        	BSTNode<E> nextNode=null;  
	            while(currentNode!=null)  
	            {  
	                nodeStack.push(currentNode);  
	                currentNode=currentNode.left;  
	            }  
	            if(!nodeStack.isEmpty())  
	            {  
	                nextNode=nodeStack.pop();  
	                currentNode=nextNode.right;  
	            }  
	            return nextNode.key;  
	        }  
	  
	        @Override  
	        public void remove() {  
	            // TODO Auto-generated method stub  
	            throw new UnsupportedOperationException();  
	        }  
	          
	    }  
		/**
		 * return the inorder iterator
		 */
		@Override
		public Iterator<E> iterator() {
			
			return new InorderIterator();
		}
		
		/**
		 * get the height of the tree
		 * @param node the current root node
		 * @return
		 */
	    private int getHeight(BSTNode<E> node){
	    	  if(node==null)return 0;
	    	  int leftHeight=getHeight(node.left);
	    	  int rightHeight=getHeight(node.right);
	    	  return Math.max(leftHeight, rightHeight)+1;
	    }
	    /**
	     * get the height of the tree
	     */
		@Override
		public int height() {
			
			return getHeight(mRoot);
		}
		/**
	     * get the size of all elements
	     */
		@Override
		public int size() {
			// TODO Auto-generated method stub
			return size;
		}
	    
		

	}

}
