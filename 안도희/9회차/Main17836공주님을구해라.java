package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main17836공주님을구해라 {

	static class hero {
		int i, j;

		public hero(int i, int j) {
			this.i = i;
			this.j = j;
		}

	}

	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };

	static int n, m, t;
	static int[][] map;
	static int min = Integer.MAX_VALUE;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());

		map = new int[n + 1][m + 1];
		visited = new boolean[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Queue<hero> queue = new LinkedList<>();
		queue.add(new hero(1, 1));
		visited[1][1] = true;
		if (bfs(queue))
			System.out.println(min);
		else
			System.out.println("Fail");

	}

	private static boolean bfs(Queue<hero> queue) {
		int cnt = 0;
		while (!queue.isEmpty()) {
			cnt++;
			if (cnt == min)
				return true;
			int num = queue.size();
			for (int i = 0; i < num; i++) {
				hero now = queue.poll();

				for (int d = 0; d < 4; d++) {
					int nexti = now.i + di[d];
					int nextj = now.j + dj[d];
					if (nexti == n && nextj == m) {//공주
						min = cnt;
						return true;
					}
					if (nexti > 0 && nexti <= n && nextj > 0 && nextj <= m && !visited[nexti][nextj]) {

						if (map[nexti][nextj] == 1) {// 벽
							continue;

						} else if (map[nexti][nextj] == 2) {// 
							visited[nexti][nextj] = true;
							if (cnt + (n - nexti) + (m - nextj) <= t)
								min = cnt + (n - nexti) + (m - nextj);
							continue;
						}

						visited[nexti][nextj] = true;
						queue.add(new hero(nexti, nextj));
					}

				}
			}
			if (cnt == t) {
				break;
			}
		}
		if (min == Integer.MAX_VALUE)
			return false;
		return true;

	}
}
