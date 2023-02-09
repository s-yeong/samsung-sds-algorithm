import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];
        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<=i; j++) {
                int num = Integer.parseInt(st.nextToken());
                arr[i][j] = num;
            }

            if(i==0) continue;

            // 맨 왼쪽
            arr[i][0] += arr[i-1][0];

            for(int j=1; j<=i-1; j++) {
                arr[i][j] += Math.max(arr[i-1][j-1],arr[i-1][j]);
            }

            // 맨 오르쪽
            arr[i][i] += arr[i-1][i-1];
        }

        int max = 0;
        for(int i=0; i<n; i++) {
            max = Math.max(max,arr[n-1][i]);
        }
        System.out.println(max);
    }
}