import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer> primeList = new ArrayList<>();
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        //자연수 N을 연속된 소수의 합으로 나타낼 수 있는 경우의 수

        // n이하의 소수 나타내기
        boolean[] isPrime = new boolean[n+1];
        Arrays.fill(isPrime, true);
        for(int i=2; i<=n; i++) {
            if(isPrime[i]) {
                primeList.add(i);
                if(i > Math.sqrt(n)) continue;
                for(int j=i*i; j<=n; j=j+i) {
                    isPrime[j] = false;
                }
            }
        }

        int answer = 0;
        int lt = 0;
        long sum = 0;
        for(int rt=0; rt<primeList.size(); rt++) {
            sum += primeList.get(rt);
            if(sum == n) {
                answer++;
            }
            while(sum>=n && lt <= rt) {
                sum -= primeList.get(lt++);
                if(sum == n) {
                    answer++;
                }
            }
        }

        System.out.println(answer);

    }
}