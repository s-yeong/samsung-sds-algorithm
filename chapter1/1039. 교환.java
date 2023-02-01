import java.io.*;
import java.util.*;

class Main {

    static int K;
    static char[] chars;
    static boolean[][] ch;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String str = st.nextToken();
        chars = str.toCharArray();
        K = Integer.parseInt(st.nextToken());
        ch = new boolean[1000001][K+1];
        // M을 정수 N의 자릿수

        // 1 ≤ i < j ≤ M인 i와 j를 고른다
        // i번 위치의 숫자와 j번 위치의 숫자를 바꾼다. 이때, 바꾼 수가 0으로 시작하면 안 된다.
        // 위의 연산을 K번 했을 때, 나올 수 있는 수의 최댓값
        recursion(0);

        if(answer == Integer.MIN_VALUE) System.out.println(-1);
        else System.out.println(answer);
    }

    static int answer = Integer.MIN_VALUE;
    static void recursion(int L) {
        // 방문한 경우, 바꾼 수가 0인 경우
        if(ch[Integer.parseInt(String.valueOf(chars))][L] || chars[0] == '0') return;
        // 안한 경우
        ch[Integer.parseInt(String.valueOf(chars))][L] = true;

        // 연산 K번 했을 때,
        if(L==K) {

            int num = Integer.parseInt(String.valueOf(chars));
            answer = Math.max(answer, num);
        }
        else {
            for(int i=0; i<chars.length; i++) {
                for(int j=i+1; j<chars.length; j++) {
                    // 바꾸기
                    char tmp = chars[i];
                    chars[i] = chars[j];
                    chars[j] = tmp;

                    recursion(L+1);

                    // 원래 대로
                    chars[j] = chars[i];
                    chars[i] = tmp;
                }
            }

        }

    }
}