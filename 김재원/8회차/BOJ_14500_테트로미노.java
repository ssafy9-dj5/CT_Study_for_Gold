import java.io.*;
import java.util.*;

public class BOJ_14500_테트로미노 {
    static int[][] map;
    static boolean[][] visited;
    static int ans = Integer.MIN_VALUE;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1 ,1};
    static int R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < R; i++){
            for (int j = 0; j < C; j++){
                visited[i][j] = true;
                dfs(0, i, j, map[i][j]);
                for (boolean[] item : visited){
                    Arrays.fill(item, false);
                }
                check(i, j);
            }
        }
        System.out.println(ans);
    }
    public static void dfs(int level, int r, int c, int sum){
        if (level == 3){
            ans = Math.max(sum, ans);
            return;
        }
        for (int i = 0; i < 4; i++){
            int newR = r + dr[i];
            int newC = c + dc[i];
            if (0 <= newR && newR < R && 0 <= newC && newC < C && !visited[newR][newC]){
                visited[newR][newC] = true;
                dfs(level + 1, newR, newC, sum + map[newR][newC]);
                visited[newR][newC] = false;
            }
        }
    }
    public static void check(int r, int c){
        int wings = 0;
        int sum = map[r][c];
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < 4; i++){
            int newR = r + dr[i];
            int newC = c + dc[i];
            if (0 <= newR && newR < R && 0 <= newC && newC < C){
                int val = map[newR][newC];
                sum += val;
                min = Math.min(val, min);
                wings++;
            }
        }
        if (wings < 3) return;
        else if (wings == 4) sum -= min;
        ans = Math.max(sum, ans);
    }
}