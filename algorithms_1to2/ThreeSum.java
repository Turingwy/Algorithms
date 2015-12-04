package algorithms_1to2;
import java.util.Arrays;

public class ThreeSum {
	public int [] n;
	public int []a;
	public ThreeSum(int N) {
		n = new int[N];
		a = new int[N];
		for(int i = 0;i<N;i++) {
			n[i] = (int) (Math.random()*1000);
			if(n[i] %2 == 0)
				n[i] = -n[i];
		}
	}
	public static int bisearch(int []n,int a,int l, int length) {
		int left = l;
		int right = length-1;
		int mid = (left+right)/2;
		while(left <= right) {
			if(n[mid] < a) {
				left = mid+1;
			}
			else if(n[mid] > a)
				right = mid-1;
			else
				return mid;
			
			mid = (left+right)/2;
			

		}
		return -1;
	} 
	public void sum() {
		int N = n.length;
		Arrays.sort(n);
		
		int b;
		for(int i = 0;i<N;i++) {
			for(int j = i+1;j<N;j++) {
				b = bisearch(n,-(n[i]+n[j]),j+1,N);
				if(b != -1) {
					//System.out.println(n[i] + " " + n[j] + " " + n[b]);
				}
			}
		}
		
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

	public static void main(String[] args) {
		ThreeSum s;
		double last = 1;
		TimeCost c = new TimeCost();
		for(int i =250;;i+=i) {
			s = new ThreeSum(i);
			s.sum();
			double now = c.end();
			System.out.printf("%d %5.1f\n",i,now/last);
			last = now;
			
		}
		

		
	}
}
