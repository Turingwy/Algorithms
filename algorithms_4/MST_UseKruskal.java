package algorithms_4;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import algorithms_1to2.UF_Set;
import algorithms_4.EWGraph.WeightEdge;

public class MST_UseKruskal {
	private UF_Set ufs;
	private EWGraph ewg;
	private Queue<WeightEdge> q;
	private PriorityQueue<WeightEdge> pq;
	
	public MST_UseKruskal(EWGraph ewg) {
		ufs = new UF_Set(ewg.getv());
		q = new LinkedList<WeightEdge>();
		pq = new PriorityQueue<WeightEdge>();
		this.ewg = ewg;
		addAllEdge();
		kruskal();
		
	}
	
	private void addAllEdge() {
		for(int i = 0;i<ewg.getv();i++) {
			for(WeightEdge v:ewg.adj(i)) {
				pq.add(v);
			}
		}
	}
	
	private void kruskal() {
		while(!pq.isEmpty() && q.size() < ewg.getv() -1) {
			WeightEdge we = pq.poll();
			if(!ufs.connected(we.s, we.v)) {
				ufs.union(we.s, we.v);
				q.add(we);			
			}
		}
	}
	
	public static void main(String[] args) {
		EWGraph ewg = new EWGraph(4);
		ewg.addEdge(0, 1, 5);
		ewg.addEdge(1, 2, 3);
		ewg.addEdge(1, 3, 2);
		ewg.addEdge(2, 3, 6);
		MST_UseKruskal mst = new MST_UseKruskal(ewg);
		for(WeightEdge we : mst.q) {
			System.out.println(we.s + " " + we.v);
		}
	}
}
