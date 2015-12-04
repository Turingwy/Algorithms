package DynamicPrograming;
//Longest Common Subsequence 
public class lcs {
	String a = "abcdefg";
	String b = "adehfg";
	int c[][] = new int[a.length()+1][b.length()+1];
	
	public void solve(int al, int bl) {
		if(c[al][bl] != -1)
			return;
		if(a.charAt(al-1) == b.charAt(bl-1)) {
			solve(al-1, bl-1);
			c[al][bl] = c[al-1][bl-1] + 1;
		}
		else {
			solve(al-1,bl);
			solve(al,bl-1);
			c[al][bl] = (c[al-1][bl]>c[al][bl-1] ? c[al-1][bl] : c[al][bl-1]);
		}
	}
	
	public lcs() {
		for(int i = 0;i<a.length()+1;i++)
			for(int j = 0;j<b.length()+1;j++) 
				if(i == 0 || j == 0)
					c[i][j] = 0;
				else
					c[i][j] = -1;
		
		
	}
	
	public static void main(String[] args) {
		lcs a = new lcs();
		a.solve(7,6);
		System.out.println(a.c[7][6]);
		
	}
}
