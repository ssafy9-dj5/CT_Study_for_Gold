import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, m, t;
	static int[][] map;
	static int ans;

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

	public static void bfs() {
		Queue<Pos> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[n][m];

		q.offer(new Pos(0, 0));
		visited[0][0] = true;

		int time = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int s = 0; s < size; s++) {
				Pos cur = q.poll();

				if (cur.x == n - 1 && cur.y == m - 1) {
					ans = Math.min(ans, time);
				}
				if (map[cur.x][cur.y] == 2) {
					int dist = Math.abs(cur.x - (n - 1)) + Math.abs(cur.y - (m - 1)); // 맨하탄 거리 = 벽을 뚫을 수 있기 때문에
					if (time + dist > t) { // t와 같아도 구할 수 있기 때문에 t 포함 x
						return;
					}
					ans = Math.min(ans, time + dist);
				}

				for (int d = 0; d < 4; d++) {
					int nextx = cur.x + di[d];
					int nexty = cur.y + dj[d];
					if (nextx >= 0 && nextx < n && nexty >= 0 && nexty < m && !visited[nextx][nexty]
							&& map[nextx][nexty] != 1) {
						q.offer(new Pos(nextx, nexty));
						visited[nextx][nexty] = true;
					}
				}
			}
			if (time >= t) { // t와 같을 때 확인하는 것을 이미 했기 때문에 t 포함
				return;
			}
			time++;
		}

		return;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());

		map = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// end input

		ans = Integer.MAX_VALUE;

		bfs();

		System.out.println(ans == Integer.MAX_VALUE ? "Fail" : ans);

	}
}