package algorithms_4;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import algorithms_4.EWGraph.WeightEdge;

public class MST_UsePrim {
	private boolean[] marked;
	private EWGraph g;
	private PriorityQueue<WeightEdge> pq;
	private Queue<Integer> q;
	public MST_UsePrim(EWGraph g) {
		this.g = g;
		pq = new PriorityQueue<WeightEdge>();
		q = new LinkedList<Integer>();
		marked = new boolean[g.getv()];
		prim(0);
	}
	
	public void visit(int v) {
		marked[v] = true;
		
		for(WeightEdge we : g.adj(v)) {
			pq.add(we);
		}
	}
	
	public void prim(int v) {
		visit(v);
		q.add(v);
		WeightEdge we;
		while(!pq.isEmpty()) {
			we = pq.poll();
			if(!marked[we.other()]) {
				q.add(we.other());
				visit(we.other());
			} else if(!marked[we.either()]) {
				q.add(we.either());
				visit(we.either());
			}
		}
	}
	
	public Iterable<Integer> mst() {
		return q;
	}
	
	public static void main(String[] args) {
		EWGraph ewg = new EWGraph(4);
		ewg.addEdge(0, 1, 5);
		ewg.addEdge(1, 2, 3);
		ewg.addEdge(1, 3, 3);
		ewg.addEdge(2, 3, 5);
		for(int i : new MST_UsePrim(ewg).mst()) {
			System.out.println(i);
		}
		
	}
	
	
}
