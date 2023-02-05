import java.io.*;
import java.util.*;

public class Main {
    static int n,m;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // n개의 수
        arr = new int[n];
        permu = new int[m];

        //N개의 자연수 중에서 M개를 고른 수열
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
        }
        // 오름차순
        Arrays.sort(arr);

        recur(0);
        for(String s : set) System.out.println(s);
    }
    static int[] permu;
    static boolean[] ch = new boolean[10];
    static LinkedHashSet<String> set = new LinkedHashSet<>();
    static void recur(int L) {
        if(L == m) {
            StringBuilder sb = new StringBuilder();
            for(int x: permu) {
                sb.append(x).append(" ");
            }
            set.add(sb.toString());
        }
        else {
            for(int i=0; i<n; i++) {
                if(!ch[i]) {
                    ch[i] = true;
                    permu[L] = arr[i];
                    recur(L + 1);
                    ch[i] = false;
                }
            }
        }
    }
}