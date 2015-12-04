package algorithms_1to2;

import java.util.Arrays;
import java.util.Iterator;

public class IndexMinPriorityQueue<T extends Comparable<T>>{
	private T items[];
	private int len;
	private int max;
	private int ranks[];
	private int reranks[];

	public void print() {
		for(int i = 1;i<=len;i++) {
			System.out.println(items[ranks[i]]);
		}
	}
	
	public boolean isEmpty() {
		return len <= 0;
	}
	public IndexMinPriorityQueue() {
		this(20);
	}

	public IndexMinPriorityQueue(int max) {
		this.max = max;
		items = (T[]) new Comparable[max];
		ranks = new int[max];
		reranks = new int[max];
		len = 0;
		for(int i = 0;i<max;i++)
			reranks[i] = -1;
		
	}

	public T getItem(int k) {
		return items[k];
	}
	
	private boolean bigger(int a, int b) {
		
		return items[a].compareTo(items[b]) > 0;
		
	}

	private void swim(int k) {
		int parent = k / 2;
		int tmp = ranks[k];
		while (k > 1) {
			
			if (bigger(ranks[parent], tmp)) {
				reranks[ranks[parent]] = k;
				ranks[k] = ranks[parent];
				k = parent;
				parent = k / 2;
			} else
				break;
		}
		reranks[tmp] = k;
		ranks[k] = tmp;
	}

	private void sink(int k) {
		int child = k * 2;
		int tmp = ranks[k];
		while (child <= len) {
			if (child + 1 <= len && bigger(ranks[child], ranks[child + 1]))
				child++;

			if (bigger(tmp, ranks[child])) {
				reranks[ranks[child]] = k;
				ranks[k] = ranks[child];
				k = child;
				child = k * 2;
			} else
				break;
		}
		reranks[tmp] = k;
		ranks[k] = tmp;

	}

	public void puts(int k, T it) {
		if (len >= max - 1) {
			T[] nitems = (T[]) new Comparable[2 * max];
			int[] nranks = new int[2 * max];
			max *= 2;
			for (int i = 0; i <= len; i++) {
				nitems[i] = items[i];
				nranks[i] = ranks[i];
			}
			items = nitems;
			ranks = nranks;
		}

		items[k] = it;
		ranks[++len] = k;
		reranks[k] = len;
		swim(len);

	}

	public int delete(int k) {
		ranks[reranks[k]] = ranks[len];
		reranks[k] = -1;
		reranks[ranks[len]] = k;
		len--;
		sink(k);
		return k;

	}
	

	public int deleteMin() {
		return delete(ranks[1]);
	}
	
	public T min() {
		return items[ranks[1]];
	}
	public boolean contains(int rank) {
		if(reranks[rank] == -1) {
			return false;
		}
		else
			return true;
	}
	
	public void change(int k, T t) {
		if(reranks[k] == -1)
			puts(k,t);
		else {
			T tmp = items[k];
			items[k] = t;
			if(tmp.compareTo(t) > 0)
				swim(reranks[k]);
			else
				sink(reranks[k]);
		}
	}
	
	
	//¶àÂ·ÅÅÐò
	public static void main(String[] args) {
		IndexMinPriorityQueue<Integer> a = new IndexMinPriorityQueue<>();
		Integer[][] n = new Integer[3][];
		for (int i = 0; i < 3; i++)
			n[i] = new Integer[10];

		for (int i = 0; i < 10; i++) {
			n[0][i] = (int) (Math.random() * 40);
			n[1][i] = (int) (Math.random() * 40);
			n[2][i] = (int) (Math.random() * 40);
		}
		for (int i = 0; i < 3; i++) {
			Arrays.sort(n[i]);
			for (int j : n[i]) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
		int x[] = { 1, 1, 1 };
		a.puts(0, n[0][0]);
		a.puts(1, n[1][0]);
		a.puts(2, n[2][0]);

		for (int i = 0; i < 100; i++) {
			int r = a.deleteMin();
			if (x[r] < 10)
				a.puts(r, n[r][x[r]++]);

		}
	}
	
	
}
