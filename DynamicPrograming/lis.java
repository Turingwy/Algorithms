package DynamicPrograming;
//longest increasing subsequence
public class lis {
	private int[] id;
	private int size[];
	private int paths[];
	public lis(int[] id) {
		this.id = id;
		size = new int[id.length];
		paths = new int[id.length];
	}
	
	public void start() {
		size[0] = 1;
		paths[0] = -1;
		int max = 1;
		for(int i = 1;i<id.length;i++) {
			max = 1;
			for(int j = 0;j<i;j++) {
				if(id[j] < id[i] && max < size[j]+1) {
					max = size[j]+1;
					paths[i] = j;
				}
			}
			size[i] = max;
			if(size[i] == 1) {
				paths[i] = -1;
			}
			
		}
		System.out.println(size[id.length-1]);
		int i = id.length-2;
		while(paths[i] != -1) {
			System.out.println(id[i]);
			i = paths[i];
		}
		System.out.println(id[i]);
	}
	
	public static void main(String[] args) {
		int[] s = {5,4,6,2,9,6};
		new lis(s).start();
	
	}
}
