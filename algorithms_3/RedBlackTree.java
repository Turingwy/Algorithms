package algorithms_3;

public class RedBlackTree<Key extends Comparable<Key>, Value> {
	public static final boolean red = true;
	public static final boolean black = false;

	private class Node {
		Node left;
		Node right;
		Key k;
		Value v;
		boolean color;
		int size;

		public Node(Key k, Value v) {
			this.k = k;
			this.v = v;
			color = red;
			size = 1;
		}
	}

	private Node root;

	public RedBlackTree() {
	}

	private int getSize(Node n) {
		if (n == null)
			return 0;
		else
			return n.size;
	}

	private void colorFlip(Node r) {
		r.color = red;
		r.left.color = black;
		r.right.color = black;
	}

	private Node left(Node r) {
		Node tmp = r.right;
		r.right = tmp.left;
		tmp.left = r;
		tmp.color = r.color;
		r.color = red;
		tmp.size = r.size;
		r.size = getSize(r.left) + getSize(r.right) + 1;

		return tmp;
	}

	public boolean contains(Key k) {
		return gets(k) != null;
	}
	private Node right(Node r) {
		Node tmp = r.left;
		r.left = tmp.right;
		tmp.right = r;
		tmp.color = r.color;
		r.color = red;
		tmp.size = r.size;
		r.size = getSize(r.left) + getSize(r.right) + 1;

		return tmp;
	}

	public void puts(Key k, Value v) {
		root = puts(root, k, v);
		root.color = black;
	}

	private boolean isRed(Node r) {
		if (r == null)
			return false;
		return r.color == red;
	}

	private Node puts(Node r, Key k, Value v) {
		if (r == null)
			return new Node(k, v);

		int cmp = r.k.compareTo(k);
		if (cmp > 0)
			r.left = puts(r.left, k, v);
		else if (cmp < 0)
			r.right = puts(r.right, k, v);
		else {
			r.v = v;
		}
		if (!isRed(r.left) && isRed(r.right))
			r = left(r);
		if (isRed(r.left) && isRed(r.left.left))
			r = right(r);
		if (isRed(r.left) && isRed(r.right))
			colorFlip(r);

		r.size = getSize(r.left) + getSize(r.right) + 1;
		return r;
	}
	
	public Value gets(Key k) {
		Node nowRoot = root;
		while (nowRoot != null) {
			int cmp = nowRoot.k.compareTo(k);
			if (cmp > 0) {
				nowRoot = nowRoot.left;
			} else if (cmp < 0) {
				nowRoot = nowRoot.right;
			} else
				return nowRoot.v;
		}

		return null;
	}

	public int rank(Key k) {
		Node nowRoot = root;
		int rank = 0;
		while (nowRoot != null) {
			int cmp = nowRoot.k.compareTo(k);
			if (cmp > 0) {
				nowRoot = nowRoot.left;
			} else if (cmp < 0) {
				rank += nowRoot.size - getSize(nowRoot.right);
				nowRoot = nowRoot.right;
			} else {
				rank += nowRoot.size - getSize(nowRoot.right);
				return rank;
			}
		}
		return -1;
	}

	public Key rankTo(int rank) {
		Node nowRoot = root;
		while (nowRoot != null) {
			int nowRank = nowRoot.size - getSize(nowRoot.right);
			int cmp = - rank + nowRank;
			if (cmp > 0) {
				nowRoot = nowRoot.left;
			} else if (cmp < 0) {
				rank -= nowRank;
				nowRoot = nowRoot.right;
			} else
				return nowRoot.k;
		}

		return null;
	}

	private Node min(Node r) {
		if(r.left == null)
			return r;
		
		return min(r.left);
	}

	public void midRootTrival(Node r) {
		if (r == null)
			return;

		midRootTrival(r.left);
		System.out.print(r.k + " ");
		midRootTrival(r.right);
	}

	public void firstRootTrival(Node r) {
		if (r == null)
			return;

		System.out.print(r.k + " " + r.color + " ");
		firstRootTrival(r.left);
		firstRootTrival(r.right);
	}

	public void delMin() {
		root = delMin(root);
	}
	
	private Node delMin(Node r) {
		if(r.left == null)
			return null;
		
		if(!isRed(r.left) && !isRed(r.left.left)) {
			r = delMinchange(r);
		}
		r.left = delMin(r.left);
		r.size = getSize(r.left) + getSize(r.right) +1;
		return balance(r);
	}
	
	private Node dleft(Node r) {
		Node tmp = r.right;
		r.right = tmp.left;
		tmp.left = r;
		tmp.color = r.color;
		r.color = black;
		tmp.size = r.size;
		r.size = getSize(r.left) + getSize(r.right) + 1;

		return tmp;
	}
	
	private Node dright(Node r) {
		Node tmp = r.left;
		r.left = tmp.right;
		tmp.right = r;
		tmp.color = r.color;
		r.color = black;
		tmp.size = r.size;
		r.size = getSize(r.left) + getSize(r.right) + 1;

		return tmp;
	}
	
	private Node delMinchange(Node r) {
		r.color = black;
		r.left.color = red;
		r.right.color = red;
		if(isRed(r.right.left)) {
			r.right = right(r.right);
			r = left(r);
		}
		return r;
	}
	
	private Node balance(Node r) {
		if(!isRed(r.left) && isRed(r.right))
			r = left(r);
		if(isRed(r.left) && isRed(r.left.left))
			r = right(r);
		if(isRed(r.left) && isRed(r.right))
			colorFlip(r);
		return r;
	}
	
	public void delMax() {
		root = delMax(root);
	}
	
	private Node delMax(Node r) {
		if(isRed(r.left))
			r = right(r);
		if(r.right == null)
			return null;
		
		if(!isRed(r.right) && !isRed(r.right.right)) {
			r = delMaxchange(r);
		}
		r.right = delMax(r.right);
		r.size = getSize(r.left) + getSize(r.right) + 1;
		return balance(r);
		
	}
	
	private Node delMaxchange(Node r) {
		r.color = black;
		r.left.color = red;
		r.right.color = red;
		if(isRed(r.left.left)) {
			r = dright(r);
		}
		return r;
	}
	
	private Node delete(Node r, Key k) {
		if(r == null)
			return null;
		
		int cmp = r.k.compareTo(k);
		if(cmp > 0) {
			if(!isRed(r.left) && r.left.left != null && !isRed(r.left.left)) {
				r = delMinchange(r);
			}
			r.left = delete(r.left,k);
		} else if(cmp < 0) {
			if(isRed(r.left))
				r = right(r);
			if(!isRed(r.right) && !isRed(r.right.right)) {
				r = delMaxchange(r);
			}
			r.right = delete(r.right, k);
		} else {
			if(r.left == null) {
				return r.right;
			}
			if(r.right == null) {
				return r.left;
			}
			
			Node x = min(r.right);
			r.k = x.k;
			r.v = x.v;
			
			r.right = delMin(r.right);
			
			
		}
		r.size = getSize(r.left) + getSize(r.right) + 1;
		return balance(r);
	} 
	
	public static void main(String[] args) {
		RedBlackTree<Integer, Integer> r = new RedBlackTree<>();
		for (int i = 1; i < 10; i++) {
			r.puts(i, i);
		}
		
		r.root = r.delMin(r.root);
		r.root = r.delMin(r.root);
		r.root = r.delete(r.root, 8);
		r.root = r.delMin(r.root);
		System.out.println(r.root.size);
		r.midRootTrival(r.root);
		r.firstRootTrival(r.root);
	}
}
