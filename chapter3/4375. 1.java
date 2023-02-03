import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while(true) {
            String s = br.readLine();
            if (s == null) break;

            int n = Integer.parseInt(s);

            // 2와 5로 나누어 떨어지지 않는 정수 n(1 ≤ n ≤ 10000)가 주어졌을 때,
            // 1로만 이루어진 n의 배수를 찾기

            // 1로 이루어진 n의 배수 중 가장 작은 수의 자리수를 출력
            int cnt = 0;
            long num = 1;
            while(true) {
                num %= n;
                cnt++;
                if(num == 0) break;
                num*=10;
                num+=1;
            }
            sb.append(cnt).append("\n");
        }
        System.out.print(sb);
    }
}