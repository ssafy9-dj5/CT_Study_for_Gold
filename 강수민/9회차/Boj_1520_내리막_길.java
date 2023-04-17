package G3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1520_내리막_길 {
    private static int N, M;
    private static int[][] map, history;
    private static boolean[][] visited;
    private static int[] dx = {-1,0,1,0};
    private static int[] dy = {0,1,0,-1};

    public static void main(String[] args) throws IOException {
        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        history = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                history[i][j] = -1;
            }
        }

        // DFS
        dfs(0, 0);

        // Output
        System.out.print(history[0][0]);
    }
    private static int dfs(int r, int c) {
        if (r == N-1 && c == M-1){
            return 1;
        }
        if (history[r][c] >= 0) {
            return history[r][c];
        }
        else {
            visited[r][c] = true;
            int tmp = 0;
            for (int i = 0; i < 4; i++) {
                int x = r + dx[i];
                int y = c + dy[i];
                if (0 <= x && x < N && 0 <= y && y < M && !visited[x][y] && map[r][c] > map[x][y]) {
                    tmp += dfs(x, y);
                }
            }
            visited[r][c] = false;
            history[r][c] = tmp;
            return tmp;
        }
    }
}
