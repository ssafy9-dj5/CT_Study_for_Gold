package G4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj_2458_키_순서 {
    private static int N, big, small;
    private static boolean[] visit;
    private static ArrayList<Integer>[][] edge;

    public static void main(String[] args) throws IOException {
        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        edge = new ArrayList[2][N + 1];
        for (int i = 0; i < 2; i++) {
            for (int j = 1; j <= N; j++) {
                edge[i][j] = new ArrayList<>();
            }
        }

        int M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edge[0][a].add(b);
            edge[1][b].add(a);
        }

        // DFS
        int ans = 0;

        for (int i = 1; i <= N; i++) {
            // 큰 쪽 확인
            big = 0;
            visit = new boolean[N + 1];
            dfs_big(i);

            // 작은 쪽 확인
            small = 0;
            visit = new boolean[N + 1];
            dfs_small(i);
            if (big + small == N + 1) {
//                System.out.println("=========================");
//                System.out.println(i + " " + big + " " + small);
                ans++;
            }
        }
        System.out.print(ans);
    }

    private static void dfs_big(int i) {
        visit[i] = true;

        big++;
        for (int next : edge[0][i]) {
            if (!visit[next]) {
                dfs_big(next);
            }
        }
    }

    private static void dfs_small(int i) {
        visit[i] = true;
        small++;
        for (int next : edge[1][i]) {
            if (!visit[next]) {
                dfs_small(next);
            }
        }
    }
}