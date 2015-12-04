package DynamicPrograming;

import alg_4_Digraph.Digraph;

public class TreeDP {
	private Digraph dg;
	private int root;
	private int weight[];
	private int size[][];
	public TreeDP(Digraph dg, int root, int weight[]) {
		this.dg = dg;
		this.weight = weight;	
		size = new int[dg.getV()][2];
		for(int i = 0;i<dg.getV();i++) {
			if(dg.degree(i) == 0) {
				size[i][1] = weight[i];
				size[i][0] = 0;
			}
		}
		dfs(root);
	}
	//size[i][0] = max{size[j][0],size[j][1]}
	public void dfs(int v) {
		int max = 0;
		for(int w:dg.adj(v)) {
			dfs(w);
			size[v][1] += size[w][0];
			max = size[w][0] > size[w][1] ? size[w][0] : size[w][1];
			size[v][0] += max;
		}
		if(dg.degree(v)!=0)
			size[v][1] +=weight[v];
	}
	
	public static void main(String[] args) {
		Digraph dg = new Digraph(5);
		dg.addEdge(0, 1);
		dg.addEdge(1, 2);
		dg.addEdge(1, 3);
		dg.addEdge(0, 4);
		int weight[] = {1,2,3,4,5};
		TreeDP tdp = new TreeDP(dg,0,weight);
		System.out.println(tdp.size[0][0]);
		System.out.println(tdp.size[0][1]);
	}
}
