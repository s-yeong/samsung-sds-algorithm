import java.io.*;
import java.util.*;

public class Main {
    static int[][] matrix; // 인접 행렬
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        matrix = new int[n+1][n+1];

        for(int i=0; i<m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 버스의 시작 도시, 도착 도시, 비용
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            // 시작 도시와 도착 도시를 연결하는 노선은 하나가 아니기 때문에, 최소값 넣기
            if(matrix[a][b] == 0 || matrix[a][b] > c) {
                matrix[a][b] = c;
            }
        }

        // 경유지
        for(int i=1; i<=n; i++) {
            // 전체 쌍
            for(int j=1; j<=n; j++) {
                for(int k=1; k<=n; k++) {
                    if(j!=k && matrix[j][i] !=0 && matrix[i][k] !=0) {
                        if (matrix[j][k] == 0 || matrix[j][k] > matrix[j][i] + matrix[i][k]) {
                            matrix[j][k] = matrix[j][i] + matrix[i][k];
                        }
                    }
                }
            }
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                sb.append(matrix[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);

    }
}