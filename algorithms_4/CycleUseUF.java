package algorithms_4;

import algorithms_1to2.UF_Set;

public class CycleUseUF {
	private UF_Set uf;
	private Graph g;
	private boolean has;
	public CycleUseUF(Graph g) {
		this.g = g;
		uf = new UF_Set(g.getV());
		for(int i = 0;i<g.getV();i++) {
			for(int k:g.adj(i)) {
				if(k > i) {
					if(uf.connected(k, i)) {
						has = true;
					} else {
						uf.union(k, i);
					}
				}
			}
		}
		System.out.println(has);
	}
	
	public static void main(String[] args) {
		Graph g = new Graph(3);
		g.addEdge(0, 1);
		
		CycleUseUF s = new CycleUseUF(g);
	}
}
