package boj.s1;

import java.util.Scanner;

public class Boj12852_1로_만들기_2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] memo = new int[N + 1][2];

		// 1: 1을 뺀다, 2: 2로 나눈다, 3: 3으로 나눈다
		for (int i = 2; i <= N; i++) {
			memo[i][0] = memo[i - 1][0] + 1;
			memo[i][1] = 1;
			if (i % 2 == 0 && memo[i / 2][0] + 1 < memo[i][0]) {
				memo[i][0] = memo[i / 2][0] + 1;
				memo[i][1] = 2;
			}
			if (i % 3 == 0 && memo[i / 3][0] + 1 < memo[i][0]) {
				memo[i][0] = memo[i / 3][0] + 1;
				memo[i][1] = 3;
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append(memo[N][0]).append('\n');

		int now = N;
		while (true) {
			sb.append(now).append(" ");
			if (now == 1) {
				break;
			}
			switch (memo[now][1]) {
			case 1:
				now = now - 1;
				break;
			case 2:
				now = now / 2;
				break;
			case 3:
				now = now / 3;
				break;
			}
		}
		System.out.println(sb.toString());
		sc.close();
	}
}
