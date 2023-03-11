package boj.g5;

import java.util.Scanner;

public class Boj17070_파이프_옮기기_1 {
	static int N, count;
	static int[][] map;
	static boolean[][] visited;
	static int[] di = { 0, 1, 1 }; // 가로, 대각선, 세로
	static int[] dj = { 1, 1, 0 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		visited = new boolean[N][N];
		count = 0;
		dfs(0, 1, 0);

		System.out.println(count);
	}

	private static void dfs(int i, int j, int dir) {
		if (i == N - 1 && j == N - 1) {
			count++;
			return;
		}
		visited[i][j] = true;

		int nexti, nextj;
		switch (dir) {
		case 0: // 가로 파이프
			for (int d = 0; d <= 1; d++) {
				nexti = i + di[d];
				nextj = j + dj[d];
				if (nexti >= 0 && nexti < N && nextj >= 0 && nextj < N && !visited[nexti][nextj]
						&& map[nexti][nextj] == 0) {
					if (d == 1 && (map[nexti][j] == 1 || map[i][nextj] == 1))
						continue;
					dfs(nexti, nextj, d);
				}
			}
			break;
		case 1: // 대각선 파이프
			for (int d = 0; d <= 2; d++) {
				nexti = i + di[d];
				nextj = j + dj[d];
				if (nexti >= 0 && nexti < N && nextj >= 0 && nextj < N && !visited[nexti][nextj]
						&& map[nexti][nextj] == 0) {
					if (d == 1 && (map[nexti][j] == 1 || map[i][nextj] == 1))
						continue;
					dfs(nexti, nextj, d);
				}
			}
			break;
		case 2: // 세로 파이프
			for (int d = 1; d <= 2; d++) {
				nexti = i + di[d];
				nextj = j + dj[d];
				if (nexti >= 0 && nexti < N && nextj >= 0 && nextj < N && !visited[nexti][nextj]
						&& map[nexti][nextj] == 0) {
					if (d == 1 && (map[nexti][j] == 1 || map[i][nextj] == 1))
						continue;
					dfs(nexti, nextj, d);
				}
			}
			break;

		}
		visited[i][j] = false;
	}
}
