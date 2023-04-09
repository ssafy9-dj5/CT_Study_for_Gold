package G5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_2589_보물섬 {
    private static class Point {
        int r, c, d;

        public Point(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        boolean[][] map = new boolean[H][W];

        for (int i = 0; i < H; i++) {
            String ss = br.readLine();
            for (int j = 0; j < W; j++) {
                if (ss.charAt(j) == 'L') map[i][j] = true;
            }
        }

        // BFS
        int max = 0;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        Queue<Point> que = new ArrayDeque<>();
        boolean[][] visited;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j]) {
                    que.add(new Point(i, j, 0));
                    visited = new boolean[H][W];
                    visited[i][j] = true;
                    while (!que.isEmpty()) {
                        Point point = que.poll();
                        max = Math.max(max, point.d);

                        for (int k = 0; k < 4; k++) {
                            int r = point.r + dx[k];
                            int c = point.c + dy[k];
                            if (0 <= r && r < H && 0 <= c && c < W && !visited[r][c] && map[r][c]) {
                                visited[r][c] = true;
                                que.add(new Point(r, c, point.d + 1));
                            }
                        }
                    }
                }
            }
        }

        // Output
        System.out.print(max);
    }
}
