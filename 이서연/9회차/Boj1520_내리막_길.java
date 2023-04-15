package boj.g3;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Boj1520_내리막_길 {
	static int N, M, answer;
	static int[][] map;
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		answer = 0;
		bfs();
		System.out.println(answer);
		sc.close();
	}

	private static void bfs() {
		Queue<Point> queue = new ArrayDeque<>();
		queue.add(new Point(0, 0, map[0][0]));

		while (!queue.isEmpty()) {
			Point now = queue.poll();
			if (now.i == N - 1 && now.j == M - 1) {
				answer++;
			}

			for (int d = 0; d < 4; d++) {
				int nexti = now.i + di[d];
				int nextj = now.j + dj[d];
				if (nexti >= 0 && nexti < N && nextj >= 0 && nextj < M && now.h > map[nexti][nextj]) {
					queue.add(new Point(nexti, nextj, map[nexti][nextj]));
				}
			}
		}
	}

	static class Point {
		int i, j, h;

		public Point(int i, int j, int h) {
			super();
			this.i = i;
			this.j = j;
			this.h = h;
		}
	}
}
