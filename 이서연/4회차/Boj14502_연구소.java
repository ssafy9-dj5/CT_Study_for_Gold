package boj.g4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Boj14502_연구소 {
	static int N, M, answer;
	static int[][] map, copy;
	static ArrayList<Point> wallPoint;
	static boolean[] selected;
	static boolean[][] visited;
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();

		map = new int[N][M];
		wallPoint = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 0) {
					wallPoint.add(new Point(i, j));
				}
			}
		}
		selected = new boolean[wallPoint.size()];

		answer = 0;
		selectWall(0, 0);

		System.out.println(answer);
	}

	private static void selectWall(int idx, int cnt) {
		if (cnt == 3) {
			copy = deepcopy(map);
			for (int i = 0; i < selected.length; i++) {
				if (selected[i]) {
					Point now = wallPoint.get(i);
					copy[now.i][now.j] = 1;
				}
			}
			getSafeArea();
			return;
		}

		if (idx == wallPoint.size()) {
			return;
		}

		selected[idx] = true;
		selectWall(idx + 1, cnt + 1);
		selected[idx] = false;
		selectWall(idx + 1, cnt);
	}

	private static void getSafeArea() {
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copy[i][j] == 2 && !visited[i][j]) {
					bfs(i, j);
				}
			}
		}

		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copy[i][j] == 0) {
					count++;
				}
			}
		}
		answer = Math.max(answer, count);
	}

	private static void bfs(int i, int j) {
		Queue<Point> queue = new LinkedList<>();
		visited[i][j] = true;
		queue.add(new Point(i, j));

		while (!queue.isEmpty()) {
			Point now = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nexti = now.i + di[d];
				int nextj = now.j + dj[d];

				if (nexti >= 0 && nexti < N && nextj >= 0 && nextj < M && !visited[nexti][nextj]
						&& copy[nexti][nextj] == 0) {
					copy[nexti][nextj] = 2;
					visited[nexti][nextj] = true;
					queue.add(new Point(nexti, nextj));
				}
			}
		}
	}

	static int[][] deepcopy(int origin[][]) {
		int[][] tmp = new int[origin.length][origin[0].length];

		for (int i = 0; i < origin.length; i++) {
			for (int j = 0; j < origin[0].length; j++) {
				tmp[i][j] = origin[i][j];
			}
		}
		return tmp;
	}

	static class Point {
		int i, j;

		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + "]\n";
		}

		public Point(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
	}
}
