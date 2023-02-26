package S1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_1926_그림 {
    public static void main(String[] args) throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        boolean[][] arr = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken()) != 0;
            }
        }

        // bfs
        int cnt = 0, max = 0;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        Queue<Pair> que = null;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j]) {
                    int temp = 0;
                    cnt++;
                    que = new ArrayDeque<>();
                    que.add(new Pair(i,j));
                    arr[i][j] = false;

                    while (!que.isEmpty()) {
                        temp++;
                        Pair pair = que.poll();
                        for (int k = 0; k < 4; k++) {
                            int r = pair.r+dx[k];
                            int c = pair.c+dy[k];

                            if (0<=r&&r<n && 0<=c && c<m&&arr[r][c]){
                                arr[r][c] = false;
                                que.offer(new Pair(r,c));
                            }
                        }
                    }
                    max = Math.max(max, temp);
                }
            }
        }
        System.out.println(cnt);
        System.out.println(max);
    }

    private static class Pair{
        int r, c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
