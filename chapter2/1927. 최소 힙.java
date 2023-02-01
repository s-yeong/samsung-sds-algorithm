import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> mnq = new PriorityQueue<>();
        for(int i=0; i<n; i++) {
            int x = Integer.parseInt(br.readLine());
            //x가 자연수라면 배열에 x라는 값 넣기
            if(x != 0) mnq.offer(x);
            //x가 0이라면 배열에서 가장 작은 값을 출력하고 그 값을 배열에서 제거
            else {
                if(mnq.isEmpty()) sb.append(0).append("\n");
                else sb.append(mnq.poll()).append("\n");
            }
        }
        System.out.print(sb);
    }
}