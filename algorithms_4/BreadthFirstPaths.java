package algorithms_4;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
public class BreadthFirstPaths {
	private Graph g;
	private boolean[] marked;
	private int[] paths;
	int startNode;
	public BreadthFirstPaths(Graph g, int sn) {
		this.g = g;
		marked = new boolean[g.getV()];
		paths = new int[g.getV()];
		startNode = sn;
		
	}
	
	private void bfs() {
		LinkedList<Integer> q = new LinkedList<Integer>();
		q.add(startNode);
		marked[startNode] = true;
		paths[startNode] = startNode;
		while(!q.isEmpty()) {
			int s = q.removeLast();
			
			
			for(int v : g.adj(s)) {
				if(!marked[v]) {
					marked[v] = true;
					paths[v] = s;
					q.add(v);
				}
				
			}
		}
	}
	
	public boolean hasPath(int v) {
		return marked[v];
	}
	
	public Iterable<Integer> path(int v) {
		Stack<Integer> s = new Stack<Integer>();
		if(!hasPath(v))	return null;
		
		while(v != startNode) {
			s.push(v);
			v = paths[v];
		}
		s.push(startNode);
		return s;
		
	} 
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int a,b;
		a = in.nextInt();
		b = in.nextInt();
		in.nextLine();
		Graph g = new Graph(a*b);
		int[][]arr = new int[a][b];
		for(int i = 0;i<a;i++) {
			String str = in.nextLine();
			
			for(int j = 0;j<b;j++) {
				arr[i][j] = Character.getNumericValue(str.charAt(j));
			}
		}
		
		for(int i = 0;i<a*b;i++) {
			int shu = i%a;
			int heng = i/a;
			if(arr[heng][shu] == 1)
				continue;
			else {
				if(shu -1 >= 0 && arr[heng][shu-1] == 0) {
					g.addEdge(i, heng*a+shu-1);
				}
				if(shu +1 < b && arr[heng][shu+1] == 0) {
					g.addEdge(i, heng*a+shu+1);
				}
				if(heng-1 >= 0 && arr[heng-1][shu] == 0)
					g.addEdge(i, (heng-1)*a + shu);
				if(heng+1 > b && arr[heng+1][shu] == 0)
					g.addEdge(i, (heng+1)*a + shu);
			}
		}
		
		
		BreadthFirstPaths s = new BreadthFirstPaths(g,0);
		s.bfs();
		Stack<Integer> n = (Stack<Integer>) s.path(a*b-1);
		int aa = n.pop();
		int count = n.size();
		System.out.println(count);
		while(!n.isEmpty()) {
			int bb = n.peek();
			int aax = aa/a;
			int aay = aa%a;
			int bbx = bb/a;
			int bby = bb%a;
			char d = 0;
			if(aax < bbx) {
				d = 'D';
			} else if(aax>bbx) {
				d = 'U';
			} else if(aax == bbx) {
				if(aay < bby)
					d = 'R';
				else 
					d = 'L';
			}
			
			System.out.print(d);
			
			aa = n.pop();
		}
	}
	
	
}
