package alg_4_Digraph;

import java.util.Stack;

import alg_4_Digraph.EWDigraph.DirectedEdge;
import algorithms_1to2.IndexMinPriorityQueue;

public class topoDijkstra {
	private EWDigraph ewg;
	private DirectedEdge[] edgeTo;
	private double[] weight;
	private boolean[] marked;
	private Stack<Integer> topoLogical;
	
	public topoDijkstra(EWDigraph ewg, int s) {
		edgeTo = new DirectedEdge[ewg.getv()];
		weight = new double[ewg.getv()];
		marked = new boolean[ewg.getv()];
		topoLogical = new Stack<>();
		this.ewg = ewg;
		
		for(int i = 0;i<ewg.getv();i++) {
			weight[i] = Double.POSITIVE_INFINITY;
		}
		weight[s] = 0;
		
		for(int i = 0;i<ewg.getv();i++) {
			if(!marked[i])
				dfs(i);
		}
		
		dijkstra(s);
		
		
	}
	
	private void dfs(int v) {
		marked[v] = true;
		
		for(DirectedEdge w:ewg.adj(v)) {
			if(!marked[w.to()])
				dfs(w.to());			
		}
		topoLogical.push(v);
	}
	
	private void dijkstra(int s) {
		visit(s);
		while(!topoLogical.isEmpty()) {
			int v = topoLogical.pop();
			visit(v);
		}
	}
	
	private void visit(int v) {
		for(DirectedEdge de : ewg.adj(v)) {
			if(weight[de.to()] > weight[v] + de.weight) {
				weight[de.to()] = weight[v] + de.weight;
				edgeTo[de.to()] = de;
			}
		}
	}
	

	public boolean hasPathTo(int v) {
		return edgeTo[v]!=null;
	}
	
	public Iterable<DirectedEdge> pathTo(int v) {
		Stack<DirectedEdge> stack = new Stack<>();
		DirectedEdge path = edgeTo[v];
		while(path != null) {
			stack.push(path);
			path = edgeTo[path.from()];
		}
		return stack;
	}
	
	public static void main(String[] args) {
		EWDigraph ewg = new EWDigraph(4);
		ewg.addEdge(0, 1, 5);
		ewg.addEdge(1, 2, 1);
		ewg.addEdge(1, 3, 5);
		ewg.addEdge(2, 3, 1);
	
		topoDijkstra pa = new topoDijkstra(ewg,0);

		for(DirectedEdge w:pa.pathTo(3))
			System.out.println(w.from() + "-" + w.to());
	}
	
	
}
