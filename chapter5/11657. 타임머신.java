import java.io.*;
import java.util.*;

public class Main {
    static class Info {
        int from;
        int to;
        int cost;

        public Info(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
    static long[] dis;
    static ArrayList<Info> edge_list;
    static int n,m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dis = new long[n+1]; // 최단 거리
        edge_list = new ArrayList<>();    //간선 리스트
        Arrays.fill(dis, Integer.MAX_VALUE);

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edge_list.add(new Info(a,b,c));
        }

        findShortestPath();

        // 무한히 오래 전으로 되돌릴 수 있다면 첫째 줄에 -1을 출력 (음의 사이클 존재 하면)
        if(findNegativeCycle()) {
            System.out.println(-1);
        }
        else {
            StringBuilder sb = new StringBuilder();
            for(int i=2; i<=n; i++) {
                // 해당 도시로 가는 경로가 없다면 대신 -1을 출력
                if(dis[i] == Integer.MAX_VALUE) sb.append(-1).append("\n");
                else sb.append(dis[i]).append("\n");
            }
            System.out.print(sb);
        }
    }
    static void findShortestPath(){

        // 1번 도시에서 출발해서 나머지 도시로 가는 가장 빠른 시간
        dis[1] = 0;

        for(int i=0; i<n-1; i++) {
            for(int j=0; j<m; j++) {
                Info info = edge_list.get(j);
                if(dis[info.from] != Integer.MAX_VALUE) {
                    if(dis[info.to] > dis[info.from] + info.cost) {
                        dis[info.to] = dis[info.from] + info.cost;
                    }
                }
            }
        }
    }

    static boolean findNegativeCycle() {

        // 갱신되면 음의 사이클을 가진다
        for(int i=0; i<m; i++) {
            Info info = edge_list.get(i);
            if(dis[info.from] != Integer.MAX_VALUE) {
                if(dis[info.to] > dis[info.from] + info.cost) {
                    return true;
                }
            }
        }
        return false;
    }
}