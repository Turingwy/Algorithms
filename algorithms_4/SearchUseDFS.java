package algorithms_4;

public class SearchUseDFS {
	Graph g;
	int s;
	boolean[] marked;
	int count;	//连通个数
	public SearchUseDFS(Graph g, int s) {
		this.g = g;
		marked = new boolean[g.getV()];
		this.s = s;
		dfs(s);
	}
	
	public void dfs(int v) {
		marked[v] = true;
		count++;
		for(int w:g.adj(v)) {
			if(!marked[w])
				dfs(w);
		}
	} 
	
	public boolean marked(int v) {
		return marked[v];
	}
	
	public int count() {
		return count;
	}
}
