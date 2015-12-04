package alg_4_Digraph;

import algorithms_4.Graph;

public class Euler {
	private Graph dg;
	private boolean hasEulerCycle;
	private boolean edge[][];
	
	public Euler(Graph dg) {
		this.dg = dg;
		edge = new boolean[dg.getV()][dg.getV()];
		for(int i = 0;i<dg.getV();i++) {
			for(int w:dg.adj(i)) {
				edge[i][w] = true;
				
			}
		}
		dfs(1);
	}
	
	private void dfs(int v) {
		
		for(int w:dg.adj(v)) {
			if(edge[v][w]) {
				
				edge[v][w] = false;
				edge[w][v] = false;
				dfs(w);
			}
			
		}
		System.out.println(v);
		
	}
	
	public static void main(String[] args) {
		Graph dg = new Graph(7);
		dg.addEdge(3, 0);
		dg.addEdge(0, 1);
		dg.addEdge(1, 2);
		dg.addEdge(2, 4);
		dg.addEdge(4, 6);
		dg.addEdge(4, 3);
		dg.addEdge(5, 6);
		dg.addEdge(5, 3);
		dg.addEdge(3, 1);
		
		dg.addEdge(1, 4);
		Euler dc = new Euler(dg);
		
		
	}
}
