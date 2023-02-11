import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 메모리
        int[] memory = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }
        // 비활성화 비용
        int[] cost = new int[n+1];
        int total_cost = 0;
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            total_cost+=cost[i];
        }

        /*
        1. n개의 앱 활성화, 각각 m 바이트 만큼 메모리 사용, 비활성화 후 다시 실행하는 비용 c
         */

        // 비활성화 했을 경우의 비용 c 의 합을 최소화하여 필요한 메모리 m 바이트를 확보하는 방법
        // => c의 합을 최소화하는 메모리 m

        // 1~i번 까지 앱을 확인헸을 때, j의 비용으로 확보할 수 있는 최대한의 메모리
        int[][] dp = new int[n+1][total_cost+1];


        for(int i=1; i<=n; i++) {
            for (int j = 1; j <= total_cost; j++) {
                dp[i][j] = Math.max(dp[i][j], dp[i-1][j]);

                // 비용이 허락하는한, i번째 어플을 계속 꺼본다
                if(j - cost[i] >= 0) {
                    // j값을 cost[i] 만큼 빼서 켜본다
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-cost[i]]+memory[i]);
                }
            }
        }

        int answer = total_cost+1;
        for (int i = 1; i <= total_cost; i++) {

            // m 메모리 확보하면,
            if(dp[n][i] >= m) {
                answer = i;
                break;
            }
        }
        System.out.println(answer);

    }
}