package DynamicPrograming;

public class lcstring {
	String a = "abcdefg";
	String b = "afgdeh";
	int c[][] = new int[a.length()+1][b.length()+1];
	public void solve(int al, int bl) {
		if(c[al][bl] != -1)
			return;
		if(a.charAt(al-1) == b.charAt(bl-1)) {
			solve(al-1, bl-1);
			c[al][bl] = c[al-1][bl-1] + 1;
		}
		else {
			c[al][bl] = 0;
		}
	}
	
	public lcstring() {
		for(int i = 0;i<a.length()+1;i++)
			for(int j = 0;j<b.length()+1;j++) 
				if(i == 0 || j == 0)
					c[i][j] = 0;
				else
					c[i][j] = -1;
		
		
	}
	
	public static void main(String[] args) {
		lcstring a = new lcstring();
		
		
		int max = 0;
		for(int i = 1;i<=7;i++) {
			for(int j = 1;j<=6;j++) {
				a.solve(i, j);
				System.out.print( a.c[i][j] + " ");
			}
			System.out.println("");
		}
	
		
	}
}
