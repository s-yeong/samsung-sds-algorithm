import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int[] indegree;
    static int n;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        indegree = new int[n+1];
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // a가 b의 앞에 서야한다. a->b
            indegree[b]++;  // 진입 차수 증가
            graph.get(a).add(b);
        }
        /*
        정점 : 사람
        간선 : 키 비교 결과
         */

        solution();
        System.out.print(sb);
    }
    static void solution() {

        Queue<Integer> Q = new LinkedList<>();

        // 진입차수 0이면, 넣기 (최초 탐색)
        for(int i=1; i<=n; i++) {
            if(indegree[i] == 0) {
                Q.offer(i);
            }
        }

        while(!Q.isEmpty()) {

            int v = Q.poll();
            sb.append(v).append(" ");

            // 인접한 노드들 검사
            for(int nv : graph.get(v)) {
                indegree[nv]--; // 진입 차수 1씩 내리기

                // 진입 차수가 0이 되면, 큐에 넣기
                if(indegree[nv] == 0) Q.offer(nv);
            }
        }

    }
}