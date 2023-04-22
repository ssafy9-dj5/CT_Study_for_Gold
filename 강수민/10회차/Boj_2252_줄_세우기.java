package G3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_2252_줄_세우기 {
    public static void main(String[] args) throws IOException {
        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] in_cnt = new int[N + 1];
        ArrayList<Integer>[] out_list = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            out_list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int out = Integer.parseInt(st.nextToken());
            int in = Integer.parseInt(st.nextToken());
            in_cnt[in]++;
            out_list[out].add(in);
        }

        // BFS
        Queue<Integer> que = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (in_cnt[i] == 0)
                que.add(i);
        }

        StringBuilder sb = new StringBuilder();

        while (!que.isEmpty()) {
            int n = que.poll();
            sb.append(n).append(' ');

            for (int i : out_list[n]) {
                if (--in_cnt[i] == 0)
                    que.add(i);
            }
        }

        // Output
        sb.deleteCharAt(sb.length() - 1);
        System.out.print(sb);
    }
}
