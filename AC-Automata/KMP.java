
public class KMP {
    private static int len;
    private static int[] next = new int[100];           // next array for the string "ababca"

    private static void generateNextArray(String pattern) {
        next[0] = -1;
        for(int i = 1; i < pattern.length(); i++) {
            for(int j = i-1; j >= 1; j--) {
                int k = 0;
                for(; k < j; k++) {
                    if(pattern.charAt(k) != pattern.charAt(k+i-j))
                        break;
                }

                if(k >= j) {
                    next[i] = j;
                    break;
                }
            }

        }
    }

    public static void main(String[] args) {
        String str = "abcabababaabaaabababaababacaabacabadbadbad";
        String pattern = "abababaab";
        len = pattern.length();
        generateNextArray(pattern);
        for(int i = 0; i < pattern.length(); i++) {
            System.out.println(next[i]);
        }
        int i = 0, j = 0;
        while (j < len && i < str.length()) {
            if (str.charAt(i) != pattern.charAt(j)) {
                j = next[j];
                if (j == -1) {
                    i++;
                    j++;
                }
            } else {
                i++;
                j++;
            }
        }

        if(j < len)
            System.out.println("No substring found!");
        else {
            System.out.println(i-len);
            for(int k = i - len; k < i; k++) {
                System.out.print(str.charAt(k));
            }
        }

    }

}
