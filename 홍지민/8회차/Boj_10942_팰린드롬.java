package day0409;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_10942_팰린드롬 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int[] num = new int[n + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		int[][] dp = new int[n + 1][n + 1];

		for (int i = 1; i <= n; i++) {
			dp[i][i] = 1; // 길이 1인 경우
			if (i < n) {
				if (num[i] == num[i + 1]) { // 길이 2인 경우
					dp[i][i + 1] = 1;
				}
			}
		}

		for (int j = 2; j < n; j++) { // 길이 3 이상의 경우
			for (int i = 1; i <= n - j; i++) {
				if (dp[i + 1][i + j - 1] == 1 && num[i] == num[i + j]) {
					dp[i][i + j] = 1;
				}
			}
		}

		int m = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			sb.append(dp[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())]).append("\n");
		}
		System.out.println(sb);

	}
}
