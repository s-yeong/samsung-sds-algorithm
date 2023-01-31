import java.io.*;
import java.util.*;

public class Main {
    static int N,M;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        // 이분 탐색을 위해 정렬
        Arrays.sort(arr);
        /*
        1. 상근이 나무 M미터 필요
        2. 상근이는 절단기에 높이 H를 지정
        2.1 높이가 H보다 큰 나무는 H 위의 부분이 잘림
        2.2 H보다 낮은 나무는 잘리지 X
        3. 절단기에 설정할 수 있는 높이 >= 0
         */

        // 적어도 M미터의 나무를 집에 가져가기 위해서 절단기에 설정할 수 있는 높이의 최댓값
        binarySearch();
        System.out.println(answer);
    }

    static int answer = 0;
    static void binarySearch() {

        // lt와 rt 사이에 무조건 값 존재
        // 3. 절단기에 설정할 수 있는 높이 >= 0
        int lt = 0;
        int rt = arr[N - 1];

        while(lt<=rt) {
            int mid = (lt+rt)/2;

            // mid 값이 가능한지
            long sum = 0;
            for(int x : arr) {
                // 2.2 H보다 낮은 나무는 잘리지 X
                if(x < mid) continue;

                sum += x - mid;
            }

            // 절단기에 설정할 수 있는 높이의 최댓값

            // 답이 가능한 경우
            if(sum >= M) {
                answer = mid;
                // 더 나은 값(최대값) 있는지 찾기
                lt = mid + 1;
            }
            // 값이 안되면 줄이기
            else {
                rt = mid - 1;
            }
        }

    }
}