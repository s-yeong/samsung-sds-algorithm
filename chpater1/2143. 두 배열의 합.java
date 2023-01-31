import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        /*
         1. A의 부 배열의 합에 B의 부 배열의 합을 더해서 T가 되는 모든 부 배열 쌍의 개수
         => 연속된 배열의 합을 구했을 때 T가 되는 경우의 수 구하기
         */
        long answer = 0;
        int T = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) A[i] = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(br.readLine());
        int[] B = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) B[i] = Integer.parseInt(st.nextToken());

        int[] subSumA = new int[(n * (n + 1)) / 2];
        int idx = 0;
        // A에서 만들 수 있는 모든 부 배열의 합
        for(int i=0; i<n; i++) {
            for(int j=i; j<n; j++) {
                // i부터 j까지의 합
                subSumA[idx++] = subSum(A, i, j);
            }
        }

        int[] subSumB = new int[(m * (m + 1)) / 2];
        idx = 0;
        // B에서 만들 수 있는 모든 부 배열의 합
        for(int i=0; i<m; i++) {
            for(int j=i; j<m; j++) {
                // i부터 j까지의 합
                subSumB[idx++] = subSum(B, i, j);
            }
        }

        // 투 포인터위해 정렬
        Arrays.sort(subSumA);
        Arrays.sort(subSumB);

        // 중복 개수 세기
        HashMap<Integer, Integer> countMapA = new HashMap<>();
        HashMap<Integer, Integer> countMapB = new HashMap<>();
        for(int x : subSumA) countMapA.put(x, countMapA.getOrDefault(x,0) + 1);
        for(int x : subSumB) countMapB.put(x, countMapB.getOrDefault(x,0) + 1);

        // 투포인터
        int pA = 0;
        int pB = subSumB.length-1;

        while(pA<subSumA.length && pB>=0) {

            int sum = subSumA[pA] + subSumB[pB];

            // 원하는 값이면, 개수 체크
            if(sum == T) {
                long countA = countMapA.get(subSumA[pA]);
                long countB = countMapB.get(subSumB[pB]);
                answer += countA*countB;

                // 중복 피하기 위해
                while(pA+1<subSumA.length && subSumA[pA+1] == subSumA[pA]) pA++;
                while(pB-1>=0 && subSumB[pB-1] == subSumB[pB]) pB--;

                pA++;
                pB--;
            }

            // 값이크면, B 줄이기
            else if(sum > T) pB--;

            // 값이 작으면, A 늘리기
            else pA++;
        }

        System.out.println(answer);

    }

    static int subSum(int[] tmp, int i, int j) {
        int sum = 0;
        for(int x=i; x<=j; x++) {
            sum += tmp[x];
        }
        return sum;
    }
}