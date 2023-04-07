package G4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_9019_DSLR {
    private static class DSLR {
        String history;
        int num;

        public DSLR(int num, String history) {
            this.num = num;
            this.history = history;
        }
    }

    private static final int MAX = 10_000;

    public static void main(String[] args) throws IOException {
        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            boolean[] visited = new boolean[MAX];

            // BFS
            Queue<DSLR> que = new ArrayDeque<>();
            que.offer(new DSLR(A, ""));

            while (!que.isEmpty()) {
                DSLR dslr = que.poll();
                int num = dslr.num;
                String history = dslr.history;

                // B발견하면 종료
                if (num == B) {
                    sb.append(history).append('\n');
                    break;
                }

                if (!visited[num]) {
                    visited[num] = true;

                    // D 연산
                    int temp = (num * 2) % MAX;
                    if (!visited[temp]) {
                        que.offer(new DSLR(temp, history + 'D'));
                    }

                    // S 연산
                    temp = num == 0 ? 9999 : num - 1;
                    if (!visited[temp]) {
                        que.offer(new DSLR(temp, history + 'S'));
                    }

                    // L 연산
                    temp = (num * 10) % 10000 + num / 1000;
                    if (!visited[temp]) {
                        que.offer(new DSLR(temp, history + 'L'));
                    }

                    // R 연산
                    temp = num / 10 + num % 10 * 1000;
                    if (!visited[temp]) {
                        que.offer(new DSLR(temp, history + 'R'));
                    }
                }
            }
        }
        // Output
        System.out.println(sb);
    }
}
