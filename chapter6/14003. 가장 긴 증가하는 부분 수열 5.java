import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 정렬된 LIS 배열에 i번째에 올 수 있는 숫자
        int[] dp = new int[n+1];

        // i번쨰 숫자가 dp 배열의 어떤 index에 들어갔는지 저장
        int[] index_order = new int[n+1];

        // 실제 LIS 저장하는 배열
        int[] tracking = new int[n+1];

        // LIS 길이
        int length=0;

        // 0. 초기값 셋팅
        length++;
        dp[1] = arr[1];
        index_order[1] = 1;

        // 1. DP + BinarySearch 이용해서 dp 배열을 채워서 LIS 의 길이 찾기
        for(int i=2; i<=n; i++) {
            int search_idx = binarySearch(dp, arr[i], length);
            index_order[i] = search_idx;

            if(search_idx > length) {
                length++;
                dp[length] = arr[i];
            }
            else {
                dp[search_idx] = arr[i];
            }
        }

        int answer = length;

        // 2. LIS 길이에서 내려오면서 실제 LIS 찾기
        for(int i=n; i>=1; i--) {
            if(length == index_order[i]) {
                tracking[length] = arr[i];
                length--;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(answer).append("\n");
        for(int i=1; i<=answer; i++) {
            sb.append(tracking[i]).append(" ");
        }
        System.out.println(sb);
    }

    static int binarySearch(int[] dp, int key, int length) {
        int lt = 1;
        int rt = length;

        while(lt<=rt) {

            int mid = (lt+rt)/2;

            if(dp[mid] > key) {
                rt = mid -1;
            }
            else if(dp[mid] < key) {
                lt = mid+1;
            }
            else return mid;
        }

        return lt;
    }
}