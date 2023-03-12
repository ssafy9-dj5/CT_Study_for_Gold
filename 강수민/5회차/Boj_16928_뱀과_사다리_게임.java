package G5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_16928_뱀과_사다리_게임 {
    private static class Game {
        int num, cnt;

        public Game(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] map = new int[101];
        boolean[] visited = new boolean[101];

        for (int i = 0; i <= 100; i++) {
            map[i] = i;
        }

        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }

        Queue<Game> que = new ArrayDeque<>();
        int now = 1;
        int cnt = 0;

        que.offer(new Game(now, cnt));

        while (!que.isEmpty()) {
            Game game = que.poll();
            now = game.num;
            cnt = game.cnt;

            if (now == 100) {
                break;
            }

            visited[now] = true;

            for (int i = 1; i <= 6; i++) {
                if (now + i <= 100 && !visited[now + i]) {
                    que.offer(new Game(map[now + i], cnt + 1));
                }
            }
        }
        System.out.println(cnt);
    }
}
