package boj.g5;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Boj17836_공주님을_구해라 {
	static int N, M, T, answer;
	static int[][] map;
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		T = sc.nextInt();

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		answer = Integer.MAX_VALUE;
		bfs();
		if (answer <= T)
			System.out.println(answer);
		else
			System.out.println("Fail");

		sc.close();
	}

	private static void bfs() {
		Queue<Point> queue = new ArrayDeque<>();
		boolean[][][] visited = new boolean[N][M][2];

		queue.add(new Point(0, 0, 0));
		visited[0][0][0] = true;

		int time = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int s = 0; s < size; s++) {
				Point now = queue.poll();
				if (now.i == N - 1 && now.j == M - 1) {
					answer = Math.min(answer, time);
				}
				for (int d = 0; d < 4; d++) {
					int nexti = now.i + di[d];
					int nextj = now.j + dj[d];
					int nextS = now.hasSword;
					if (nexti >= 0 && nexti < N && nextj >= 0 && nextj < M && !visited[nexti][nextj][nextS]) {
						if (nextS == 1 || map[nexti][nextj] == 0) { // 검을 갖고있거나 빈칸이면
							visited[nexti][nextj][nextS] = true;
							queue.add(new Point(nexti, nextj, nextS));
						} else if (map[nexti][nextj] == 2) {
							nextS = 1;
							visited[nexti][nextj][nextS] = true;
							queue.add(new Point(nexti, nextj, nextS));
						}
					}
				}
			}
			time++;
		}
	}

	static class Point {
		int i, j, hasSword;

		public Point(int i, int j, int hasSword) {
			super();
			this.i = i;
			this.j = j;
			this.hasSword = hasSword; // 검이 있으면 1
		}
	}
}
