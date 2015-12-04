package algorithms_4;

public class UnionFind {
	private int[] ufarr;
	private int[] weight;
	public UnionFind(int setCount) {
		ufarr = new int[setCount];
		weight = new int[setCount];
		for(int i = 0;i<setCount;i++) {
			ufarr[i] = i;
			weight[i] = 1;
		}
	}
	
	public int find(int v) {
		int parent = ufarr[v];
		while(parent != ufarr[parent]) {
			parent = ufarr[parent];
		} 
		
		
		return parent;
	}
	
	public void union(int v, int w) {
		int vParent = find(v);
		int wParent = find(w);
		
		if(vParent != wParent) {
			if(weight[vParent] > weight[wParent]) {
				ufarr[wParent] = ufarr[vParent];
				weight[vParent] += weight[wParent];
			}
			else {
				ufarr[vParent] = ufarr[wParent];
				weight[wParent] += weight[vParent];
				
			}
		}
		
	}
	
	public int connectCount(int v) {
		return weight[find(v)];
	}
	public boolean connect(int v, int w) {
		return find(v) == find(w);
	}
	public static void main(String[] args) {
		UnionFind uf = new UnionFind(3);
		uf.union(1,2);
		System.out.println(uf.find(0));
		
	}
}
