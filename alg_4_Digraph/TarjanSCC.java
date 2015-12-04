package alg_4_Digraph;

import java.util.Stack;

public class TarjanSCC {
	private Digraph dg;
	private int[] dfn;
	private int[] low;
	private Stack<Integer> stack;
	private int index;
	public TarjanSCC(Digraph dg) {
		this.dg = dg;
		dfn = new int[dg.getV()];
		low = new int[dg.getV()];
		stack = new Stack<>();
		for(int i = 0;i<dg.getV();i++) 
			dfn[i] = -1;
		
		for(int i = 0;i<dg.getV();i++) {
			if(dfn[i]==-1)
				dfs(i);
		}
	}
	
	public int min(int a, int b) {
		return a<b?a:b;
	}
	private void dfs(int v) {
		stack.push(v);
		dfn[v] = low[v] = index;
		index++;
		for(int w:dg.adj(v)) {
			if(dfn[w] == -1) {
				dfs(w);
				low[v] = min(low[v],low[w]);
			}
			else if(stack.contains(w)) {
				low[v] = min(low[v], low[w]);
			}
		}
		
		if(low[v] == dfn[v]) {
			
			while(stack.peek() != v) {
				System.out.print(stack.peek() + " ");
				stack.pop();
			} 
			System.out.println(v + " ");
			
		}
		
	}
	
	public static void main(String[] args) {
		Digraph dg = new Digraph(5);
		dg.addEdge(0, 1);
		dg.addEdge(1, 2);
		dg.addEdge(2, 0);
		dg.addEdge(3, 4);
		dg.addEdge(4,3);
		dg.addEdge(2, 3);
	
		TarjanSCC scc = new TarjanSCC(dg);
		
	}
}
