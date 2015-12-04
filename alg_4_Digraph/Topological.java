package alg_4_Digraph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Topological {
	private Digraph dg;
	private boolean hasCycle;
	private boolean[] marked;
	private boolean[] onStack;
	private int[] degrees;
	public Topological(Digraph dg) {
		this.dg = dg;
		marked = new boolean[dg.getV()];
		onStack = new boolean[dg.getV()];
		degrees = new int[dg.getV()];
		topoQueue();
	}
	
	public boolean isDAG() {
		for(int i = 0;i<dg.getV();i++) {
			if(!marked[i])
				dfs(i);
		}
		
		return hasCycle;
	}

	public void topoQueue() {
		
		Queue<Integer> queue = new LinkedList<Integer>();
		for(int i = 0;i<dg.getV();i++) {
			for(int j:dg.adj(i)) {
				degrees[j]++;
			}
		}
		for(int t = 0;t<dg.getV();t++) {
			int i;
			for(i = 0;i<dg.getV();i++) {
				if(degrees[i] == 0)
					break;
			}
			
			queue.add(i);
			System.out.println(i);
			degrees[i] = -1;
			for(int v:dg.adj(i)) {
				degrees[v]--;
			}
		}
		
		
	}
	
	private void dfs(int v) {
		marked[v] = onStack[v] = true;
		
		for(int w : dg.adj(v)) {
			if(hasCycle) return;
			if(!marked[w])
				dfs(w);
			else if(onStack[w])
				hasCycle = true;
		}
		onStack[v] = false;		
	}
	
	public Iterable<Integer> topo() {
		return new DigraphOrder(dg).reversePost();
	}
	
	public static void main(String[] args) {
		Digraph dg = new Digraph(5);
		dg.addEdge(0, 1);
		dg.addEdge(1, 2);
		dg.addEdge(2, 3);
		dg.addEdge(3, 4);
		
	
		Topological scc = new Topological(dg);
		
	}
}
