package algorithms_4;

import algorithms_1to2.UF_Set;

public class SearchUseUnionFind {
	private Graph g;
	private int v;
	private UF_Set uf;
	
	public SearchUseUnionFind(Graph g, int v) {
		this.g = g;
		this.v = v;
		uf = new UF_Set(g.getV());
		create();
	}
	
	public void create() {
		for(int i = 0;i<g.getV();i++) {
			for(int w:g.adj(i))
				uf.union(v, w);
		}
	}
	
	public boolean marked(int s) {
		return uf.connected(s,v);
	}
	
	public int count() {
		return uf.connectCount(v)-1;
	}
	
	public static void main(String[] args) {
		Graph g = new Graph(3);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(0, 2);
		SearchUseUnionFind s = new SearchUseUnionFind(g,0);
		System.out.println(s.count());
	}
}
