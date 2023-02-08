import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Info>> graph = new ArrayList<>();
    static class Info implements Comparable<Info>{
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
    static int[] distance;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        // 모든 정점 1부터 V까지
        for (int i = 0; i <= V; i++) graph.add(new ArrayList<>());
        // 시작 정점의 번호
        int k = Integer.parseInt(br.readLine());
        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            // u에서 v로 가는 가중치 w인 간선
            graph.get(u).add(new Info(v,w));
        }

        PriorityQueue<Info> pQ = new PriorityQueue<>();
        distance = new int[V+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[k] = 0;



        pQ.offer(new Info(k,0));

        while(!pQ.isEmpty()) {

            Info now = pQ.poll();
            if(now.distance > distance[now.node]) continue;

            for (Info next : graph.get(now.node)) {
                if(distance[next.node] > distance[now.node] + next.distance) {
                    distance[next.node] = distance[now.node] + next.distance;
                    pQ.offer(new Info(next.node, distance[next.node]));
                }
            }
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=V; i++) {
            if(distance[i] == Integer.MAX_VALUE) sb.append("INF").append("\n");
            else sb.append(distance[i]).append("\n");
        }

        System.out.print(sb);
    }
}