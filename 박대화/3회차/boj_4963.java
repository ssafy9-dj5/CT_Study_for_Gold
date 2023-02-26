import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int w, h;
	static int[][] delta = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };

	public static void bfs(int[][] map, int r, int c) {
		Queue<int[]> q = new ArrayDeque<>();

		q.offer(new int[] { r, c });
		map[r][c] = 2;

		while (!q.isEmpty()) {
			int[] p = q.poll();

			for (int i = 0; i < 8; i++) {
				int nextr = p[0] + delta[i][0];
				int nextc = p[1] + delta[i][1];

				if (nextr < h && nextr >= 0 && nextc < w && nextc >= 0 && map[nextr][nextc] == 1) {
					q.offer(new int[] { nextr, nextc });
					map[nextr][nextc] = 2;
				}
			}
		}
	}

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			int[][] map = new int[h][w];
			int landcnt = 0;

			if (w == 0 && h == 0) {
				break;
			}

			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// bfs
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (map[i][j] == 1) {
						bfs(map, i, j);
						landcnt++;
					}
				}
			}

			System.out.println(landcnt);

		}

		br.close();
	}
}