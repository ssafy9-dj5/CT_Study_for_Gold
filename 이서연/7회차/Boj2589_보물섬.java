package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2589_보물섬 {
	static int N, M, answer;
	static char[][] map;
	static boolean[][] visited;
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		answer = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'L') {
					bfs(i, j);
				}
			}
		}

		System.out.println(answer);
	}

	private static void bfs(int si, int sj) {
		visited = new boolean[N][M];
		Queue<Point> queue = new ArrayDeque<>();
		queue.add(new Point(si, sj, 0));
		visited[si][sj] = true;

		while (!queue.isEmpty()) {
			Point now = queue.poll();
			answer = Math.max(answer, now.d);
			for (int d = 0; d < 4; d++) {
				int nexti = now.i + di[d];
				int nextj = now.j + dj[d];

				if (nexti >= 0 && nexti < N && nextj >= 0 && nextj < M && !visited[nexti][nextj]
						&& map[nexti][nextj] == 'L') {
					visited[nexti][nextj] = true;
					queue.add(new Point(nexti, nextj, now.d + 1));
				}
			}
		}
	}

	static class Point {
		int i, j, d;

		public Point(int i, int j, int d) {
			super();
			this.i = i;
			this.j = j;
			this.d = d;
		}
	}
}
