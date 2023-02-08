import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int[][] parents;  // sparse table
    static int[] depth; // 깊이
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>(); // 인접리스트
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        /*
        Sparse Table을 사용하여, LCA를 구하는 시간 복잡도를 logN으로 만들기
         */
        parents = new int[18][100001];   // k=17, n=100,000
        depth = new int[100001];
        int n = Integer.parseInt(br.readLine()); // 노드의 개수
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        // 트리 상에서 연결된 두 정점
        for(int i=0; i<n-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        BFS();

        // spase table 만들기
        for(int i=1; i<=17; i++) {
            for(int j=1; j<=n; j++) {
                // parents[i][j] : 정점 j의 i(=2^k) 번째 조상 정점의 번호
                // j의 i번째 조상 = j의 i-1번째 조상의 i-1번째 조상 (i=2^k)
                parents[i][j] = parents[i-1][parents[i-1][j]];
            }
        }

        // LCA 구하기
        int m = Integer.parseInt(br.readLine());
        while(m-->0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // 두 노드의 가장 가까운 공통 조상 => LSA
            sb.append(getLCA(a, b)).append("\n");
        }
        System.out.print(sb);
    }

    static void BFS() {
        Queue<Integer> Q = new LinkedList<>();
        // 루트는 1번, 깊이 1
        Q.offer(1);
        depth[1] = 1;
        while(!Q.isEmpty()) {
            int v = Q.poll();
            for(int nv : graph.get(v)) {
                if(depth[nv] == 0) {
                    depth[nv] = depth[v] + 1;
                    parents[0][nv] = v;
                    Q.offer(nv);
                }
            }
        }
    }

    static int getLCA(int a, int b) {


        // 더 깊이 있는 정점 a를 기준으로,
        if(depth[a] < depth[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        // 1. depth 맞추기
        for(int i=0; i<=17; i++) {
            // a와 b가 19칸 차이 난다고 했을 떄, 19 = 16 + 2 + 1 = 10011(2)
            // 1칸+2칸+16칸 올라감 (1일때는 자리수에 맞춰 올려준다)
            if(((depth[a]-depth[b]) & (1 << i)) >= 1) { // i를 왼쪽으로 한칸씩 민다
                a = parents[i][a];
            }
        }

        // 맞췄으면 같은지 검사
        if(a==b) {
            return a;
        }

        // 2. 공통조상이 아닐 때까지 부모를 따라 올라간다
        // 최종적으로는 LCA 바로 밑칸까지 올라간다
        for(int i = 17; i>=0; i--) {
            // 공통 조상이면 (parents[i][a] == parents[i][b]) -> 절반 줄이기 (안올림)

            // 공통 조상이 아니면, 이 때 올려준다
            if(parents[i][a] != parents[i][b]) {
                a = parents[i][a];
                b = parents[i][b];
            }
        }

        // 마지막으로 한칸 올리면, LCA
        return parents[0][a];
    }
}