import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] child = new int[27][3];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        // 각 노드와 그의 왼쪽 자식 노드, 오른쪽 자식 노드
        // 항상 A가 루트 노드, 자식 노드가 없는 경우 : .
        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node = st.nextToken().charAt(0) - 'A' + 1;
            int node_l = st.nextToken().charAt(0) - 'A' + 1;
            int node_r = st.nextToken().charAt(0) - 'A' + 1;

            if(node_l != ('.' - 'A' + 1)) child[node][1] = node_l;
            if(node_r != ('.' - 'A' + 1)) child[node][2] = node_r;
        }

        // 전위 순회
        preorder(1);
        sb.append("\n");
        // 중위 순회
        inorder(1);
        sb.append("\n");
        // 후위 순회
        postorder(1);
        sb.append("\n");

        // 출력
        System.out.print(sb);
    }

    static void preorder(int node) {
        // 말단 노드이면 return
        if(node == 0) return;
        sb.append((char)(node + 'A' - 1));
        preorder(child[node][1]);
        preorder(child[node][2]);
    }

    static void inorder(int node) {
        // 말단 노드이면 return
        if(node == 0) return;
        inorder(child[node][1]);
        sb.append((char)(node + 'A' - 1));
        inorder(child[node][2]);
    }

    static void postorder(int node) {
        // 말단 노드이면 return
        if(node == 0) return;
        postorder(child[node][1]);
        postorder(child[node][2]);
        sb.append((char)(node + 'A' - 1));
    }
}