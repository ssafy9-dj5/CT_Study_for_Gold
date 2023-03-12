package day0312;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_16928_뱀과사다리게임 {
    static int cnt[] = new int[101];
    static int map[] = new int[101];
    static boolean visited[] = new boolean[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n + m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a] = b;
        }

        bfs();
    }

    static void bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);
        cnt[1] = 0;
        visited[1] = true;

        while (!q.isEmpty()) {
            int now = q.poll();
            if (now == 100) {
                System.out.println(cnt[now]);
                return;
            }

            for (int i = 1; i <= 6; i++) {
                int next = now + i;
                if (next <= 100 && !visited[next]) {
                    visited[next] = true;

                    if (map[next] > 0) {
                        if (!visited[map[next]]) {
                            q.offer(map[next]);
                            visited[map[next]] = true;
                            cnt[map[next]] = cnt[now] + 1;
                        }
                    } else {
                        q.offer(next);
                        cnt[next] = cnt[now] + 1;
                    }

                }

            }
        }
    }

}