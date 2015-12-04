package algorithms_1to2;

import java.util.Iterator;

public class MaxPriorityQueue<T extends Comparable<T>> {
	private T items[];
	int len;
	int max;
	
	public MaxPriorityQueue() {
		this(20);
	}
	
	public MaxPriorityQueue(T[] arr, int len) {
		items = arr;
		this.len = len;
		this.max = arr.length;
		initMQ();
	}
	
	private void initMQ() {
		int i = len/2;
		while(i>=1) {
			sink(i);
			i--;
		}
	}
	
	public MaxPriorityQueue(int max) {
		this.max = max;
		items = (T[])new Comparable[max];
		len = 0;
	}
	
	
	private boolean less(T a, T b) {
		return a.compareTo(b) < 0;
	}
	
	private void swim(int k) {
		int parent = k/2;
		T tmp = items[k];
		while(k > 1) {
			if(less(items[parent],tmp)) {
				items[k] = items[parent];
				k = parent;
				parent = k/2;
			}
			else break;
		}
		items[k] = tmp;
	}
	private void sink(int k) {
		int child = k*2;
		T tmp = items[k];
		while(child<=len) {
			if(child+1 <= len && less(items[child],items[child+1])) 
				child++;
			
			if(less(tmp,items[child])) {
				items[k] = items[child];
				k = child;
				child = k*2;
			}
			else
				break;
		}
		items[k] = tmp;
		
	}
	
	public void puts(T it) {
		if(len >= max-1) {
			T[] nitems = (T[])new Comparable[2*max];
			max *= 2;
			for(int i = 0;i<=len;i++) {
				nitems[i] = items[i];
			}
			items = nitems;
		}
		
		items[++len] = it;
		
		swim(len);
		
	}
	
	public void print() {
		for(int i = 1;i<=len;i++) {
			System.out.print(items[i]+" ");
		}
		System.out.println();
	}
	public T deleteMax() {
		T t = items[1];
		delete(t);
		return t;
		
		
	}
	public void delete(T it) {
		for(int i = 1;i<=len;i++) {
			if(it.equals(items[i])) {
				items[i] = items[len];
				len--;
				sink(i);
			}
		}
				
	}
	
	
}
