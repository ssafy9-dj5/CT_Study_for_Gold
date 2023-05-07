import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int[] t = new int[n];
		int[] p = new int[n];
		int[] dp = new int[n + 1];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			t[i] = Integer.parseInt(st.nextToken());
			p[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = n - 1; i >= 0; i--) {
			if (i + t[i] > n) {
				dp[i] = dp[i + 1];
			} else {
				dp[i] = Math.max(dp[i + 1], dp[i + t[i]] + p[i]);
			}
		}

		System.out.println(dp[0]);
	}
}
