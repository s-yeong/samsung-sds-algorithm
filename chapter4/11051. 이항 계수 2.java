import java.io.*;
import java.util.*;

public class Main {
    static int n,k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        // nCk
        int[][] paskal = new int[1001][1001];

        paskal[0][0] = 1;
        for(int i=1; i<=n; i++) {
            paskal[i][0] = 1;
            for(int j=1; j<=i; j++) {
                paskal[i][j] = (paskal[i-1][j-1] + paskal[i-1][j]) % 10007;
            }
        }

        System.out.println(paskal[n][k]);

    }
}