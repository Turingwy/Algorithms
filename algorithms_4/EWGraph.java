package algorithms_4;

import java.util.LinkedList;

public class EWGraph {
	public static class WeightEdge implements Comparable<WeightEdge> {
		public int s;
		public int v;
		public double weight;
		
		public int either() {
			return s;
		}
		
		public int other() {
			return v;
		}
		
		public int other(int v) {
			if(v == s)
				return this.v;
			else if(v == this.v)
				return s;
			return -1;
		}
		public WeightEdge(int s, int v, int weight) {
			this.s = s;
			this.v = v;
			this.weight = weight;
		}
		@Override
		public int compareTo(WeightEdge other) {
			double cmp = weight - other.weight;
			if(cmp > 0) return 1;
			else if(cmp == 0) return 0;
			else return -1;
		}
	}
	
	private final int v;
	private int e;
	private LinkedList<WeightEdge>[] l;
	public EWGraph(int vnum) {
		this.v = vnum;
		l = (LinkedList<WeightEdge>[])new LinkedList[v];
		for(int i = 0;i<v;i++) {
			l[i] = new LinkedList<WeightEdge>();
		}
	}
	
	public void addEdge(int v, int w, int weight) {
		WeightEdge edge = new WeightEdge(v, w, weight);
		l[v].add(edge);
		e++;
		l[w].add(edge);
	}
	
	public Iterable<WeightEdge> adj(int v) {
		return l[v];
	}
	
	public int getv() {
		return v;
	}
	
	public int gete() {
		return e;
	}
}
