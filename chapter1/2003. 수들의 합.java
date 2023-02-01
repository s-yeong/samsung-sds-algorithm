import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N개의 수로 된 수열
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());

        int count = 0;

        int lt = 0;
        int sum = 0;
        for(int rt=0; rt<N; rt++) {
            sum += A[rt];

            if(sum == M) count++;

            while(sum >= M) {
                sum -= A[lt++];
                if(sum == M) count++;
            }
        }

        System.out.println(count);

    }
}