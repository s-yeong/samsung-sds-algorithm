import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        /*
         정수로 이루어진 크기가 같은 배열 A, B, C, D
         A[a], B[b], C[c], D[d]의 합이 0인 쌍의 개수
         */
        long answer = 0;

        int n = Integer.parseInt(br.readLine());
        long[] A = new long[n];
        long[] B = new long[n];
        long[] C = new long[n];
        long[] D = new long[n];

        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[i] = Long.parseLong(st.nextToken());
            B[i] = Long.parseLong((st.nextToken()));
            C[i] = Long.parseLong((st.nextToken()));
            D[i] = Long.parseLong((st.nextToken()));
        }

        long[] sum_AB = new long[n * n];
        long[] sum_CD = new long[n * n];

        // A B 합, C D 합
        int idx_AB = 0;
        int idx_CD = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                sum_AB[idx_AB++] = A[i] + B[j];
                sum_CD[idx_CD++] = C[i] + D[j];
            }
        }

        // 투포인터 위해 정렬
        Arrays.sort(sum_AB);
        Arrays.sort(sum_CD);

        // 투포인터
        int p1 = 0;
        int p2 = sum_CD.length-1;

        while(p1< sum_AB.length && p2 >= 0) {

            long sum = sum_AB[p1] + sum_CD[p2];

            if(sum == 0) {

                // 중복 세기
                long cnt1 = 1;
                long cnt2 = 1;
                while(p1+1<sum_AB.length && sum_AB[p1 +1] == sum_AB[p1]) {
                    cnt1++;
                    p1++;
                }
                while(p2-1>=0 && sum_CD[p2 - 1] == sum_CD[p2]) {
                    cnt2++;
                    p2--;
                }
                answer += cnt1 * cnt2;
                p1++;
                p2--;
            }

            // 크면 p2 감소
            else if(sum > 0) p2--;
            // 작으면 p1 증가
            else p1++;
        }
        System.out.println(answer);

    }
}