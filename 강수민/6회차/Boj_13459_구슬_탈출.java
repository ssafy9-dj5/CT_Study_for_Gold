package G1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_13459_구슬_탈출 {
    private static char[][] map;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        int[] start = new int[4];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = s.charAt(j);
                if (c == 'R') {
                    start[0] = i;
                    start[1] = j;
                    map[i][j] = '.';
                } else if (c == 'B') {
                    start[2] = i;
                    start[3] = j;
                    map[i][j] = '.';
                } else {
                    map[i][j] = c;
                }
            }
        }

        // BFS + Output
        System.out.println(bfs(start));
    }

    private static int bfs(int[] start) {
        Queue<int[]> que = new ArrayDeque<>();
        que.add(start);
        int cnt = 1;
        while (!que.isEmpty() && cnt <= 10) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                int[] points = que.poll();
                for (int j = 0; j < 4; j++) {
                    int[] result = new int[4];      // 굴렸을때 공 위치
                    int ret = go(j, points, result);   // 공 굴려보기
                    if (ret == 1) return 1;         // 정답 찾음
                    else if (ret == 0) {            // 진행은 가능
                        que.add(result);
                    }
                }
            }
            cnt++;
        }
        return 0;
    }

    private static int go(int d, int[] points, int[] result) {
        boolean red = false, blue = false;
        int rx = points[0];
        int ry = points[1];
        int bx = points[2];
        int by = points[3];

//        System.out.println(rx + " " + ry + " " + bx + " " + by + " " + d);

        // 빨간 공 굴리기
        while (map[rx + dx[d]][ry + dy[d]] != '#') {
            rx += dx[d];
            ry += dy[d];
            if (map[rx][ry] == 'O') {
                red = true;
                break;
            }
//            System.out.println(rx + " " + ry);
        }

        // 파란 공 굴리기
        while (map[bx + dx[d]][by + dy[d]] != '#') {
            bx += dx[d];
            by += dy[d];
            if (map[bx][by] == 'O') {
                blue = true;
                break;
            }
        }

        if (blue) return 2;         // 파란공 들어가버림 (진행불가)
        else if (red) return 1;     // 빨간 공만 들어감 (성공)
        else {                      // 둘 다 안들어감 (계속 진행)
            if (rx == bx && ry == by) {  // 두 공이 겹침
                if (first(d, points)) {      // 빨간 공이 빠름
                    bx += dx[(d + 2) % 4];
                    by += dy[(d + 2) % 4];
                } else {                    // 파란 공이 빠름
                    rx += dx[(d + 2) % 4];
                    ry += dy[(d + 2) % 4];
                }
            }
            result[0] = rx;
            result[1] = ry;
            result[2] = bx;
            result[3] = by;
            return 0;
        }
    }

    private static boolean first(int d, int[] points) {
        if (d == 0) {
            return points[0] < points[2];
        } else if (d == 1) {
            return points[1] > points[3];
        } else if (d == 2) {
            return points[0] > points[2];
        } else {
            return points[1] < points[3];
        }
    }
}
