import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        String str1 = br.readLine();
        String str2 = br.readLine();
        int length1 = str1.length();
        int length2 = str2.length();

        // 1번 문자열을 i번째와 2번 문자열 j번째를 마지막으로 하는 공통된 "연속" 부분 문자열의 길이
        int[][] dp = new int[length1 + 1][length2 + 1];
        int answer = 0;
        for(int i=1; i<=length1; i++) {
            for(int j=1; j<=length2; j++) {
                // 문자 같은 경우
                if(str1.charAt(i-1) == str2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1]+1;
                    answer = Math.max(answer, dp[i][j]);
                }
            }
        }

        System.out.println(answer);
    }
}