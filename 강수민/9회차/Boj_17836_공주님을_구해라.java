package G5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_17836_공주님을_구해라 {
    private static class Point{
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // BFS
        int[] dx = {-1,0,1,0};
        int[] dy = {0,1,0,-1};
        boolean[][] visited = new boolean[N][M];
        Queue<Point> que = new ArrayDeque<>();
        que.add(new Point(0, 0));
        visited[0][0] = true;

        int ans = Integer.MAX_VALUE;
        int cnt = 0;
        while(!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                Point point = que.poll();
                if (point.r == N-1 && point.c == M-1) {
                    que.clear();
                    ans = Math.min(ans, cnt);
                    break;
                }

                for (int j = 0; j < 4; j++) {
                    int r = point.r + dx[j];
                    int c = point.c + dy[j];
                    if (0 <= r && r < N && 0<= c && c < M && !visited[r][c]) {
                        if (map[r][c] == 0) {
                            visited[r][c] = true;
                            que.add(new Point(r,c));
                        }
                        else if (map[r][c] == 2) {
                            visited[r][c] = true;
                            int tmp = Math.abs(N - 1 - r) + Math.abs(M - 1 - c) + cnt + 1;
                            ans = tmp;
                        }
                    }
                }
            }
            cnt++;
        }

        // Output
        if (ans > T) {
            System.out.print("Fail");
        }
        else
            System.out.print(ans);
    }
}