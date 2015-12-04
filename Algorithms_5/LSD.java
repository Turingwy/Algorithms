package Algorithms_5;
/*
 * 低位优先的字符串排序
 */
public class LSD {
	//定长
	public static void sort(String[] s) {
		int R = 256;
		int[] r = new int[R+1];
		String[] aux = new String[s.length];
		for(int i = 0;i<s[0].length();i++) {
			
			for(int k = 0; k < s.length; k++) {
				r[s[k].charAt(s[0].length()-1-i)+1]++;
			}
			
			for(int k = 0; k < R ; k++) {
				r[k+1] += r[k];
				
			}
			
			for(int k = 0; k < s.length; k++) {
				aux[r[s[k].charAt(s[0].length()- 1 - i)]++] = s[k];
			}
			for(int k = 0; k < s.length; k++) {
				s[k] = aux[k];
				
			}
			
			for(int j = 0; j <= R; j++)
				r[j] = 0;
			
		}
	}
	
	public static void main(String[] args) {
		String[] s = new String[] {"yx","dx", "ab", "cd"};
		sort(s);
		for(int k = 0; k < s.length; k++)
			System.out.println(s[k]);
	}
}
