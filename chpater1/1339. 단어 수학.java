import java.io.*;
import java.util.*;

class Main {
    static int answer = Integer.MIN_VALUE;
    static String[] strArr;
    static ArrayList<Character> list = new ArrayList<>();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        strArr = new String[N];
        for(int i=0; i<N; i++) {
            strArr[i] = br.readLine();
            for(char x : strArr[i].toCharArray()) {
                if(!list.contains(x)) list.add(x);
            }
        }
        // 10개의 숫자로 list.size 개의 알파벳 순열
        permu = new int[list.size()];
        alphabet = new int['Z' - 'A' + 1];
        recursion(0);
        System.out.println(answer);
    }
    static int[] permu;
    static int[] alphabet;
    static boolean[] ch = new boolean[10];
    static void recursion(int L) {

        if(L==list.size()) {
            for(int i=0; i<list.size(); i++) {
                char x = list.get(i);
                alphabet[x - 'A'] = permu[i];
            }
            int sum = 0;
            for(String str : strArr) {
                StringBuilder sb = new StringBuilder();
                for(char x : str.toCharArray()) {
                    sb.append(alphabet[x - 'A']);
                }
                sum += Integer.parseInt(sb.toString());
            }
            answer = Math.max(answer, sum);
        }
        else {
            for(int i=0; i<=9; i++) {
                if(!ch[i]) {
                    ch[i] = true;
                    permu[L] = i;    // L번쨰 인덱스에 해당하는 알파벳에 i번 숫자 넣기
                    recursion(L + 1);
                    ch[i] = false;
                }
            }
        }
    }
}