package G4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_14500_테트로미노 {
    private static int N, M, map[][], s, d, ans, x, y;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // Combination
        for (int i = 0; i < N * M - 4; i++) {
            x = i / M;
            y = i % M;
            visited[x][y] = true;
            comb(i+1, 1, map[x][y]);
            visited[x][y] = false;
        }

        // Output
        System.out.print(ans);
    }

    private static void comb(int start, int cnt, int sum) {
        if (start/5 - x > 4) return;
        if (cnt == 4) {
            if (sum > ans) {
                boolean[][] temp = new boolean[N][M];
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        temp[i][j] = visited[i][j];
                    }
                }
                d = 0;
                dfs(x, y, temp);
                if(d == 4)
                    ans = sum;
            }
            return;
        }

        for (int i = start; i <= N * M - (4 - cnt); i++) {
            visited[i / M][i % M] = true;
            comb(i + 1, cnt + 1, sum + map[i / M][i % M]);
            visited[i / M][i % M] = false;
        }
    }

    private static void dfs(int x, int y, boolean[][] temp) {
        d++;
        temp[x][y] = false;
        for (int i = 0; i < 4; i++) {
            int r = x + dx[i];
            int c = y + dy[i];
            if (0 <= r && r < N && 0 <= c && c < M && temp[r][c]) {
                dfs(r, c, temp);
            }
        }
    }
}
