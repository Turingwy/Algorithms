package algorithms_4;

import java.util.LinkedList;
import java.util.Queue;

import algorithms_4.EWGraph.WeightEdge;

//delete maximum weight edge in cycle --loop
public class MST_UseMaxCycleWeight {
	private WeightEdge max;
	private boolean hasCycle;
	private Queue<WeightEdge> q;
	private LinkedList<WeightEdge>[] we;
	private boolean[] marked;
	public MST_UseMaxCycleWeight(EWGraph g) {
		we = (LinkedList<WeightEdge>[])new LinkedList[g.getv()]; 
		for(int i = 0;i<g.getv();i++) {
			we[i] = new LinkedList<WeightEdge>();
			for(WeightEdge e : g.adj(i))
				we[i].add(e);
		}
		q = new LinkedList<>();
		marked = new boolean[g.getv()];
		start();
	}
	
	public void dfs(int v) {
		marked[v] = true;
		for(WeightEdge e:we[v]) {
			if(hasCycle) 
				return;
			if(!marked[e.other(v)]) {
				if(max!=null) {
					if(max.weight < e.weight)
					max = e;
				} else {
					max = e;
				}
				dfs(e.other(v));
			} else {
				hasCycle = true;
			}
		}
	}
	
	public void start() {
		for(;;) {
			dfs(0);
			if(hasCycle) {
				we[max.s].remove(max);
				we[max.v].remove(max);
			} else {
				for(int i = 0;i<we.length;i++) {
					for(WeightEdge we:we[i]) {
						if(we.other(i) > i) {
							q.add(we);
						}
					}
				}
				break;
			}
			hasCycle = false;
			for(int i = 0;i<marked.length;i++)
				marked[i] = false;
		}
	}
	
	public static void main(String[] args) {
		EWGraph ewg = new EWGraph(4);
		ewg.addEdge(0, 1, 5);
		ewg.addEdge(1, 2, 3);
		ewg.addEdge(1, 3, 2);
		ewg.addEdge(2, 3, 6);
		for(WeightEdge i : new MST_UseMaxCycleWeight(ewg).q) {
			System.out.println(i.s + " " + i.v);
		}
		
	}
	
}
