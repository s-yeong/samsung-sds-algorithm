import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] A;
    static HashMap<Integer, Integer> B;
    static int tree_size, offset;
    static int[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        A = new int[n+1];
        B = new HashMap<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int num = Integer.parseInt(st.nextToken());
            B.put(num, i);
        }

        /*
        1. 해쉬맵을 이용해 값 꺼내오기
        2. 정답 갱신하기 (자신 보다 뒤에있는 구간합 구하기
        3. 트리 업데이트 (자신의 자리에 1 표시)
        4. 1~3 반복
         */

        getTreeSize();
        offset = (tree_size >> 1) - 1;  // tree 의 offset
        tree = new int[tree_size];

        // A[1]의 위치 = map.get(A[1])
        long answer = 0;
        for (int i = 1; i <= n; i++) {
            int idx = B.get(A[i]);
            answer += getSum(idx+1, n);
            updateTree(idx, 1);
        }
        System.out.println(answer);
    }

    static void getTreeSize() {
        tree_size = 1;
        while(tree_size < n) {
            tree_size = tree_size * 2;
        }
        tree_size *= 2;
    }
    static void updateTree(int idx, int num) {
        // idx : 업데이트 위치, num : 업데이트 될 값
        idx = idx + offset;
        tree[idx] += num;

        // 부모 찾기 위해 절반으로 나누면서 root까지 올라감
        for(idx >>= 1; idx!=0; idx >>= 1) {
            tree[idx] = tree[idx*2] + tree[idx*2 + 1];
        }
    }

    static long getSum(int lt, int rt) {
        long sum = 0;

        // tree 배열의 인덱스로 변환
        lt += offset;
        rt += offset;

        while(lt <= rt) {
            // lt 홀수 = lt가 오른쪽 자식
            if(lt%2 == 1) {
                // 바꾸고 한칸 오른쪽으로
                sum += tree[lt];
                lt++;
            }
            // rt가 짝수 = rt가 왼쪽 자식
            if(rt%2 == 0) {
                // 바꾸고 한칸 왼쪽으로
                sum += tree[rt];
                rt--;
            }
            lt >>= 1;   // lt = lt/2
            rt >>= 1;   // rt = rt/2
        }
        return sum;
    }

}