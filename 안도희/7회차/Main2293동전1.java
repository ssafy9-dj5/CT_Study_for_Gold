package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Character.Subset;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main2293µ¿Àü1 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int sum = Integer.parseInt(st.nextToken());

		int[] coin = new int[n + 1];
		int[][] dp = new int[n + 1][sum + 1];
		for (int i = 1; i <= n; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= sum; j++) {
				if (coin[i] > j)
					dp[i][j] = dp[i - 1][j];
				else {
					dp[i][j] = dp[i - 1][j] + dp[i][j - coin[i]];
				}
				if (coin[i] == j)
					dp[i][j] += 1;

			}
		}
		System.out.println(dp[n][sum]);
	}

}
