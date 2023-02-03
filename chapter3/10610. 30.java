import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 1. 3의 배수이냐
        // 2. 0이 있냐

        // 모든 자릿수를 합이 3의 배수이면, 3의 배수
        // 0이 하나라도 있으면 -> 반드시 0이 아닌 숫자가 하나라도 있음
        char[] chars = br.readLine().toCharArray();
        int[] ints = new int[chars.length];

        int sum = 0;
        int cnt = 0;
        for (int i = 0; i < chars.length; i++) {
            ints[i] = Integer.parseInt(String.valueOf(chars[i]));
            sum += ints[i];
            if(ints[i] == 0) cnt++;
        }

        // 30의 배수 존재
        if(sum%3 == 0 && cnt > 0) {
            Arrays.sort(ints);
            StringBuilder sb = new StringBuilder();
            for (int i = ints.length - 1; i >= 0; i--) sb.append(ints[i]);
            System.out.println(sb);
        }
        else {
            System.out.println(-1);
        }

    }
}