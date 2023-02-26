package S2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_4963_섬의_개수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1}; //상 상우 우 우하 하 좌하 좌 좌상
        int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            if (w == 0 || h == 0) break;

            int[][] map = new int[h][w];
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            // input done.


            // BFS
            int answer = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (map[i][j] == 1) {
//                        print(map);
                        answer++;

                        Queue<Pair> que = new ArrayDeque<>();
                        que.add(new Pair(i, j));
                        map[i][j] += answer;

                        while (!que.isEmpty()) {
                            Pair pair = que.poll();

                            for (int k = 0; k < 8; k++) {
                                int x = pair.x + dx[k];
                                int y = pair.y + dy[k];

                                if (0 <= x && x < h && 0 <= y && y < w && map[x][y] == 1) {
                                    que.add(new Pair(x, y));
                                    map[x][y] += answer;
                                }
                            }
                        }
                    }
                }
            }
            System.out.println(answer);
        }
    }

    private static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void print(int[][] map) {
        System.out.println();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("================================");
    }
}
