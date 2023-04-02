package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2589º¸¹°¼¶ {
	static int max = 0;
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	static int r, c;
	static char[][] map;
	static boolean[][] visited;

	public static class Index {
		int i, j;

		public Index(int i, int j) {
			this.i = i;
			this.j = j;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];

		for (int i = 0; i < r; i++) {
			String s = br.readLine();
			map[i] = s.toCharArray();
		}

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] == 'L') {
					visited = new boolean[r][c];
					max = Math.max(max, bfs(i, j));
				}

			}
		}
		System.out.println(max);

	}

	private static int bfs(int i, int j) {
		Queue<Index> queue = new LinkedList<>();
		queue.add(new Index(i, j));
		visited[i][j] = true;
		int cnt = -1;

		while (!queue.isEmpty()) {
			int num = queue.size();
			cnt++;
			for (int t = 0; t < num; t++) {
				Index now = queue.poll();
				
				for (int d = 0; d < 4; d++) {
					int nexti = now.i + di[d];
					int nextj = now.j + dj[d];
					if (nexti >= 0 && nexti < r && nextj >= 0 && nextj < c && map[nexti][nextj] == 'L'
							&& !visited[nexti][nextj]) {
						queue.add(new Index(nexti, nextj));
						visited[nexti][nextj] = true;
				}}
			}
		}
		return cnt;

	}

}
