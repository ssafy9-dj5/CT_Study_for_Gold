package boj.g5;

import java.util.Scanner;

public class Boj12865_평범한_배낭 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int maxWeight = sc.nextInt();
		int[][] input = new int[N+1][2];

		for (int i = 1; i <= N; i++) {
			input[i][0] = sc.nextInt(); // 무게
			input[i][1] = sc.nextInt(); // 가치
		}

		int[][] dp = new int[N+1][maxWeight + 1];

		for (int i = 1; i <= N; i++) {
			for (int w = 1; w <= maxWeight; w++) {
				if (input[i][0] > w) {
					dp[i][w] = dp[i - 1][w];
				} else {
					dp[i][w] = Math.max(dp[i - 1][w], input[i][1] + dp[i - 1][w - input[i][0]]);
				}
			}
		}
		
		System.out.println(dp[N][maxWeight]);
	}
}
