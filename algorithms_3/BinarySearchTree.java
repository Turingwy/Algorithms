package algorithms_3;

public class BinarySearchTree<K extends Comparable<K>,V> {
	private class Node {
		public K key;
		public V val;
		public Node left;
		public Node right;
		public int size = 1;
		public Node(K k,V v) {
			key = k;
			val = v;
		}
	}
	Node root;
	public BinarySearchTree() {}
	public void puts(K k,V v) {
		root = puts(root,k,v);
		
	}
	
	public Node puts(Node r, K k, V v) {
		if(r == null)
			return new Node(k,v);
		
		if(k.compareTo(r.key)<0) {
			r.left = puts(r.left,k,v);
		}
		else if(k.compareTo(r.key)>0) {
			r.right = puts(r.right, k,v);
		} 
		else {
			r.val = v;
		}
		r.size = getSize(r.left)+getSize(r.right)+1;
		return r;
	}
	
	private int getSize(Node n) {
		if(n == null)
			return 0;
		else
			return n.size;
	}
	
	public V gets(K k) {
		return gets(root, k);
	}	
	private V gets(Node r, K k) {
		if(r == null)
			return null;
		
		if(r.key.compareTo(k) < 0)
			return gets(r.right,k);
		else if(r.key.compareTo(k) >0)
			return gets(r.left,k);
		else
			return r.val;
		
	}
	
	public void delMin() {
		root = delMin(root);
	}
	private Node delMin(Node r) {
		if(r == null)
			return null;
		if(r.left == null)
			return r.right;
		
		r.left = delMin(r.left);
		r.size = getSize(r.left)+getSize(r.right)+1;
		return r;
	}
	
	public Node min() {
		return min(root);
	}
	private Node min(Node r) {
		if(r.left == null)
			return r;
		
		return min(r.left);
	}
	public Node delete(Node r, K k) {
		if(r== null)
			return null;
		else {
			if(r.key.compareTo(k) < 0)
				r.right = delete(r.right,k);
			else if(r.key.compareTo(k) >0)
				r.left = delete(r.left,k);
			else {
				if(r.left == null) {
					return r.right;
				}
				if(r.right == null) {
					return r.left;
				}
				else {
					Node x = min(r.right);
					x.left = r.left;
					x.right = delMin(r.right);
					x.size = getSize(x.left)+getSize(x.right)+1;
					return x;
				}
			}
		
			return r;
		}
	}
	
		
	private int rank(Node r, K k) {
		if(r == null)
			return 0;
		
		int cmp = k.compareTo(r.key);
		if(cmp < 0) {
			return rank(r.left, k);
		}
		else if(cmp == 0)
			return r.size - getSize(r.right);
		else {
			return getSize(r.left)+1+rank(r.right,k);
		}
	}
	
	private K rankTo(Node r, int rank) {
		if(r == null)
			return null;
		int myRank = r.size - getSize(r.right);
		if(myRank == rank)
			return r.key;
		else if(myRank < rank) {
			return rankTo(r.right,rank-myRank);
		}
		else {
			return rankTo(r.left,rank);
		}
	}
	
	public static void main(String [] args) {
		BinarySearchTree<Integer,String> r = new BinarySearchTree<>();
		r.puts(1, "adf");
		r.puts(2, "123");
		r.puts(0, "123123");
		r.puts(5,"123");
		for(int i = 1;i<=r.getSize(r.root);i++) {		//通过排名函数求有序数列；
			System.out.println(r.rankTo(r.root, i));
		}
	}
	
}

