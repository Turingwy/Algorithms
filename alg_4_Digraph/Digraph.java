package alg_4_Digraph;

import java.util.LinkedList;

public class Digraph {
	private final int V;
	private int E;
	private LinkedList<Integer> [] l;
	
	public Digraph(int V) {
		this.V = V;
		l = (LinkedList<Integer>[])new LinkedList[V];
		for(int i = 0;i<V;i++) {
			l[i] = new LinkedList<Integer>();
		}
		E = 0;
	}
	
	public boolean hasEdge(int i, int j) {
		return l[i].contains(j);
	}
	
	public void addEdge(int v, int w) {
		l[v].add(w);
		E++;
	}
	
	public Iterable<Integer> adj(int v) {
		return l[v];
	}
	
	public Digraph reverse() {
		Digraph rev = new Digraph(V);
		for(int i = 0;i<V;i++) {
			for(int w:l[i]) {
				rev.addEdge(w,i);
			}
		}
		
		return rev;
		
	}
	
	public int getV() {
		return V;
	}
	
	public int getE() {
		return E;
	}
	
	public int degree(int v) {
		int degree = 0;
		for(Integer i : adj(v))
			degree++;
		return degree;
	}
	
}
