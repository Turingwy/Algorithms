package DynamicPrograming;

public class MaxSubsequence {	
	public static void main(String[] args) {
		int id[] = {1,-2,4,-6,2,4,8};
		int thisLeigth = 0;
		int max = 0;
		
		for(int i = 0;i<id.length;i++) {
			thisLeigth += id[i];
			if(thisLeigth > max)
				max = thisLeigth;
			else if(thisLeigth < 0)
				thisLeigth = 0;	
		}
		
		System.out.println(max);
	}
}
