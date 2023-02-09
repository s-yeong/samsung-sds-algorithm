import java.io.*;
import java.util.*;

public class Main {
    static class Info {
        int node;
        int cost;

        public Info(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }
    static ArrayList<ArrayList<Info>> graph;
    static int[][] parents;
    static int[][] min_distance;
    static int[][] max_distance;
    static int[] depth;
    static int logN;
    static int N;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 정점
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();
        for(int i=0; i<=N; i++) graph.add(new ArrayList<>());

        getLogN();
        depth = new int[N+1];
        parents = new int[logN+1][N+1];
        min_distance = new int[logN+1][N+1];
        max_distance = new int[logN+1][N+1];


        // A와 B사이에 길이가 C인 도로가 있다
        int A,B,C;
        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            graph.get(A).add(new Info(B,C));
            graph.get(B).add(new Info(A,C));
        }

        // 1.
        bfs();
        // 2.
        makeSparseTable();

        // K개의 도시쌍
        int K = Integer.parseInt(br.readLine());

        // 서로 다른 두 자연수 D,E
        int D,E;
        while(K-->0) {
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            // D와 E를 연결하는 경로에서 가장 짧은 도로의 길이와 가장 긴 도로의 길이를 구해서 출력
            getLCA(D,E);
            sb.append(answer_min).append(" ").append(answer_max).append("\n");
        }
        System.out.print(sb);
    }

    public static void getLogN() {
        logN = 0;
        for (int k = 1; k < N; k *= 2) {
            logN++;
        }
    }

    static void bfs() {

        Queue<Integer> Q = new LinkedList<>();
        depth[1] = 1;
        Q.offer(1);

        while(!Q.isEmpty()) {
            int now = Q.poll();

            for(Info next : graph.get(now)) {
                if(depth[next.node] == 0) {
                    depth[next.node] = depth[now] + 1;
                    parents[0][next.node] = now;
                    min_distance[0][next.node] = next.cost;
                    max_distance[0][next.node] = next.cost;
                    Q.offer(next.node);
                }
            }
        }
    }

    static void makeSparseTable() {

        for(int i=1; i<=logN; i++) {
            for(int j=1; j<=N; j++) {
                parents[i][j] = parents[i-1][parents[i-1][j]];
                min_distance[i][j] = Math.min(min_distance[i-1][j], min_distance[i-1][parents[i-1][j]]);
                max_distance[i][j] = Math.max(max_distance[i-1][j], max_distance[i-1][parents[i-1][j]]);
            }
        }
    }

    static int answer_min;
    static int answer_max;
    static int getLCA(int a, int b) {

        // a가 더 깊은 depth
        if(depth[a] < depth[b]) {
            return getLCA(b,a);
        }

        answer_min = Integer.MAX_VALUE;
        answer_max = 0;

        // 1. depth 맞추기
        for(int i=0; i<=logN; i++) {

            if(((depth[a] - depth[b]) & (1 << i)) >= 1) {
                answer_min = Math.min(answer_min, min_distance[i][a]);
                answer_max = Math.max(answer_max, max_distance[i][a]);
                a = parents[i][a];
            }
        }

        // 2. 같은 경우
        if(a==b) return a;

        // 3. 공통조상이 아닌 경우까지 올라감
        for(int i=logN; i>=0; i--) {

            if(parents[i][a] != parents[i][b]) {
                answer_min = Math.min(answer_min, Math.min(min_distance[i][a], min_distance[i][b]));
                answer_max = Math.max(answer_max, Math.max(max_distance[i][a], max_distance[i][b]));

                a = parents[i][a];
                b = parents[i][b];
            }
        }

        answer_min = Math.min(answer_min, Math.min(min_distance[0][a], min_distance[0][b]));
        answer_max = Math.max(answer_max, Math.max(max_distance[0][a], max_distance[0][b]));

        // 한 칸 올려서 LCA 리턴
        return parents[0][a];
    }

}