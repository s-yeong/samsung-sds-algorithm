import java.io.*;
import java.util.*;

public class Main {
    static class EdgeList implements Comparable<EdgeList> {

        int v1;
        int v2;
        int cost;

        public EdgeList(int v1, int v2, int cost) {
            this.v1 = v1;
            this.v2 = v2;
            this.cost = cost;
        }

        @Override
        public int compareTo(EdgeList o) {
            return this.cost - o.cost;
        }
    }
    static int n,m;
    static ArrayList<EdgeList> list = new ArrayList<>();
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());    // 컴퓨터의 수
        m = Integer.parseInt(br.readLine());    // 연결할 수 있는 선의 수

        // 각 컴퓨터를 연결하는데 필요한 비용이 주어졌을 때 모든 컴퓨터를 연결하는데 필요한 최소비용을 출력
        // => 최소 신장 트리(MST) - 크루스칼 알고리즘

        // union-find 초기화
        group = new int[n + 1];
        for(int i=0; i<=n; i++) group[i] = i;


        // 정점 : 컴퓨터, 간선 : 컴퓨터 연결, 가중치 : 최소 비용
        for(int i=0; i<m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            // a컴퓨터와 b컴퓨터를 연결하는데 비용이 c
            list.add(new EdgeList(a, b, c));
        }

        // 간선 비용순으로 정렬
        Collections.sort(list);
        int cnt = 0;

        for(int i=0; i<m; i++) {
            int f1 = find(list.get(i).v1);
            int f2 = find(list.get(i).v2);

            // 같은 집합 아니면(현재 선택된 간선의 두개 정점이 연결된 상태가 아니라면),
            if(f1 != f2) {
                union(f1, f2);
                answer += list.get(i).cost;
            }

            // 정점-1개 간선 연결시 MST 완성 -> 종료
            if(cnt == n-1) break;
        }

        System.out.println(answer);
    }
    static int[] group;
    static int find(int v) {
        if(v == group[v]) return v;
        else return group[v] = find(group[v]);
    }

    static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        group[fa] = fb;
    }
}