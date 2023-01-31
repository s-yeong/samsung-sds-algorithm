import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        /*
        1. N줄에 0 이상 9 이하의 숫자가 "세 개"씩
         */
        int min1, min2, min3;
        int max1, max2, max3;

        int N = Integer.parseInt(br.readLine());
        // 첫줄 초기화
        StringTokenizer st = new StringTokenizer(br.readLine());
        min1 = max1 = Integer.parseInt(st.nextToken());
        min2 = max2 = Integer.parseInt(st.nextToken());
        min3 = max3 = Integer.parseInt(st.nextToken());

        for(int i=1; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            int num3 = Integer.parseInt(st.nextToken());

            // num1의 최소, 최대
            int t_min1 = Math.min(min1,min2);
            int t_max1 = Math.max(max1,max2);

            // num2의 최소, 최대
            int t_min2 = Math.min(Math.min(min1,min2),min3);
            int t_max2 = Math.max(Math.max(max1,max2),max3);

            // num3의 최소, 최대
            int t_min3 = Math.min(min2,min3);
            int t_max3 = Math.max(max2,max3);

            // 넣기
            min1 = num1 + t_min1;
            max1 = num1 + t_max1;

            min2 = num2 + t_min2;
            max2 = num2 + t_max2;

            min3 = num3 + t_min3;
            max3 = num3 + t_max3;
        }

        int max = Math.max(Math.max(max1,max2),max3);
        int min = Math.min(Math.min(min1,min2),min3);
        System.out.print(max + " " + min);
    }
}