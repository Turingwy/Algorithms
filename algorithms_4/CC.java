package algorithms_4;

public class CC {
	private boolean[] marked;
	private int[] id;
	private Graph g;
	private int count;
	public CC(Graph g) {
		this.g = g;
		marked = new boolean[g.getV()];
		id = new int[g.getV()];
		create();
	}
	
	private void create() {
		int nowid = 0;
		for(int i = 0;i<g.getV();i++) {
			if(!marked[i])
				dfs(i, nowid++);
		}
		count = nowid;
	} 
	
	private void dfs(int v, int connid) {
		marked[v] = true;
		id[v] = connid;
		for(int w : g.adj(v)) {
			if(!marked[w])
				dfs(w, connid);
		}
	}
	
	public boolean connected(int i, int j) {
		return id[i] == id[j];
	}
	
	public int count() {
		return count;
	}
	
	public int id(int i) {
		return id[i];
	}
	public static void main(String[] args) {
		Graph g = new Graph(3);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(0, 2);
		CC s = new CC(g);
		System.out.println(s.count());
	}
}