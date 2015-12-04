package alg_4_Digraph;

import java.util.Stack;

public class HamPath {
	private Stack<Integer> s;
	private Digraph dg;
	private boolean[] marked;
	private boolean has = true;
	public HamPath(Digraph dg) {
		this.dg = dg;
		s = new Stack<Integer>();
		marked = new boolean[dg.getV()];
		for(int i = 0;i<dg.getV();i++)
			if(!marked[i])
				topoLogical(i);
		
		while(!s.isEmpty()) {
			int i = s.pop();
			int j;
			if(!s.isEmpty())
				j = s.pop();
			else 
				break;
			if(!dg.hasEdge(i, j)) {
				has = false;
				break;
			}
		}
		System.out.println(has);
	}
	
	public void topoLogical(int w) {
		marked[w] = true;
		
		for(int i:dg.adj(w)) {
			if(!marked[i])
				topoLogical(i);
		}
		
		s.push(w);
	}
	
	public static void main(String[] args) {
		Digraph dg = new Digraph(5);
		dg.addEdge(0, 1);
		dg.addEdge(1, 2);
		dg.addEdge(2, 3);
		dg.addEdge(3, 4);
		
	
		HamPath scc = new HamPath(dg);
		
	}
	
}
