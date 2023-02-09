import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] board = new int[n][m];
        int[][] dp = new int[n][m];

        for(int i=0; i<n; i++) {
            String[] split = br.readLine().split("");
            for(int j=0; j<m; j++) {
                dp[i][j] = board[i][j] = Integer.parseInt(split[j]);
            }
        }

        // dp[i][j] : i,j 위치를 오른쪽 아래 기준으로 하는 정사각형
        for(int i=1; i<n; i++) {
            for(int j=1; j<m; j++) {
                if(board[i][j] == 1) {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
            }
        }

        int max = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                max = Math.max(max, dp[i][j]);
            }
        }
        System.out.println(max * max);

    }
}