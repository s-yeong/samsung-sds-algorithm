import java.io.*;
import java.util.*;

class Main {
    static int N;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        recursion(0);
        System.out.println(count);
    }

    /*
    1. 각 라인별로 어느 라인에 퀸이 들어가 있는지 체크 배열
    2. 오른쪽 대각선 별로 어디에 퀸이 들어가 있는지 체크 배열
    3. 왼쪽 대각선 별로 어디에 퀸이 들어가 있는지 체크 배열
     */
    static boolean[] ch_y = new boolean[16];
    static boolean[] ch_l = new boolean[29];
    static boolean[] ch_r = new boolean[29];

    static int count = 0;
    static void recursion(int x) {

        if(x==N) {
            count++;
            return;
        }
        else {
            for (int i = 0; i < N; i++) {
                if (!ch_y[i] && !ch_l[x + i] && !ch_r[x - i + N]) {
                    ch_y[i] = true;
                    ch_l[x + i] = true;
                    ch_r[x - i + N] = true;
                    recursion(x + 1);
                    ch_y[i] = false;
                    ch_l[x + i] = false;
                    ch_r[x - i + N] = false;
                }
            }
        }

    }
}