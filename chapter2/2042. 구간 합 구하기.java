import java.io.*;
import java.util.*;

public class Main {
    static int n,m,k;
    static long[] idxTree = new long[2100000];
    static long[] arr;
    static int firstLeaf;

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        // n: 수의 개수, m: 수의 변경이 일어나는 횟수, k: 구간의 합을 구하는 횟수

        arr = new long[n];
        for(int i=0; i<n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        // 첫번째 리프 노드 찾기
        firstLeaf = 1;
        while(firstLeaf < n) firstLeaf *= 2;

        // 데이터 입력
        // 1. 리프노드
        for(int i=firstLeaf; i<firstLeaf+n; i++) {
            idxTree[i] = arr[i-firstLeaf];
        }
        // 2. 내부 노드
        for(int i=firstLeaf-1; i>=1; i--) {
            idxTree[i] = idxTree[i * 2] + idxTree[i * 2 + 1];
        }

        for(int i=0; i<m+k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());

            // b번째 수를 c로 바꾸기
            if(a==1) {
                int b = Integer.parseInt(st.nextToken());
                long c = Long.parseLong(st.nextToken());

                int x = firstLeaf + b - 1;
                idxTree[x] = c;
                edit(x/2);
            }
            // b번째 수부터 c번째 수까지 합
            else if(a==2){
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                sb.append(sum(1, 1, firstLeaf, b, c)).append("\n");
            }
        }

        System.out.print(sb);
    }

    static long sum (int x, int l, int r, int search_l, int search_r) {

        // 현재 노드가 아예 겹치지 않는지
        if(search_l > r || search_r < l) return 0;

            // 현재 노드가 구간에 완전히 속해 있는지
        else if(search_l <= l && search_r >= r) return idxTree[x];

        // 현재 노드가 구간에 일부 속해 있는지
        return sum(x * 2, l, (l + r) / 2, search_l, search_r) + sum(x * 2 + 1, (l + r) / 2 + 1, r, search_l, search_r);
    }

    static void edit (int x) {
        if(x<=0) return;
        else {
            idxTree[x] = idxTree[x * 2] + idxTree[x * 2 + 1];
            edit(x/2);
        }
    }
}