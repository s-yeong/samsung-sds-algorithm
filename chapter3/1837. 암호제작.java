import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String p = st.nextToken();   // 두 소수의 곱
        int k = Integer.parseInt(st.nextToken());
        // p, q 중 하나라도 K보다 작은 암호는 좋지 않은 암호로 간주하여 제출하지 않기로 함
        // => K이하의 소수를 구한 다음, p로 나누어 떨어지면 BAD

        boolean[] isPrime = new boolean[k+1];
        Arrays.fill(isPrime, true);
        for(int i=2; i<=k; i++) {
            if(isPrime[i]) {
                for(int j=i+i; j<=k; j=j+i) {
                    isPrime[j] = false;
                }
            }
        }

        for(int i=2; i<k; i++) {
            if(isPrime[i]) {
                int sum = 0;
                for(char x : p.toCharArray()) {
                    sum *= 10; // <<
                    sum += x - '0';
                    sum %=i;
                }
                if(sum == 0) {
                    System.out.println("BAD " + i);
                    System.exit(0);
                }
            }
        }
        System.out.println("GOOD");
    }
}