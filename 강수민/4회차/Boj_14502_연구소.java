package G4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_14502_연구소 {
    private static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static int N, M, ans, cnt = -3;
    private static int[][] map, copy;
    private static Queue<Point> temp;
    private static ArrayList<Point> virus;
    private static int[] dr = {-1, 0, 1, 0};
    private static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        copy = new int[N][M];
        virus = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2)
                    virus.add(new Point(i, j));
                else if (map[i][j] == 0)
                    cnt++;
            }
        }

        // Combination
        comb(0, 0);

        // Output
        System.out.println(ans);
    }

    private static void comb(int cnt, int now) {
        if (cnt == 3) {
            ans = Math.max(ans, bfs());
            return;
        }

        for (int i = now; i < N * M; i++) {
            int r = i / M;
            int c = i % M;
            if (map[r][c] == 0) {
                map[r][c] = 1;
                comb(cnt + 1, i + 1);
                map[r][c] = 0;
            }
        }
    }

    private static int bfs() {
        deepCopy();

        temp = new ArrayDeque<>();
        for (Point p : virus) {
            temp.offer(p);
        }

        int add_virus = 0;
        while (!temp.isEmpty()) {
            Point point = temp.poll();

            for (int i = 0; i < dr.length; i++) {
                int r = point.r + dr[i];
                int c = point.c + dc[i];

                if (0<=r&&r<N&&0<=c&&c<M&&copy[r][c] == 0) {
                    copy[r][c] =2;
                    add_virus++;
                    temp.offer(new Point(r,c));
                }
            }
        }
        return cnt-add_virus;
    }

    private static void deepCopy() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copy[i][j] = map[i][j];
            }
        }
    }
}
