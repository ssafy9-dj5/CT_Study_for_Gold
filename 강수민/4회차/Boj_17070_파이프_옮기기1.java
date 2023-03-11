package G5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_17070_파이프_옮기기1 {
    private static int N, map[][], ans;
    private static int[] dr = {0,1,1};
    private static int[] dc = {1,1,0};
    public static void main(String[] args) throws IOException {
        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                   map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // DFS
        ans = 0;
        dfs(0, 1, 0);

        // Output
        System.out.println(ans);
    }

    private static void dfs(int r, int c, int d) {
        if (r == N-1 && c == N-1) {
            ans++;
            return;
        }

        switch (d) {
            case 0: // 기존 가로
                // 가로
                if (check(r+dr[0],c+dc[0]))
                    dfs(r+dr[0], c+dc[0], 0);
                // 대각선
                if (check(r+dr[0],c+dc[0])
                        &&check(r+dr[1],c+dc[1])
                        &&check(r+dr[2],c+dc[2]))
                    dfs(r+dr[1], c+dc[1], 1);
                break;
            case 1: // 기존 대각선
                // 대각선
                if (check(r+dr[0],c+dc[0])
                        &&check(r+dr[1],c+dc[1])
                        &&check(r+dr[2],c+dc[2]))
                    dfs(r+dr[1], c+dc[1], 1);
                // 가로
                if (check(r+dr[0],c+dc[0]))
                    dfs(r+dr[0], c+dc[0], 0);
                // 세로
                if (check(r+dr[2],c+dc[2]))
                    dfs(r+dr[2], c+dc[2], 2);
                break;
            case 2: // 기존 세로
                // 세로
                if (check(r+dr[2],c+dc[2]))
                    dfs(r+dr[2], c+dc[2], 2);
                // 대각선
                if (check(r+dr[0],c+dc[0])
                        &&check(r+dr[1],c+dc[1])
                        &&check(r+dr[2],c+dc[2]))
                    dfs(r+dr[1], c+dc[1], 1);
                break;
        }
    }

    private static boolean check(int r, int c) {
        if (0 <= r && r < N && 0 <= c && c < N && map[r][c] == 0)
            return true;
        return false;
    }
}
