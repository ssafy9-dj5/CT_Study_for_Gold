package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1926_그림 {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };
	static List<Integer> result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		visited = new boolean[N][M];
		result = new ArrayList<>();

		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					bfs(i, j);
					count++;
				}
			}
		}

		System.out.println(count);

		int max = 0;
		for (int i = 0; i < result.size(); i++) {
			max = Math.max(max, result.get(i));
		}
		System.out.println(max);
	}

	private static void bfs(int si, int sj) {
		Queue<Point> queue = new LinkedList<>();

		visited[si][sj] = true;
		queue.add(new Point(si, sj));

		int picture = 0;
		while (!queue.isEmpty()) {
			Point now = queue.poll();
			picture++;

			for (int d = 0; d < 4; d++) {
				int nexti = now.i + di[d];
				int nextj = now.j + dj[d];

				if (nexti >= 0 && nexti < N && nextj >= 0 && nextj < M && map[nexti][nextj] == 1
						&& !visited[nexti][nextj]) {
					visited[nexti][nextj] = true;
					queue.add(new Point(nexti, nextj));
				}
			}
		}

		result.add(picture);
	}

	static class Point {
		int i;
		int j;

		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
