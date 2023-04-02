import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static char[][] map;

	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };

	public static class Pos {
		int x;
		int y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Pos [x=" + x + ", y=" + y + "]";
		}
	}

	public static int bfs(int i, int j) {
		Queue<Pos> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[n][m];

		q.offer(new Pos(i, j));
		visited[i][j] = true;

		int time = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int s = 0; s < size; s++) {
				Pos cur = q.poll();

				for (int d = 0; d < 4; d++) {
					int nextx = cur.x + di[d];
					int nexty = cur.y + dj[d];
					if (nextx >= 0 && nextx < n && nexty >= 0 && nexty < m && map[nextx][nexty] == 'L'
							&& !visited[nextx][nexty]) {
						q.offer(new Pos(nextx, nexty));
						visited[nextx][nexty] = true;
					}
				}
			}
			time++;
		}

		return time - 1;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new char[n][m];

		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		// end input

		int max = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 'L') {
					int result = bfs(i, j);

					max = Math.max(max, result);
				}
			}
		}

		System.out.println(max);

	}
}