import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int cnt = 0;

        // k번째 지우는 수
        boolean[] isPrime = new boolean[n+1];
        Arrays.fill(isPrime, true);

        for(int i=2; i<=n; i++) {
            if(isPrime[i]) {
                for(int j=i; j<=n; j=j+i) {
                    if(isPrime[j]) {
                        isPrime[j] = false;
                        cnt++;  // 지우기
                    }
                    // k번쨰 지우는 수이면,
                    if(cnt == k) {
                        System.out.println(j);
                        System.exit(0);
                    }
                }
            }
        }
    }
}