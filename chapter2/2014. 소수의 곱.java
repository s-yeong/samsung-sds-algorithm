import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        // K개의 소수 오름차순

        // 소수의 곱을 나열했을 때 N번쨰 오는 것 출력
        // 소수를 선택시, 같은 수 여러 번 선택 가능, 주어지는 수 자체도 포함

        long[] arr = new long[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) arr[i] = Long.parseLong(st.nextToken());

        PriorityQueue<Long> pq = new PriorityQueue<>();
        // arr 배열의 모든 값들 pq에 넣기
        for (int i = 0; i < K; i++) pq.offer(arr[i]);

        long tmp = 0;
        // N번쨰 소수의 수 구하기
        for(int i=0; i<N; i++) {
            long num = pq.poll();

            // 중복제거
            while(tmp == num) num = pq.poll();

            // 이전값
            tmp = num;

            for(int j=0; j<K; j++) {
                if(num*arr[j] <= Integer.MAX_VALUE) pq.offer(num * arr[j]);

                // 중복 피하기 위해,
                if(num%arr[j] == 0) break;
            }
        }
        System.out.println(tmp);
    }
}