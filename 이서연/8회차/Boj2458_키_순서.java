package boj.g4;

import java.util.Scanner;

public class Boj2458_키_순서 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();

		int[][] D = new int[N + 1][N + 1];
		int MAX = N;
		int a, b;
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				if (i != j) {
					D[i][j] = MAX;
				} else {
					D[i][j] = 0;
				}
			}
		}

		for (int m = 0; m < M; m++) {
			a = sc.nextInt();
			b = sc.nextInt();

			D[a][b] = 1;
		}

		for (int k = 1; k < N + 1; k++) {
			for (int i = 1; i < N + 1; i++) {
				if (k == i)
					continue;
				for (int j = 1; j < N + 1; j++) {
					if (k == j || i == j)
						continue;
					D[i][j] = Math.min(D[i][j], D[i][k] + D[k][j]);
				}
			}
		}

		int[] count = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				if (D[i][j] < MAX && i != j) {
					count[i]++;
					count[j]++;
				}
			}
		}

		int answer = 0;
		for (int i = 1; i < N + 1; i++) {
			if (count[i] == N - 1)
				answer++;
		}

		System.out.println(answer);
	}
}
