import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] paskal = new int[1001][1001];

        // n+mCk
        paskal[0][0] = 1;
        for(int i=1; i<=n+m; i++) {
            paskal[i][0] = 1;
            for(int j=1; j<=i; j++) {
                paskal[i][j] = paskal[i-1][j-1] + paskal[i-1][j];

                // k보다 크면 의미 없으므로 k+1로 두기 => int 범위 내에 들어온다
                if(paskal[i][j] > k) paskal[i][j] = k+1;
            }
        }

        // 문자열의 개수가 k보다 작으면,
        if(paskal[n+m][n] < k) {
            System.out.println(-1);
            System.exit(0);
        }

        int pos = n;
        for(int i=m+n; i>=1; i--) {

            // a를 z보다 먼저 다 썼을 때,
            if(pos==0) {
                while(i-->0) {
                    sb.append('z');
                }
                break;
            }

            // k번째 문자열이 'a'로 시작한다면,
            if(paskal[i-1][pos-1] >= k) {
                sb.append('a');
                pos--;
            }
            // k번째 문자열이 'z'로 시작한다면,
            else {
                sb.append('z');
                // z로 시작하는 k-(a로 시작하는 문자열 수) 번쨰 문자열 탐색
                k -= paskal[i - 1][pos - 1];
            }
        }

        System.out.print(sb);
    }
}