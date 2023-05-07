package boj.g3;

import java.util.Scanner;

public class Boj7579_앱 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] m = new int[N + 1];
		int[] c = new int[N + 1];
		int cost = 0;

		for (int n = 1; n <= N; n++) {
			m[n] = sc.nextInt();
		}
		for (int n = 1; n <= N; n++) {
			c[n] = sc.nextInt();
			cost += c[n];
		}

		int[][] d = new int[N + 1][cost + 1];
		for (int j = 0; j <= cost; j++) {
			for (int i = 1; i <= N; i++) {
				d[i][j] = d[i - 1][j];
				if (c[i] <= j) {
					d[i][j] = Math.max(d[i][j], d[i - 1][j - c[i]] + m[i]);
				}
			}
		}

		int answer = 0;
		for (int j = 0; j <= cost; j++) {
			if (d[N][j] >= M) {
				answer = j;
				break;
			}
		}
		System.out.println(answer);
	}
}
