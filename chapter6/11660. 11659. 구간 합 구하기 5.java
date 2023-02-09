import java.io.*;
import java.util.*;

public class Main {
    static class Info implements Comparable<Info> {

        int node;
        int distance;

        public Info(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }

        @Override
        public int compareTo(Info o) {
            return this.distance - o.distance;
        }
    }

    static ArrayList<ArrayList<Info>> graph;
    // 최단 경로를 트랙킹할 수 있는 리스트 배열
    static ArrayList<ArrayList<Integer>> tracking;
    static int S,D;
    static boolean[][] isShortest;
    static int[] distance;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());   // 정점
            int M = Integer.parseInt(st.nextToken());   // 간선

            // 종료
            if(N==0 && M==0) break;

            graph = new ArrayList<>();
            tracking = new ArrayList<>();
            // 0번부터 N-1번 까지
            for (int i = 0; i < N; i++) graph.add(new ArrayList<>());
            for (int i = 0; i < N; i++) tracking.add(new ArrayList<>());
            isShortest = new boolean[N][N]; // 최단 경로 간선에 표시하는 배열

            // 시작점 S와 도착점 D
            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            int U,V,P;
            for (int i = 0; i < M; i++) {
                // 도로의 정보 U, V, P (U에서 V로 가는 도로의 길이 P)
                st = new StringTokenizer(br.readLine());
                U = Integer.parseInt(st.nextToken());
                V = Integer.parseInt(st.nextToken());
                P = Integer.parseInt(st.nextToken());
                graph.get(U).add(new Info(V, P));
            }

        /*
        1. 다익스트라 수행
        2. 모든 최단경로 찾아서 속하는 모든 간선들에 flag(못쓰게)
        3. 다익스트라 수행 -> 거의 최단 경로
         */

            // 1. 다익스트라 수행
            dijkstra(N);

            // 2. 모든 최단경로 찾아서 속하는 모든 간선들에 flag(못쓰게)
            findShortestEdge(D);

            // 3. 다익스트라 수행 -> 거의 최단 경로
            dijkstra(N);

            if(distance[D] == Integer.MAX_VALUE) sb.append("-1").append("\n");
            else sb.append(distance[D]).append("\n");
        }
        System.out.print(sb);
    }
    static void dijkstra(int N) {

        PriorityQueue<Info> pQ = new PriorityQueue<>();
        pQ.offer(new Info(S,0));
        distance = new int[N];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[S] = 0;

        while(!pQ.isEmpty()) {

            Info now = pQ.poll();

            // 최소값 보다 크면 pass
            if(now.distance > distance[now.node]) continue;

            for(Info next : graph.get(now.node)) {

                // 최단 경로면 pass
                if(isShortest[now.node][next.node]) continue;

                if(distance[next.node] == distance[now.node] + next.distance) {
                    tracking.get(next.node).add(now.node);
                }

                // 최소값이면,
                if(distance[next.node] > distance[now.node] + next.distance) {

                    // 업데이트
                    tracking.get(next.node).clear();
                    tracking.get(next.node).add(now.node);

                    distance[next.node] = distance[now.node] + next.distance;
                    pQ.offer(new Info(next.node, distance[next.node]));
                }

            }
        }
    }

    static void findShortestEdge(int now) {

        if(now == S) return;

        // 반대로 -> Start 지점까지
        for(int next : tracking.get(now)) {
            if(!isShortest[next][now]) {
                isShortest[next][now] = true;
                findShortestEdge(next);
            }
        }
    }

}