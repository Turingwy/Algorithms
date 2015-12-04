package alg_4_Digraph;

import algorithms_4.Graph;
import algorithms_4.SearchUseUnionFind;

public class SearchUseDFS {
	private Digraph dg;
	private boolean[] marked;
	private int[] id;
	private int count;
	public SearchUseDFS(Digraph dg, int v) {
		this.dg = dg;
		marked = new boolean[dg.getV()];
		id = new int[dg.getV()];
		
		for(int i = 0;i<dg.getV();i++) {
			if(!marked[i]) {
				id[i] = count;
				dfs(i);
				count++;
			}
			
		}
	}
	
	private void dfs(int v) {
		marked[v] = true;
		
		for(int w : dg.adj(v)) {
			if(!marked[w]) {
				id[w] = count;
				dfs(w);
			}
		}
	}	
	
	public boolean connected(int v, int w) {
		return id[v] == id[w];
	}
	
	public int count() {
		return count;
	}
	
	public static void main(String[] args) {
		Digraph g = new Digraph(3);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(0, 2);
		SearchUseDFS s = new SearchUseDFS(g,0);
		System.out.println(s.count());
		for(int i = 0;i<3;i++)
			System.out.println(s.id[i]);
	}
}
