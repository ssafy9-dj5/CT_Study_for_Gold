package st;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_19621 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] meeting = new int[Math.max(2, n)][3];
        int[] dp = new int[Math.max(2, n)];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            meeting[i][0] = Integer.parseInt(st.nextToken());
            meeting[i][1] = Integer.parseInt(st.nextToken());
            meeting[i][2] = Integer.parseInt(st.nextToken());
        }

        //초기값
        dp[0] = meeting[0][2];
        dp[1] = Math.max(meeting[0][2], meeting[1][2]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + meeting[i][2]);
        }

        System.out.println(dp[n - 1]);
    }
}


