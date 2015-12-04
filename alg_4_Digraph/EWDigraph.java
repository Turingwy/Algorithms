package alg_4_Digraph;

import java.util.LinkedList;

public class EWDigraph {
		public static class DirectedEdge implements Comparable<DirectedEdge> {
			int s;
			int v;
			double weight;
			
			public String toString() {
				return s + "-" + v;
			}
			public int from() {
				return s;
			}
			
			public int to() {
				return v;
			}
		
			public DirectedEdge(int s, int v, int weight) {
				this.s = s;
				this.v = v;
				this.weight = weight;
			}
			@Override
			public int compareTo(DirectedEdge other) {
				double cmp = weight - other.weight;
				if(cmp > 0) return 1;
				else if(cmp == 0) return 0;
				else return -1;
			}
		}
		
		private final int v;
		private int e;
		private LinkedList<DirectedEdge>[] l;
		public EWDigraph(int vnum) {
			this.v = vnum;
			l = (LinkedList<DirectedEdge>[])new LinkedList[v];
			for(int i = 0;i<v;i++) {
				l[i] = new LinkedList<DirectedEdge>();
			}
		}
		
		public void addEdge(int v, int w, int weight) {
			DirectedEdge edge = new DirectedEdge(v, w, weight);
			l[v].add(edge);
			e++;
		}
		
		public Iterable<DirectedEdge> adj(int v) {
			return l[v];
		}
		
		public int getv() {
			return v;
		}
		
		public int gete() {
			return e;
		}
}
