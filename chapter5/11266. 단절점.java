import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int[] search_order;
    static boolean[] cut_vertex;
    static int order = 0;   // 방문 순서
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>(); // 인접리스트
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken()); // 정점의 개수
        int e = Integer.parseInt(st.nextToken());
        // 트리 상에서 연결된 두 정점

        // 정점 1번부터 v까지
        for (int i = 0; i <= v; i++) graph.add(new ArrayList<>());

        while(e-->0) {
            st = new StringTokenizer(br.readLine());
            // a 정점과 b 정점 연결
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }


        // 탐색 순서 배열
        search_order = new int[v+1];
        // 단절점
        cut_vertex = new boolean[v+1];


        // 주어진 그래프 연결 그래프 아닐 수도 있다 -> 루트 노드 여러개
        for(int i=1; i<=v; i++) {
            if(search_order[i] == 0) {
                dfs(i,true);
            }
        }

        int count = 0;
        for(int i=1; i<=v; i++) {
            if(cut_vertex[i]) {
                count++;
                sb.append(i).append(" ");
            }
        }

        // 답 출력
        if(count != 0) {
            System.out.println(count);
            System.out.print(sb);
        }
        else System.out.println(0);
    }

    static int dfs(int now, boolean isRoot) {

        // 탐색 순서
        search_order[now] = ++order;
        // 자식 트리 수 (루트 노드인 경우 단절점 판단 위해)
        int child = 0;
        // 리턴값 = low (지금 정점 이후에 도달할 수 있는 모든 정점들의 탐색순서 중 가장 작은 값)
        int rtn = order;    // 초기 값은 자신의 order

        for(int next : graph.get(now)) {

            // 첫 방문이면
            if(search_order[next] == 0) {
                child++;

                // 현재 정점의 다음에 방문할 모든 정점에 대해서 도달할수 있는 최소의 Order 순서 (우회로가 있나 찾아보는 것)
                int low = dfs(next, false);

                // 다음 방문할 정점의 순서가 모두 나보다 큰 경우 단절점
                if(!isRoot && search_order[now] <= low) {
                    cut_vertex[now] = true;
                }

                rtn = Math.min(rtn, low);
            }

            // 자식 정점이 이미 방문한 경우
            else {
                rtn = Math.min(rtn, search_order[next]);
            }
        }

        // 루트 -> 나의 Order보다 작은게 나올 수 없기 때문에, 자식노드의 숫자로 판단
        if(isRoot && child >= 2) {
            cut_vertex[now] = true;
        }

        return rtn;
    }

}