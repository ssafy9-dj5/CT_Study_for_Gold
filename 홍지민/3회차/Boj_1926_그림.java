package day0226;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1926_그림 {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static boolean[][] used;
    static int[][] map;
    static int n, m,cnt, width, max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        used = new boolean[n][m];
        cnt = width = 0;
        max = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(map[i][j] == 1 && !used[i][j]){
                    cnt++;
                    dfs(i, j);
                    max = Math.max(max, width);
                    width = 0;
                }
            }
        }


        System.out.println(cnt);
        System.out.println(max);
    }

    private static void dfs(int r, int c) {
        width++;
        used[r][c] = true;

        for(int d=0; d<4; d++){
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(nr >= 0 && nc >=0 && nr < n && nc < m && map[nr][nc] == 1 && !used[nr][nc]){
                dfs(nr, nc);
            }
        }

    }
}

