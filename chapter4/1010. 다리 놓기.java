import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
// 다리낄 서로 겹칠 수 없다 -> n 개를 선택하고 서쪽에 연결하는 방법은 1가지로 고정 -> 조합
        int T = Integer.parseInt(br.readLine());
        while(T-->0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());   // 강의 서쪽
            int m = Integer.parseInt(st.nextToken());   // 강의 동쪽
            // mCn
            sb.append(recur(m, n)).append("\n");
        }
        System.out.print(sb);
    }

    static int[][] dp = new int[30][30];
    static int recur(int m, int n) {
        if(dp[m][n] != 0) return dp[m][n];
        if(n==0) return 1;
        if(n==m) return 1;
        return dp[m][n] = recur(m - 1, n) + recur(m - 1, n - 1);
    }

}