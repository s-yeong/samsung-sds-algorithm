import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    static long[] weight_diff;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            if(N==0 && M==0) break;

            // union-find
            parent = new int[N+1];
            // self-loop
            for(int i=1; i<=N; i++) parent[i] = i;
            weight_diff = new long[N+1];

            while (M --> 0) {
                st = new StringTokenizer(br.readLine());
                String com = st.nextToken();
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                // 무게 재기
                if (com.equals("!")) {
                    int w = Integer.parseInt(st.nextToken());

                    // b가 a보다 w그램 무겁다
                    // ! 1 2 1 => 2번이 1번보다 1그램 무겁다
                    union(a, b, w);
                }

                // 교수님 질문
                else if (com.equals("?")) {

                    if (find(a) != find(b)) {
                        sb.append("UNKNOWN").append("\n");
                    } else {
                        // b가 a보다 얼마나 무거운지 출력
                        sb.append(weight_diff[b] - weight_diff[a]).append("\n");
                    }

                }
            }
        }
        System.out.print(sb);
    }
    static int find(int v) {
        // self-loop,
        if(v == parent[v]) return v;
        else {
            int parent_idx = find(parent[v]);
            weight_diff[v] += weight_diff[parent[v]];
            return parent[v] = parent_idx;
        }
    }

    static void union(int a, int b, int diff) {

        // 무게가 가벼운 것을 기준으로 합치기
        int fa = find(a);
        int fb = find(b);

        if(fa != fb) {
            parent[fb] = fa;
            weight_diff[fb] = weight_diff[a] - weight_diff[b] + diff;
        }
    }

}