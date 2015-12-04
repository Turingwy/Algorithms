package Algorithms_5;

import java.util.Scanner;

import algorithms_3.RedBlackTree;

public class Alphabet {
	private char[] arr;
	private RedBlackTree<Character, Integer> st;
	
	public Alphabet(String s) {
		arr = s.toCharArray();
		st = new RedBlackTree();
		for(int i = 0;i<arr.length;i++) 
			st.puts(arr[i], i);
	}
	
	public char toChar(int index) {
		return arr[index];
	}
	
	public int index(char c) {
		int a = st.gets(c);
		return a;
	}
	
	public boolean contains(char c) {
		return st.contains(c);
	}
	
	public int R() {
		return arr.length;
	}

	public static void main(String[] args) {
		Alphabet al = new Alphabet("ABCD");
		int[] count = new int[al.R()];
		String n = new Scanner(System.in).nextLine();
		for(int i = 0;i<n.length();i++) {
			if(al.contains(n.charAt(i))) {
				count[al.index(n.charAt(i))]++;
			}
		}
		int j = 0;
		for(int i : count) {
			System.out.println(al.toChar(j++) + " " + i);
		}
	}
}
