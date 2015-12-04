package DynamicPrograming;

public class MinSubsequence {
	public static void main(String[] args) {
		int id[] = { 1, 2, -2, -4, 8, 6, -5 };

		int thisLeigth = 0;
		int min = 0;

		for (int i = 0; i < id.length; i++) {
			thisLeigth += id[i];

			if (thisLeigth < min) {
				min = thisLeigth;
			} else if (thisLeigth > 0) {
				thisLeigth = 0;
			}
		}
		
		System.out.println(min);
	}
}
