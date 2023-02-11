import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int visit_all;
    static int[][] W;
    static int[][] dp;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 도시의 수
        n = Integer.parseInt(br.readLine());

        // i에서 j로 가기 위한 비용
        W = new int[n+1][n+1];
        for(int i=1; i<=n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        /*
        1. 도시들 사이에 길이 없을 수도 있다 (W[i][j] == 0)
        2. 한 번갔던 도시로는 다시갈 수 없다 => 방문 배열
        3. 가장 적은 비용을 들이기
         */


        // 방문 배열을 2진수 비트 마스킹으로 표현
        // ex) n=4, 1을 4칸 밀기, 10000(2) - 1 = 1111(2)
        // 0~1111(2)
        visit_all = (1 << n) - 1; // 모든 도시 방문한 경우

        // cur 도시까지 visit에 기록된 도시들을 방문하고 가는 최소 비용
        // 시간 복잡도 : O(N!) -> O(2^N*N^2)
        dp = new int[n+1][visit_all + 1];

        // 0. 초기값 셋팅 (1번에서 시작)
        for(int i=0; i<=n; i++) {
            for(int j=0; j<=visit_all; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[1][1] = 0;

        // 메모이제이션 방식 -> 재귀로 구현
        answer = Integer.MAX_VALUE;
        recur(1, 1);
        System.out.println(answer);
    }

    // 현재 위치, 방문 비트마스크
    static void recur(int now, int visit) {

        // 모든 지점을 다 돌았을 때, 종료
        if(visit == visit_all) {

            // 첫 지점으로 못가면,
            if(W[now][1] == 0) return;

            answer = Math.min(answer, dp[now][visit] + W[now][1]);
        }

        for(int i=1; i<=n; i++) {

            // 다음 방문 지점
            int next = (1 << (i-1));

            int next_visit = next | visit;

            // 방문 했으면,
            if(next_visit == visit) continue;

            // 다음 지점으로 가는 값이 존재하지 않으면,
            if(W[now][i] == 0) continue;

            // cur 도시까지 visit에 기록된 도시들을 방문하고 가는 최소 비용
            if(dp[i][next_visit] > dp[now][visit] + W[now][i]) {
                dp[i][next_visit] = dp[now][visit] + W[now][i];
                recur(i, next_visit);
            }
        }
    }
}