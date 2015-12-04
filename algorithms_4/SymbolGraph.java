package algorithms_4;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;

import algorithms_3.RedBlackTree;

public class SymbolGraph {
	private RedBlackTree<String,Integer> st; 
	private String keys[];
	private Graph g;
	private int count;
	
	public SymbolGraph(int num) {
		g = new Graph(num);
		st = new RedBlackTree<String,Integer>();
		keys = new String[g.getV()];
	}
	
	public void puts(String a, String b) {
		if(!st.contains(a)) {st.puts(a, count);keys[count] = a;count++;}
		if(!st.contains(b)) {st.puts(b, count);keys[count] = b;count++;}
		
		g.addEdge(st.gets(a), st.gets(b));
	}
	
	public Iterable<String> adj(String v) {
		int pos = st.gets(v);
		Iterable<Integer> iter = g.adj(pos);
		LinkedList<String> iter2 = new LinkedList<String>();
		for(int w:iter) {
			iter2.add(keys[w]);
		}
		return iter2;
	}
	
	public Graph G() {
		return g;
	}
	public int index(String name) {
		return st.gets(name);
	}
	
	public String name(int v) {
		return keys[v];
	}	
		
	public static void main(String [] args) {
		SymbolGraph sg = new SymbolGraph(4);
		sg.puts("yanxu", "wdx");
		sg.puts("chouchou", "yanxu");
		sg.puts("zhuangzhuang", "wdx");
		
		for(String s : sg.adj("wdx"))
			System.out.println(s);
	}
}
