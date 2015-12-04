package alg_4_Digraph;

import java.util.LinkedList;
import java.util.Queue;

import alg_4_Digraph.EWDigraph.DirectedEdge;

public class BellmanFord {
	private EWDigraph ewg;
	private double[] weight;
	private DirectedEdge[] edgeTo;
	private Queue<Integer> q;
	private boolean[] inQueue;
	
	private class NegativeCycleFinder {
		private DirectedEdge[] onStack;
		private boolean[] marked;
		private boolean has;
		public NegativeCycleFinder() {
			onStack = new DirectedEdge[ewg.getv()];
			marked = new boolean[ewg.getv()];
			dfs(0);
		}
		
		public boolean hasNegativeCycle() {
			return has;
		}
		public void dfs(int v) {
			marked[v] = true;
			
			for(DirectedEdge de : ewg.adj(v)) {
				if(has)
					return;
				if(!marked[de.to()]) {
					onStack[de.to()] = de;
					dfs(de.to());
				} else if(onStack[de.to()] != null || de.to() == 0) {
					int sum = 0;
					int parent = v;
					while(onStack[parent].from() != de.to()) {
						sum += onStack[parent].weight;
						parent = onStack[parent].from();
					}
					sum+=onStack[parent].weight;
					sum+=de.weight;
					if(sum < 0) 
						has = true;
				}	
			}
			onStack[v] = null;
		}
	}

	public BellmanFord(EWDigraph ewg, int s) throws Exception {
		this.ewg = ewg;
		if(new NegativeCycleFinder().hasNegativeCycle())
			throw new Exception();
		weight = new double[ewg.getv()];
		edgeTo = new DirectedEdge[ewg.getv()];
		for(int i = 0;i<ewg.getv();i++) weight[i] = Double.POSITIVE_INFINITY;
		weight[s] = 0;
		q = new LinkedList<>();
		inQueue = new boolean[ewg.getv()];
		solve(s);
	}
	
	public void solve(int s) {
		relax(s);
		
		while(!q.isEmpty()) {
			int v = q.poll();
			inQueue[v] = false;
			relax(v);
		}
	}
	
	public void relax(int v) {
		for(DirectedEdge de : ewg.adj(v)) {
			int to = de.to();
			if(weight[to] > weight[v] + de.weight) {
				if(!inQueue[to]) {
					q.add(to);
					inQueue[to] = true;
				}
				weight[to] = weight[v] + de.weight;
			}
		}
	}
	
	
	
	public static void main(String[] args) throws Exception {
		EWDigraph ewg = new EWDigraph(4);
		ewg.addEdge(0, 1, -5);
		ewg.addEdge(1, 2, -1);
		ewg.addEdge(1, 3, -5);
		ewg.addEdge(2, 3, -3);
		BellmanFord pa = new BellmanFord(ewg,0);
		for(int i = 0;i<4;i++) {
			System.out.println(pa.weight[i]);
		}
		
	}
	
}
