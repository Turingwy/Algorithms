package alg_4_Digraph;

import java.util.Scanner;

public class TarjinLCA {
	private UF_Set ufs;
	private Digraph dg;
	private boolean marked[];
	private boolean answered[];
	private Node[] x;
	private class Node {
		int x;
		int y;
		int answer;
	}
	
	public TarjinLCA(Digraph dg, int root, int num) {
		this.dg = dg;
		ufs = new UF_Set(dg.getV());
		marked = new boolean[dg.getV()];
		answered = new boolean[dg.getV()];
		x = new Node[num];
		Scanner in = new Scanner(System.in);
		for(int i = 0;i<num;i++) {
			x[i] = new Node();
			x[i].x = in.nextInt();
			x[i].y = in.nextInt();
		}
		LCA(root);
	}
	public void LCA(int root) {
		for(int w:dg.adj(root)) {
			LCA(w);
			ufs.union(w,root);
			
		}
		marked[root] = true;
		for(int i = 0;i<x.length;i++) {
			if(answered[i]) continue;
			if(x[i].x == root) {
				if(marked[x[i].y]) {
					x[i].answer = ufs.find(x[i].y);
					answered[i] = true;
				}
			}
			else if(x[i].y == root) {
				if(marked[x[i].x]) {
				x[i].answer = ufs.find(x[i].x);
				answered[i] = true;
				}
			}
		}
	}
	
	public int query(int a, int b) {
		for(int i = 0;i<x.length;i++) {
			
			if(x[i].x == a && x[i].y == b) {
				return x[i].answer;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		Digraph dg = new Digraph(5);
		dg.addEdge(0, 1);
		dg.addEdge(1, 2);
		dg.addEdge(0, 3);
		dg.addEdge(3, 4);
		TarjinLCA tj = new TarjinLCA(dg,0,2);
		System.out.println(tj.query(0, 1));
		
	}
}
