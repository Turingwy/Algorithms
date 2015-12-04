package Algorithms_5;

import java.util.Scanner;

public class KeySort {
	private class Node {
		String s;
		int num;
		public Node(String s, int num) {
			this.s = s;
			this.num = num;
		}
	}
	private int[] count;
	private int R;
	private Node[] ns;
	private Node[] aux;
	public KeySort(int R, int cnt) {
		this.R = R+2;
		ns = new Node[cnt];
		aux = new Node[cnt];
		count = new int[R+2];
		Scanner in = new Scanner(System.in);
		for(int i = 0;i<cnt;i++) {
			ns[i] = new Node(in.next(), in.nextInt());
		}
		solve();
	}
	
	public void solve() {
		for(int i = 0;i<ns.length;i++) {
			count[ns[i].num+1]++; 
		}
		
		for(int i = 0;i<R-1;i++) {
			count[i+1] += count[i];
			System.out.println(count[i+1]);
		}

		for(int i = 0;i<ns.length;i++) {
			
			aux[count[ns[i].num]++] = ns[i]; 
		}
		ns = aux;
	}
	
	public static void main(String[] args) {
		KeySort ks = new KeySort(3, 5);
		
		for(Node i: ks.aux) {
			System.out.println(i.s + " " +i.num);
		}
	}
 }
