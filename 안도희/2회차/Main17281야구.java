package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main17281야구 {
	static int[] s = { 1, 2, 3, 0, 4, 5, 6, 7, 8 };
	static boolean[] issel = new boolean[9];
	static int inning;
	static int[][] arr;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		inning = Integer.parseInt(br.readLine());
		arr = new int[inning][9];
		for (int i = 0; i < inning; i++) { // 선수들 득점
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		score();
		while (nperm());

		System.out.println(max);
	}

	private static boolean nperm() {// 타석 순서 순열
		int i = 8;
		while ((i > 0 && s[i - 1] >= s[i]) || i - 1 == 3) {
			if (i - 1 == 3) {
				if (s[i - 2] <= s[i]) {
					i--;
					break;
				}
			}
			i--;
		}
		if (i == 0)
			return false;

		int j = 8;
		while (s[i - 1] >= s[j])
			j--;
		swap(i - 1, j);

		int k = 8;
		while (i < k) {
			if (i == 3) {
				i++;
				continue;
			}
			if (k == 3) {
				k--;
				continue;
			}
			swap(i++, k--);
		}
		score();
		return true;
	}

	private static void swap(int i, int j) {
		int tmp = s[i];
		s[i] = s[j];
		s[j] = tmp;

	}

	private static void score() {
		int player = 0; // 현재 타자 번호
		int total = 0;
		for (int i = 0; i < inning; i++) {
			int out = 0;
			boolean[] play = new boolean[4]; // 출루 상황
			while (true) {
				switch (arr[i][s[player]]) {
				case 0:
					out++;
					break;
				case 1:
					if (play[3]) {
						total++;
						play[3] = false;
					}
					if (play[2]) {
						play[3] = true;
						play[2] = false;
					}
					if (play[1]) {
						play[2] = true;
						play[1] = false;
					}
					play[1] = true;

					break;
				case 2:
					if (play[3]) {
						total++;
						play[3] = false;
					}
					if (play[2]) {
						total++;
						play[2] = false;
					}
					if (play[1]) {
						play[3] = true;
						play[1] = false;
					}
					play[2] = true;

					break;
				case 3:
					if (play[3]) {
						total++;
						play[3] = false;
					}
					if (play[2]) {
						total++;
						play[2] = false;
					}
					if (play[1]) {
						total++;
						play[1] = false;
					}
					play[3] = true;

					break;

				case 4:
					if (play[3]) {
						total++;
						play[3] = false;
					}
					if (play[2]) {
						total++;
						play[2] = false;
					}
					if (play[1]) {
						total++;
						play[1] = false;
					}
					total++;
					break;
				}

				player = (player + 1) % 9;
				if (out == 3)
					break;
			}
		}
		if (total > max)
			max = total;
	}
}
