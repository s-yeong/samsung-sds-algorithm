import java.io.*;
import java.util.*;

public class Main {
    static int n,m;
    static int[] parent;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n + 1];
        // 초기화
        for(int i=0; i<=n; i++) {
            parent[i] = i;
        }

        while(m-->0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            // 합집합,
            if(a==0) {
                union(b,c);
            }
            // 두 원소가 같은 집합에 포함되어 있는지
            else {
                int fb = find(b);
                int fc = find(c);
                if(fb == fc) sb.append("yes").append("\n");
                else sb.append("no").append("\n");
            }
        }
        System.out.print(sb);
    }

    static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        parent[fa] = fb;
    }

    static int find(int v) {
        // self-loop,
        if(v== parent[v]) {
            return v;
        }
        else return parent[v] = find(parent[v]);
    }
}