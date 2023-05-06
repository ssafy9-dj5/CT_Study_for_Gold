package S1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_12852_1로_만드기2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] dp = new int[N + 1][2];
        if (N >= 2) {
            dp[2][0] = 1;
            dp[2][1] = 1;
        }
        if (N >= 3) {
            dp[3][0] = 1;
            dp[3][1] = 1;
        }

        for (int i = 2; i < N; i++) {
            if (dp[i][0] > 0) {
                int d = dp[i][0] + 1;
                if (i * 3 <= N && (dp[i * 3][0] > d || dp[i * 3][0] == 0)) {
                    dp[i * 3][0] = d;
                    dp[i * 3][1] = i;
                }
                if (i * 2 <= N && (dp[i * 2][0] > d || dp[i * 2][0] == 0)) {
                    dp[i * 2][0] = d;
                    dp[i * 2][1] = i;
                }
                if (i + 1 <= N && (dp[i + 1][0] > d || dp[i + 1][0] == 0)) {
                    dp[i + 1][0] = d;
                    dp[i + 1][1] = i;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int idx = N;
        sb.append(idx).append(' ');
        while (idx != 1) {
            sb.append(dp[idx][1]).append(' ');
            idx = dp[idx][1];
        }
        System.out.println(dp[N][0]);
        System.out.print(sb);
    }
}
