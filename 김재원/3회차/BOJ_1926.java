package day0226;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ_1926 {
	static int n;
	static int m;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };
	static int[][] wall;
	static ArrayDeque<Point> q;
	static boolean[][] isVisited;

	public static void main(String[] args) throws IOException {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		wall = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				wall[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// check
		int cnt = 0;
		int res = 0;
		q = new ArrayDeque<>();
		isVisited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!isVisited[i][j] && wall[i][j] == 1) {
					cnt++;
					res = Math.max(dfs(i, j), res);
				}
			}
		}
		System.out.println(cnt);
		System.out.println(res);
	}

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int dfs(int x, int y) {
		int cnt = 1;
		q.offer(new Point(x, y));
		isVisited[x][y] = true;
		while (!q.isEmpty()) {
			Point curr = q.poll();
			for (int i = 0; i < 4; i++) {
				Point next = new Point(curr.x + dx[i], curr.y + dy[i]);
				if (next.x >= 0 && next.y >= 0 && next.x < n && next.y < m && wall[next.x][next.y] == 1
						&& !isVisited[next.x][next.y]) {
					q.offer(next);
					isVisited[next.x][next.y] = true;
					cnt++;
				}
			}
		}
		return cnt;
	}
}
