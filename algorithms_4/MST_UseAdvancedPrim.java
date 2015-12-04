package algorithms_4;

import java.util.LinkedList;
import java.util.Queue;

import algorithms_1to2.IndexMinPriorityQueue;
import algorithms_4.EWGraph.WeightEdge;

public class MST_UseAdvancedPrim {
	private EWGraph g;
	private IndexMinPriorityQueue<Double> pq;
	private Queue<Integer> q;
	private boolean[] marked;
	public MST_UseAdvancedPrim(EWGraph g) {
		this.g = g;
		pq = new IndexMinPriorityQueue<Double>();
		q = new LinkedList<Integer>();
		marked = new boolean[g.getv()];
		for(int i = 0;i<g.getv();i++) {
			if(!marked[i])
				prim(i);
		}
	}

	public void visit(int v) {
		marked[v] = true;
		for(WeightEdge we : g.adj(v)) {
			if(pq.contains(we.other(v)) ) {
				if(pq.getItem(we.other(v)) > we.weight)
					pq.change(we.other(v), we.weight);
			} else if(!marked[we.other(v)]) {
				pq.puts(we.other(v), we.weight);
			}
		}
	}
	
	public void prim(int v) {
		visit(v);
		q.add(v);
		
		while(!pq.isEmpty()) {
			int k = pq.deleteMin();
			q.add(k);
			visit(k);
		}
	}
	
	public Iterable<Integer> mst() {
		return q;
	}
	
	public static void main(String[] args) {
		EWGraph ewg = new EWGraph(4);
		ewg.addEdge(0, 1, 5);
		ewg.addEdge(1, 2, 3);
		ewg.addEdge(1, 3, 2);
		ewg.addEdge(2, 3, 6);
		for(int i : new MST_UseAdvancedPrim(ewg).mst()) {
			System.out.println(i);
		}
		
	}
	
}
