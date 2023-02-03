import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        final int MAX = 1000000;
        boolean[] isPrime = new boolean[MAX + 1];
        Arrays.fill(isPrime, true);

        for(int i=2; i<=MAX; i++) {
            if(isPrime[i]) {
                for(int j=i+i; j<=MAX; j+=i) {
                    isPrime[j] = false;
                }
            }
        }

        while(true) {
            int n = Integer.parseInt(br.readLine());
            if(n==0) break;

            // 짝수 정수 n
            // 4보다 큰 모든 짝수는 두 홀수 소수의 합
            // n = a + b
            boolean flag = false;
            for(int i=2; i<= n/2; i++) {
                if(isPrime[i] && isPrime[n-i]) {
                    sb.append(n).append(" = ").append(i).append(" + ").append(n-i).append("\n");
                    flag = true;
                    break;
                }
            }
            if(!flag) sb.append("Goldbach's conjecture is wrong.").append("\n");
        }
        System.out.print(sb);
    }
}