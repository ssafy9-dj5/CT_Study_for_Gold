package boj.s2;

import java.util.Scanner;

public class Boj14889_스타트와_링크 {
	static int N, minResult;
	static int[][] input;
	static boolean[] selected;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		input = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				input[i][j] = sc.nextInt();
			}
		}

		selected = new boolean[N];
		minResult = Integer.MAX_VALUE;
		comb(0, 0);
		System.out.println(minResult);
	}

	private static void comb(int idx, int cnt) {
		if (cnt == N / 2) {
			int team1 = 0;
			int team2 = 0;
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					if (selected[i] && selected[j]) {
						team1 += input[i][j] + input[j][i];
					} else if (!selected[i] && !selected[j]) {
						team2 += input[i][j] + input[j][i];
					}
				}
			}
			minResult = Math.min(minResult, Math.abs(team1 - team2));
			return;
		}
		if (idx == N) {
			return;
		}

		selected[idx] = true;
		comb(idx + 1, cnt + 1);
		selected[idx] = false;
		comb(idx + 1, cnt);
	}
}
