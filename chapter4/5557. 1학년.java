import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // i번째 숫자까지 사용해서 j를 만드는 경우의 수
        long[][] dp = new long[n][21];
        dp[1][arr[1]] = 1;

        for(int i=2; i<n; i++) {
            for(int j=0; j<=20; j++) {
                if(dp[i-1][j] != 0) {
                    if (j - arr[i] >= 0) dp[i][j - arr[i]] += dp[i - 1][j];
                    if (j + arr[i] <= 20) dp[i][j + arr[i]] += dp[i - 1][j];
                }
            }
        }

        System.out.println(dp[n-1][arr[n]]);
    }

}