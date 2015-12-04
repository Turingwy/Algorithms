package algorithms_4;

import java.util.Stack;

public class Paths {
	private Graph g;
	private boolean[] marked;
	private int[] edgeToStart;
	private int startNode;
	public Paths(Graph g, int sn) {
		startNode = sn;
		this.g = g;
		int vCount = g.getV();
		marked = new boolean[vCount];
		edgeToStart = new int[vCount];
		dfs(startNode);
		edgeToStart[startNode] = startNode;
	}
	
	private void dfs(int v) {
		marked[v] = true;
		
		for(int w : g.adj(v)) {
			if(!marked[w]) {
				edgeToStart[w] = v;
				dfs(w);
			}
		}
	}
	
	public boolean hasPathTo(int v) {
		return marked[v];
	}
	
	public Iterable<Integer> pathToStart(int v) {
		if(!hasPathTo(v))
			return null;
		Stack<Integer> s = new Stack<Integer>(); 
		while(v != startNode) {
			s.push(v);
			v = edgeToStart[v];
		}
		s.push(startNode);
		return s;
		
	}
	
	public static void main(String [] args) {
		Graph g = new Graph(3);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(0,1);
		Paths p = new Paths(g,1);
		for(int i : p.pathToStart(0))
		System.out.print(i + "-");
	}
}
