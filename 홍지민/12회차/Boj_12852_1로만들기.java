package day0507;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj_12852_1로만들기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		int[] dp = new int[n + 1];
		int[] num = new int[n + 1];

		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[1] = 0;

		for (int i = 2; i <= n; i++) {

			if (i % 3 == 0 && (dp[i] > dp[i / 3] + 1)) {
				dp[i] = dp[i / 3] + 1;
				num[i] = i / 3;
			}
			if (i % 2 == 0 && (dp[i] > dp[i / 2] + 1)) {
				dp[i] = dp[i / 2] + 1;
				num[i] = i / 2;
			}
			if (i - 1 > 0 && (dp[i] > dp[i - 1] + 1)) {
				dp[i] = dp[i - 1] + 1;
				num[i] = i - 1;
			}

		}

		System.out.println(dp[n]);

		while (n > 0) {
			sb.append(n).append(" ");
			n = num[n];
		}
		System.out.println(sb);
	}
}
