package st;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_12865 {

    static int[][] arr;
    static int[][] dp;
    static int n, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());


        arr = new int[n + 1][2];
        dp = new int[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st1.nextToken());
            arr[i][1] = Integer.parseInt(st1.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                if (j - arr[i][0] >= 0) // 자신의 무게를 뺀 값이 0 이상인 경우
                    dp[i][j] = Math.max(dp[i - 1][j], arr[i][1] + dp[i - 1][j - arr[i][0]]);
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }

        System.out.println(dp[n][k]);
    }

}