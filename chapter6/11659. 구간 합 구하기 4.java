import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        // i번째부터 j번쨰 수까지의 합
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++) arr[i] = Integer.parseInt(st.nextToken());
        int[] dp = new int[n+1];
        
        // 누적합
        dp[1] = arr[1];
        for(int i=2; i<=n; i++) {
            dp[i] += dp[i-1] + arr[i];
        }

        while(m --> 0) {
            // i번째 수부터 j번쨰 수까지 합
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int sum = dp[b] - dp[a] + arr[a];

            sb.append(sum).append("\n");
        }
        System.out.print(sb);
    }
}