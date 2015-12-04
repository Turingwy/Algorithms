package alg_4_Digraph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DigraphOrder {
	private Digraph dg;
	private boolean[] marked;
	private Queue<Integer> pre;
	private Queue<Integer> post;
	private Stack<Integer> reversePost;
	
	public DigraphOrder(Digraph dg) {
		this.dg = dg;
		marked = new boolean[dg.getV()];
		pre = new LinkedList<>();
		post = new LinkedList<>();
		reversePost = new Stack<>();
		for(int i = 0; i < dg.getV(); i++) {
			if(!marked[i])
				dfs(i);
		}
	}
	
	private void dfs(int v) {
		marked[v] = true;
		pre.add(v);
		for(int w : dg.adj(v)) {
			if(!marked[w])
				dfs(w);
		}
		post.add(v);
		reversePost.push(v);
	}
	
	public Iterable<Integer> pre() {return pre;}
	
	public Iterable<Integer> post() {return post;}

	public Stack<Integer> reversePost() {return reversePost;}
	
	public static void main(String[] args) {
		Digraph dg = new Digraph(5);
		dg.addEdge(0, 1);
		dg.addEdge(1, 2);
		dg.addEdge(2, 0);
		dg.addEdge(3, 4);
		dg.addEdge(2, 3);
		dg.addEdge(4, 1);
		DigraphOrder dfo = new DigraphOrder(dg);
		for(int i : dfo.pre)
			System.out.print(i+" ");
		System.out.println("");
		for(int i : dfo.post)
			System.out.print(i+" ");
		System.out.println("");
		Stack<Integer> it = dfo.reversePost();
		while(!it.isEmpty())
			System.out.print(it.pop() + " ");

		System.out.println("");
		
		
	}
}
