package alg_4_Digraph;

import java.util.Stack;

public class DirectedCycle {
	private boolean[] marked;
	private boolean[] inStack;
	private int paths[];
	private Digraph dg;
	private int v;
	private boolean hasCycle;
	private Stack<Integer>[] s;
	private int pos;
	public DirectedCycle(Digraph dg, int v) {
		marked = new boolean[dg.getV()];
		inStack = new boolean[dg.getV()];
		paths = new int[dg.getV()];
		this.dg = dg;
		this.v = v;
		s = (Stack<Integer>[])new Stack[dg.getV()];
		dfs(v);
	}
	
	public void dfs(int v) {
		marked[v] = inStack[v] = true;
		
		for(int w:dg.adj(v)) {
			if(!marked[w]) {
				paths[w] = v;
				dfs(w); 
			} else if(inStack[w]) {
				hasCycle = true;
				
				s[pos] = new Stack<Integer>();
				for(int x = v;x!=w;x=paths[x]) {
					s[pos].push(x);
				}
				s[pos].push(w);
				s[pos++].push(v);
				hasCycle = true;
			}
		}
		inStack[v] = false;
		
	}
	
	public static void main(String[] args) {
		Digraph dg = new Digraph(5);
		dg.addEdge(0, 1);
		dg.addEdge(1, 2);
		dg.addEdge(2, 0);
		dg.addEdge(3, 4);
		dg.addEdge(2, 3);
		dg.addEdge(4, 1);
		DirectedCycle dc = new DirectedCycle(dg, 0);
		for(int i = 0;i<dc.pos;i++) {
			for(int w : dc.s[i])
				System.out.print(w + ":");
			System.out.println();
		}
		System.out.println(dc.pos);
	}
}
