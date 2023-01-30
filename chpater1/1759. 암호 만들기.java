import java.io.*;
import java.util.*;

class Main {
    static StringBuilder sb = new StringBuilder();
    static int L,C;
    static char[] arr;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        // 최소 한 개의 모음(a, e, i, o, u)과 최소 두 개의 자음으로 구성
        // 오름차순

        arr = new char[C];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<C; i++) {
            // 알파벳 소문자, 중복X
            arr[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(arr);
        answer = new char[L];

        // C개의 알파벳으로 L개의 암호 만들기
        solution(0, 0);
        System.out.print(sb);
    }
    static char[] answer;
    static void solution(int Level, int Start) {

        if(Level==L) {
            String str = "";
            int cnt1 = 0;   // 모음 수
            int cnt2 = 0;   // 자음 수
            for(char x : answer) {
                if (x == 'a' || x == 'e' || x == 'i' || x == 'o' || x == 'u') {
                    cnt1++;
                }
                else cnt2++;
                str += x;
            }
            if(cnt1 >= 1 && cnt2 >=2) sb.append(str).append("\n");
        }
        else {
            for(int i=Start; i<C; i++) {
                answer[Level] = arr[i];
                solution(Level+1,i+1);
            }
        }

    }
}