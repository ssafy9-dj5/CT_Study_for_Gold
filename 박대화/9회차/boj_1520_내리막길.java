import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static int[][] map;
	static int[][] dp;

	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };

	public static void dfs(int x, int y, int num) {
		if (x == n - 1 && y == m - 1) { // top-down 방식으로 dp
			dp[x][y] = 1;
			return;
		}

		dp[x][y] = 0;

		for (int d = 0; d < 4; d++) {
			int nextx = x + di[d];
			int nexty = y + dj[d];

			if (nextx >= 0 && nextx < n && nexty >= 0 && nexty < m && map[nextx][nexty] < num) {
				if (dp[nextx][nexty] != -1) {
					dp[x][y] += dp[nextx][nexty];
				} else {
					dfs(nextx, nexty, map[nextx][nexty]);
					dp[x][y] += dp[nextx][nexty];
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		dp = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}
		// end input

		dfs(0, 0, map[0][0]);

//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < m; j++) {
//				System.out.print(dp[i][j] + " ");
//			}
//			System.out.println();
//		}

		System.out.println(dp[0][0]);
	}
}
