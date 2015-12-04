package algorithms_1to2;
import java.util.Arrays;

public class BigNumber {
	private int[] leftNumber = new int[1000];
	private int[] rightNumber = new int[1000];
	private int[] r = new int[1000];
	private int a;
	private int b;

	public BigNumber(int a) {
		arr(a);
	}

	public void arr(int a) {
		int i = 0;
		int b =a;
		while (a / 10 != 0) {
			leftNumber[i++] = a % 10;
			a /= 10;
		}
		leftNumber[i] = a;
		this.a = i + 1;
		i = 0;
		while (b / 10 != 0) {
			rightNumber[i++] = b % 10;
			b /= 10;
		}
		rightNumber[i] = b;
		this.b = i + 1;
	}

	public void mul(int jie) {
		int jin = 0;
		int i=0,j=0;
		for (int k = 1; k < jie; k++) {
			for (j = 0; j < b; j++) {
				for (i = 0; i < a; i++) {
					int tmp = r[i+j];
					r[i + j] = ((leftNumber[i] * rightNumber[j]) + r[i + j] + jin) % 10;
					jin = ((leftNumber[i] * rightNumber[j]) + tmp + jin) / 10;
					
				}
				r[i + j] = jin;
				jin = 0;
				
			}
			if(r[i+j-1] != 0)
				a = i+j;
			else
				a = i+j-1;

			leftNumber = Arrays.copyOf(r,r.length);	
			for(int kunle =0;kunle<r.length;kunle++) {
				r[kunle] = 0;
			}
		
			
		}

		for (int aa = a-1; aa >=0; aa--) {
			System.out.print(leftNumber[aa]);

		}

	}

	public static void main(String[] args) {
		BigNumber a = new BigNumber(12);
		a.mul(10);
		
		
	}
}
