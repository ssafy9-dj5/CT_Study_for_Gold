package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Boj14500_테트로미노 {
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] board = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		answer = 0;
		// 테트로미노
		// 1. ■ ■ ■ ■
		// 2. ■ ■ ■
		//    ■
		// 3. ■ ■ ■
		//      ■
		int si = 0;
		int sj = 0;
		int sum_3 = 0;

		int[] di = { -1, -1, -1, 1, 1, 1, 0 };
		int[] dj = { 0, 1, 2, 0, 1, 2, 3 };
		while (si < N) { // 세칸 연속 가로
			if (sj == 0) {
				sum_3 = board[si][sj] + board[si][sj + 1] + board[si][sj + 2];
			} else {
				sum_3 = sum_3 - board[si][sj - 1] + board[si][sj + 2];
			}

			for (int d = 0; d < di.length; d++) {
				int plusi = si + di[d];
				int plusj = sj + dj[d];
				if (plusi >= 0 && plusi < N && plusj >= 0 && plusj < M) {
					int total = sum_3 + board[plusi][plusj];
					answer = Math.max(answer, total);
				}
			}

			sj++;
			if (sj == M - 2) {
				si++;
				sj = 0;
			}
		}

		si = 0;
		sj = 0;
		while (sj < M) { // 세칸 연속 세로
			if (si == 0) {
				sum_3 = board[si][sj] + board[si + 1][sj] + board[si + 2][sj];
			} else {
				sum_3 = sum_3 - board[si - 1][sj] + board[si + 2][sj];
			}

			for (int d = 0; d < di.length; d++) {
				int plusi = si + dj[d];
				int plusj = sj + di[d];
				if (plusi >= 0 && plusi < N && plusj >= 0 && plusj < M) {
					int total = sum_3 + board[plusi][plusj];
					answer = Math.max(answer, total);
				}
			}

			si++;
			if (si == N - 2) {
				sj++;
				si = 0;
			}
		}

		// 4.   ■
		//	  ■ ■
		//	  ■
		// 5. ■ ■
		//    ■ ■

		int sum_2;
		int[] di_1 = { 1, -1, -1 };
		int[] dj_1 = { 0, 0, 1 };
		int[] di_2 = { 1, 1, 1 };
		int[] dj_2 = { 1, 1, 0 };
		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < M - 1; j++) {
				sum_2 = board[i][j] + board[i][j + 1];
				for (int d = 0; d < di_1.length; d++) {
					int plusi_1 = i + di_1[d];
					int plusj_1 = j + dj_1[d];
					int plusi_2 = i + di_2[d];
					int plusj_2 = j + dj_2[d];
					if (plusi_1 >= 0 && plusi_2 >= 0) {
						int total = sum_2 + board[plusi_1][plusj_1] + board[plusi_2][plusj_2];
						answer = Math.max(answer, total);
					}
				}
			}
		}

		int[] di_3 = { 0, 0 };
		int[] dj_3 = { -1, 1 };
		int[] di_4 = { 1, 1 };
		int[] dj_4 = { 1, -1 };
		for (int i = 0; i < N - 1; i++) {
			for (int j = 1; j < M - 1; j++) {
				sum_2 = board[i][j] + board[i + 1][j];
				for (int d = 0; d < di_3.length; d++) {
					int plusi_3 = i + di_3[d];
					int plusj_3 = j + dj_3[d];
					int plusi_4 = i + di_4[d];
					int plusj_4 = j + dj_4[d];
					int total = sum_2 + board[plusi_3][plusj_3] + board[plusi_4][plusj_4];
					answer = Math.max(answer, total);
				}
			}
		}

		System.out.println(answer);
	}
}
