import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        // b/a
        int b1 = Integer.parseInt(st.nextToken());  //분자
        int a1 = Integer.parseInt(st.nextToken());  //분모
        st = new StringTokenizer(br.readLine());
        int b2 = Integer.parseInt(st.nextToken());  //분자
        int a2 = Integer.parseInt(st.nextToken());  //분모

        // 통분
        int a = a1 * a2;    // 분모
        int b = b1 * a2 + b2 * a1;

        int g = gcd(a, b);

        System.out.println(b / g + " " + a / g);

    }

    // a > b
    static int gcd(int a, int b) {
        if(b == 0) return a;
        else return gcd(b, a%b);
    }
}