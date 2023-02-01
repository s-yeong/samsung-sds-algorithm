import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        /*
        1. 10,000 이하의 자연수로 이루어진 길이 N짜리 수열
        2. "연속된 수"들의 부분합 중 그 합이 S이상되는 것 중 가장 짧은 것의 길이
         */

        int length = Integer.MAX_VALUE;
        int lt = 0;
        long sum = 0;
        for(int rt = 0; rt<N; rt++) {
            sum += arr[rt];

            // S 값을 넘어가면 lt 줄이면서 최소값 확인
            while(sum >= S && lt<=rt) {
                length = Math.min(length, rt - lt + 1);
                sum -= arr[lt++];
            }
        }

        // 답 불가능하면,
        if(length == Integer.MAX_VALUE) System.out.println(0);
        else System.out.println(length);

    }
}