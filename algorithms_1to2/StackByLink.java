package algorithms_1to2;
import java.util.Iterator;

public class StackByLink<Item> implements Iterable<Item> {
	private class Node {
		Item i;
		Node next;	
		public Node(Item i) {
			this.i = i;
			next = null;
		}
	}
	
	private Node top;
	public StackByLink() {
		top = null;
	}
	
	public void push(Item i) {
		Node n = new Node(i);
		if(top == null) top = n;
		else {n.next = top;top = n;}
	}
	
	public Item pop() {
		Item a  = top.i;
		top = top.next;
		return a;
	}
	
	public boolean isEmpty() {
		return top==null;
	}
	@Override
	public Iterator<Item> iterator() {
		return new StackIterator();
	}
	
	
	private class StackIterator implements Iterator<Item> {

		@Override
		public boolean hasNext() {
			return top!=null;
		}

		@Override
		public Item next() {
			return pop();
		}
		
	}
	
	public static void main(String [] args) {
		StackByLink<String> a = new StackByLink<String>();
		a.push("123");
		a.push("456");
		a.push("789");
		for(Object i:a) {
			System.out.println(i);
		}
		
	}
}
