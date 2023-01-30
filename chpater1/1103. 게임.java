import java.io.*;
import java.util.*;

class Main {
    static int N,M;
    static int[][] board;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        // 1부터 9까지의 자연수 또는 H(0으로 선언)
        for(int i=0; i<N; i++) {
            String s = br.readLine();
            for(int j=0; j<M; j++) {
                char x = s.charAt(j);
                if(x != 'H') board[i][j] = Integer.parseInt(String.valueOf(x));
            }
        }
        ch = new boolean[N][M];
        dis = new int[N][M];

        // 형택이가 최대 몇 번 동전을 움직일 수 있는지
        ch[0][0] = true;
        DFS(0, 0, 1);
        System.out.println(answer);
    }

    // 상 하 좌 우
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int answer = Integer.MIN_VALUE;
    static boolean[][] ch;
    static int[][] dis;
    static void DFS(int x, int y, int count) {

        if(dis[y][x] >= count) return;
        dis[y][x] = count;

        // 숫자 X
        int X = board[y][x];
        for(int i=0; i<4; i++) {
            int nx = x + X * dx[i];
            int ny = y + X * dy[i];

            // 바깥으로 빠져나가지 않고, 구멍에 안빠졌으면
            if(nx>=0 && ny>=0 && nx<M && ny<N && board[ny][nx] != 0) {
                if(!ch[ny][nx]) {
                    ch[ny][nx] = true;
                    DFS(nx,ny,count+1);
                    ch[ny][nx] = false;
                }
                else {
                    System.out.println(-1);
                    System.exit(0);
                }
            }
            // 게임이 종료되면
            else answer = Math.max(answer, count);
        }

    }
}