import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // nCk = n!/(n-k)!k!
        int sum = 1;

        // n!
        for(int i=1; i<=n; i++) {
            sum *= i;
        }

        // (n-k)!
        for(int i=1; i<=n-k; i++) {
            sum /= i;
        }

        // k!
        for(int i=1; i<=k; i++) {
            sum /= i;
        }

        System.out.println(sum);
    }
}