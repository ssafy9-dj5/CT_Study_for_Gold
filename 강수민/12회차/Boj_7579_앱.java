package G3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_7579_앱 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] app = new int[N][2];

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            app[i][0] = Integer.parseInt(st1.nextToken());
            app[i][1] = Integer.parseInt(st2.nextToken());
        }

        int[][] dp = new int[N][10001];

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int app_size = app[i][0];
            int app_cost = app[i][1];
            for (int j = 0; j <= 10000; j++) {
                if (i == 0) {
                    if (j >= app_cost) dp[i][j] = app_size;
                } else {
                    if (j >= app_cost) dp[i][j] = Math.max(dp[i - 1][j - app_cost] + app_size, dp[i - 1][j]);
                    else dp[i][j] = dp[i - 1][j];
                }
                if (dp[i][j] >= M) answer = Math.min(answer, j);
            }
        }
        System.out.print(answer);
    }
}
