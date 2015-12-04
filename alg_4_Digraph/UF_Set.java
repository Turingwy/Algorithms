package alg_4_Digraph;

public class UF_Set { //non-weight
	private int[] id;
	private int[] size;
	
	public UF_Set(int n) {
		id = new int[n];
		size = new int[n];
		for (int i = 0; i < n; i++) {
			id[i] = i;
			size[i] = 1;
		}
	}

	public int find(int x) {
		int k = x;
		while (id[x] != x) {
			x = id[x];
		}
		int root = x;
		int tmp;
		while(k != root) {
			tmp = id[k];
			id[k] = root;
			k = tmp;
		}
		return root;
	}

	public boolean connected(int x, int y) {
		return find(x) == find(y);
	}

	public void union(int x, int y) {
		int xPos = find(x);
		int yPos = find(y);
		if (xPos == yPos)
			return;
		else {
			id[xPos] = id[yPos];
			
		}
	}
	
	public int connectCount(int v) {
		return size[find(v)];
	}
	private static class TimeCost {
		private long start;
		public TimeCost() {
			start = System.currentTimeMillis();
		}
		
		public double end() {
			long now = System.currentTimeMillis();
			double ret = (double)(now - start)/1000;
			start = now;
			return ret;
		}
	}
	public static void main(String [] args) {
		UF_Set s;
		double last = 1;
		TimeCost c = new TimeCost();
		for(int i = 250;;i+=i) {
			s = new UF_Set(1000);
			for(int j = 0;j<i;j++) {
				s.union((int)(Math.random()*1000),(int)(Math.random()*1000));
			}
			double now = c.end();
			System.out.printf("%d %f %5.1f\n",i,now, now/last);
			last = now;
		
		}
		
		
	
		
		
	}

}

