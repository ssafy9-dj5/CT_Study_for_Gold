package day0416;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_1520_내리막길 {
	static int m, n;
	static int[][] map, dp;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		map = new int[m][n];
		dp = new int[m][n];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < m; i++) {
			Arrays.fill(dp[i], -1);
		}

		dp[m-1][n-1] = 1;
		int ans = count(0, 0);
		System.out.println(ans);
	}

	private static int count(int i, int j) {
		// 방문한 적이 있을 때
		if (dp[i][j] != -1) {
			return dp[i][j];
		}

		// 방문한 적이 없을 때
		dp[i][j] = 0;
		for (int d = 0; d < 4; d++) {
			int nr = i + dr[d];
			int nc = j + dc[d];

			if (nr >= 0 && nc >= 0 && nr < m && nc < n && map[i][j] > map[nr][nc]) {
				dp[i][j] += count(nr, nc);
			}
		}

		return dp[i][j];
	}

}
