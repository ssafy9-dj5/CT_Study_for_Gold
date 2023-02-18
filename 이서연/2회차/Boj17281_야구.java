package boj.g4;

import java.util.Scanner;

public class Boj17281_야구 {
	static boolean[] used;
	static int[] result;
	static int N, maxScore;
	static int[][] player;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		player = new int[N][10];

		for (int i = 0; i < N; i++) {
			for (int j = 1; j <= 9; j++) {
				player[i][j] = sc.nextInt();
			}
		}

		used = new boolean[10];
		result = new int[10]; // 타순
		maxScore = Integer.MIN_VALUE;
		perm(1);

		System.out.println(maxScore);
	}

	private static void perm(int idx) {
		if (idx == 10) {
			play(); // 결정된 타순으로 경기
			return;
		}

		if (idx == 4) { // 4번 타자
			result[idx] = 1;
			used[1] = true;
			perm(idx + 1);
		} else {
			for (int i = 2; i <= 9; i++) { // 1번 선수 빼고
				if (!used[i]) {
					result[idx] = i;
					used[i] = true;
					perm(idx + 1);
					used[i] = false;
				}
			}

		}
	}

	static void play() {
		int inning = 0;
		int score = 0;
		int[] base = new int[4];
		int outCnt = 0;
		int p_num = 1;

		while (true) {
			if (inning == N)
				break;
			switch (player[inning][result[p_num]]) {
			case 0:
				outCnt++;
				break;
			case 1:
				score += base[3];
				base[3] = base[2];
				base[2] = base[1];
				base[1] = 1;
				break;
			case 2:
				score += base[3] + base[2];
				base[3] = base[1];
				base[2] = 1;
				base[1] = 0;
				break;
			case 3:
				score += base[3] + base[2] + base[1];
				base[3] = 1;
				base[2] = 0;
				base[1] = 0;
				break;
			case 4:
				score += base[3] + base[2] + base[1] + 1;
				base[3] = 0;
				base[2] = 0;
				base[1] = 0;
				break;
			}

			p_num = p_num + 1;
			if (p_num == 10)
				p_num = 1;

			if (outCnt == 3) {
				inning++;
				outCnt = 0;
				base = new int[4];
			}
		}
		maxScore = Math.max(maxScore, score);
	}
}
