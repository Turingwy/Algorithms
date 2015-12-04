package algorithms_4;

import java.util.LinkedList;
import java.util.Stack;

public class DegreeOfSeparation {
	private SymbolGraph sg;
	private Graph g;
	private int startNode;
	private boolean[] marked;
	private int paths[];
	public DegreeOfSeparation(SymbolGraph sg, String startNode) {
		this.sg = sg;
		g = sg.G();
		marked = new boolean[sg.G().getV()];
		paths =  new int[sg.G().getV()];
		this.startNode = sg.index(startNode);
		bfs();
		
	}
	
	private void bfs() {
		
		LinkedList<Integer> q = new LinkedList<Integer>();
		q.add(startNode);
		marked[startNode] = true;
		paths[startNode] = startNode;
		while(!q.isEmpty()) {
			int s = q.removeLast();
			
			
			for(int v : g.adj(s)) {
				if(!marked[v]) {
					marked[v] = true;
					paths[v] = s;
					q.add(v);
				}
				
			}
		}
	}
	
	public boolean connected(String v) {
		int index = sg.index(v);
		return marked[index];
	}
	
	public Iterable<String> path(String v) {
		Stack<String> s = new Stack<String>();
		if(!connected(v))	return null;
		int index = sg.index(v);
		while(index != startNode) {
			s.push(sg.name(index));
			index = paths[index];
		}
		s.push(sg.name(startNode));
		return s;
		
	} 
	
	public static void main(String[] args) {
		SymbolGraph sg = new SymbolGraph(4);
		
		sg.puts("chouchou", "yanxu");
		sg.puts("wdx", "chouchou");
		sg.puts("zhuangzhuang", "wdx");
		DegreeOfSeparation dos = new DegreeOfSeparation(sg, "yanxu");
		for(String w : dos.path("wdx")) {
			System.out.print(w);
		}
		
	}
}
