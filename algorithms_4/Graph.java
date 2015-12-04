package algorithms_4;

import java.util.LinkedList;

public class Graph {
	private final int  V;
	private int E;
	private LinkedList<Integer> [] l; 
	public Graph(int v) {
		V = v;
		l = (LinkedList<Integer> [])new LinkedList[v];
		for(int i = 0;i<v;i++) {
			l[i] = new LinkedList<Integer>();
		}
		E = 0;
	}
	
	public int getV() {
		return V;
	}
	
	public int getE() {
		return E;
	}
	
	public void addEdge(int v, int w) {
		l[v].add(w);
		l[w].add(v);
		E++;
	}
	
	public Iterable<Integer> adj(int v) {
		return l[v];
	}
	
	public int degree(int v) {
		int degree = 0;
		for(Integer i : adj(v))
			degree++;
		return degree;
	}
	
}
