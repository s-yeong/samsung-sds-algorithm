import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1 = br.readLine();
        String str2 = br.readLine();
        int length1 = str1.length();
        int length2 = str2.length();

        // LCS : 모두의 부분 수열이 되는 수열 중 가장 긴 것

        // 1번 문자열을 i 까지 쓰고, 2번 문자열을 j 까지 쓸때 LCS 최대 길이
        int[][] dp = new int[length1 + 1][length2 + 1];
        int[][] dir = new int[length1 + 1][length2 + 1];

        // 1:str1의 문자에서 왔을 때, 2:str2의 문자에서 왔을 때, 3:두 문자가 같은 경우(대각선)으로 왔을 때
        final int from1 = 1;
        final int from2 = 2;
        final int equal = 3;

        for(int i=1; i<=length1; i++) {
            for(int j=1; j<=length2; j++) {

                // str1의 문자에서 왔을 때
                if(dp[i-1][j] >= dp[i][j-1]) {
                    dir[i][j] = from1;
                    dp[i][j] = dp[i-1][j];
                }
                // str2의 문자에서 왔을 때
                else {
                    dir[i][j] = from2;
                    dp[i][j] = dp[i][j-1];
                }
                // 두 문자가 같은 경우,
                if(str1.charAt(i-1) == str2.charAt(j-1)) {
                    if(dp[i][j] < dp[i-1][j-1] + 1) {
                        dp[i][j] = dp[i-1][j-1] + 1;
                        dir[i][j] = equal;
                    }
                }
            }
        }

        int answer = dp[length1][length2];
        StringBuilder LCS = new StringBuilder();

        while(length1>=1 && length2>=1) {

            if(dir[length1][length2] == from1) {
                length1--;
            }
            else if(dir[length1][length2] == from2) {
                length2--;
            }
            else {
                LCS.append(str1.charAt(length1-1));
                length1--;
                length2--;
            }
        }

        System.out.println(answer);
        System.out.print(LCS.reverse());
    }
}