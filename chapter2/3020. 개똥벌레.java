import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());


        int[] height = new int[h+1];
        for (int i = 0; i < n; i++) {
            int size = Integer.parseInt(br.readLine());
            // 석순
            if(i%2 == 0) {
                height[1] += 1;
                height[size+1] -= 1;
            }
            // 종유석
            if(i%2 == 1) {
                height[h-size+1] += 1;
            }
        }

        // 누적합 구하기
        int[] sum = new int[h + 1];
        for(int i=1; i<=h; i++) {
            sum[i] = sum[i-1] + height[i];
        }

        int answer_min = 200000;
        int answer_count = 1;

        for(int i=1; i<=h; i++) {
            if(answer_min > sum[i]) {
                answer_min = sum[i];
                answer_count = 1;
            }
            else if(answer_min == sum[i]) answer_count++;
        }

        System.out.println(answer_min + " " + answer_count);
    }
}