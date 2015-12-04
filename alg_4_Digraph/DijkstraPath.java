//package alg_4_Digraph;
//
//import java.util.PriorityQueue;
//import java.util.Stack;
//
//import alg_4_Digraph.EWDigraph.DirectedEdge;
//import algorithms_1to2.IndexMinPriorityQueue;
//import algorithms_4.EWGraph;
//
//public class DijkstraPath {
//	private EWDigraph dg;
//	private DirectedEdge[] edgeTo;
//	private double[] weight;
//	private IndexMinPriorityQueue<DirectedEdge> pq;
//	
//	public DijkstraPath(EWDigraph dg, int s) {
//		this.dg = dg;
//		edgeTo = new DirectedEdge[dg.getv()];
//		weight = new double[dg.getv()];
//		pq = new IndexMinPriorityQueue<DirectedEdge>();
//		for(int i = 0;i<dg.getv();i++) {
//			weight[i] = Double.POSITIVE_INFINITY;
//		}
//		weight[s] = 0;
//		dijkstra(s);
//	}
//	
//	public void dijkstra(int s) {
//		visit(s);
//		while(!pq.isEmpty()) {
//			DirectedEdge de = pq.min();
//			int v = pq.deleteMin();
//			edgeTo[v] = de;
//			visit(v);
//		}
//	}
//	
//	public void visit(int v) {
//		for(DirectedEdge de:dg.adj(v)) {
//			if(edgeTo[de.to()] != null)
//				continue;
//			if(weight[de.to()] > weight[v] + de.weight) {
//				weight[de.to()] = weight[v] + de.weight;
//				pq.change(de.to(), de);
//			}
//			
//		}
//	}
//	
//	public boolean hasPathTo(int v) {
//		return edgeTo[v]!=null;
//	}
//	
//	public Iterable<DirectedEdge> pathTo(int v) {
//		Stack<DirectedEdge> stack = new Stack<>();
//		DirectedEdge path = edgeTo[v];
//		while(path != null) {
//			stack.push(path);
//			path = edgeTo[path.from()];
//		}
//		return stack;
//	}
//	
//	public static void main(String[] args) {
//		EWDigraph ewg = new EWDigraph(4);
//		ewg.addEdge(0, 1, 5);
//		ewg.addEdge(1, 2, 1);
//		ewg.addEdge(1, 3, 5);
//		ewg.addEdge(2, 3, 1);
//	
//		DijkstraPath pa = new DijkstraPath(ewg,0);
//
//		for(DirectedEdge w:pa.pathTo(3))
//			System.out.println(w.from() + "-" + w.to());
//	}
//	
//}



package alg_4_Digraph;

import java.util.PriorityQueue;

import alg_4_Digraph.EWDigraph.DirectedEdge;

public class DijkstraPath {
	private EWDigraph ewg;
	private int start;
	private PriorityQueue<DirectedEdge> q;
	private boolean[] marked;
	private DirectedEdge[] path;
	private double[] weight;
	private int step;
	private int i;
	public DijkstraPath(EWDigraph ewg, int start) {
		this.ewg = ewg;
		this.start = start;
		marked = new boolean[ewg.getv()];
		path = new DirectedEdge[ewg.getv()];
		weight = new double[ewg.getv()];
		q = new PriorityQueue<>();
		for(int i = 0;i<ewg.getv();i++)
			weight[i] = Double.POSITIVE_INFINITY;
		weight[start] = 0;
		solve();
	}
	
	public void solve() {
		relax(start);
		step++;
		while(!q.isEmpty()) {
			
			DirectedEdge de = q.poll();
			
			int to = de.to();
			if(marked[to])
				continue;
			path[to] = de;
			relax(to);
			
		}
	}
	
	public void relax(int v) {
		marked[v] = true;
		
		for(DirectedEdge de : ewg.adj(v)) {
			int to = de.to();
			if(!marked[to]) {
				if(weight[to] > weight[v] + de.weight) {
					weight[to] = weight[v] + de.weight;
					System.out.println("weight-" + to + ":" + weight[to]);
				}
				q.add(de);
			}
			
		}
	}
	
	public static void main(String[] args) {
		EWDigraph ewg = new EWDigraph(4);
		ewg.addEdge(0, 1, 5);
		ewg.addEdge(1, 2, 1);
		ewg.addEdge(1, 3, 5);
		ewg.addEdge(2, 3, 1);
	
		DijkstraPath pa = new DijkstraPath(ewg,0);

		for(double i : pa.weight)
			System.out.println(i);
	}
}