package alg_4_Digraph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class KosarajuSCC {
	private int id[];
	private Digraph dg;
	private boolean[] marked;
	private int count;
	public KosarajuSCC(Digraph dg) {
		id = new int[dg.getV()];
		marked = new boolean[dg.getV()];
		this.dg = dg;
		Digraph rdg = dg.reverse();
		DigraphOrder dgo = new DigraphOrder(rdg);
		Stack<Integer> s = dgo.reversePost();
		while(!s.isEmpty()) {
			
			int v = s.pop();
			
			if(!marked[v]) {
				dfs(v);
				count++;
			}
			
		}
	}
	
	private void dfs(int v) {
		marked[v] = true;
		id[v] = count;
		
		for(int w : dg.adj(v)) {
			if(!marked[w])
				dfs(w);
		}
	}
	
	public Iterable<Integer> SCC(int v) {
		Queue<Integer> q = new LinkedList<Integer>();
		
		for(int i = 0;i<dg.getV();i++)
			if(id[i] == id[v])
				q.add(i);
		
		return q;
	}
	
	public boolean stronglyConnected(int v, int w) {
		return id[v] == id[w];
	}
	
	public int count() {
		return count;
	}
	
	public static void main(String[] args) {
		Digraph dg = new Digraph(5);
		dg.addEdge(0, 1);
		dg.addEdge(1, 2);
		dg.addEdge(2, 0);
		dg.addEdge(3, 4);
		dg.addEdge(4,3);
		dg.addEdge(2, 3);
	
		KosarajuSCC scc = new KosarajuSCC(dg);
		for(int w:scc.SCC(3)) 
		System.out.println(w);
	}
}
