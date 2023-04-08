package day0409;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_14500_테트로미노 {
	static int n, m, ans;
	static int[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static boolean[][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		ans = Integer.MIN_VALUE;
		map = new int[n][m];
		visit = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				visit[i][j] = true;
				dfs(i, j, 1, map[i][j]);
				visit[i][j] = false;
				block(i,j);
			}
		}
		System.out.println(ans);

	}

	static void block(int i, int j) { // ㅗ 모양 경우
		// 상 
		if (i > 0 && j < m - 2)
			ans = Math.max(ans, map[i][j] + map[i][j + 1] + map[i][j + 2] + map[i - 1][j + 1]);
		// 하
		if (i < n - 1 && j < m - 2)
			ans = Math.max(ans, map[i][j] + map[i][j + 1] + map[i][j + 2] + map[i + 1][j + 1]);
		// 좌
		if (i < n - 2 && j > 0)
			ans = Math.max(ans, map[i][j] + map[i + 1][j] + map[i + 2][j] + map[i + 1][j - 1]);
		// 우
        if (i < n - 2 && j < m - 1)
            ans = Math.max(ans, map[i][j] + map[i + 1][j] + map[i + 2][j] + map[i + 1][j + 1]);
    }

	private static void dfs(int i, int j, int cnt, int sum) { // ㅗ 제외 나머지 모양
		if (cnt == 4) {
			if (sum > ans)
				ans = sum;
			return;
		}

		for (int d = 0; d < 4; d++) {
			int nr = i + dr[d];
			int nc = j + dc[d];
			if (nr >= 0 && nc >= 0 && nr < n && nc < m && !visit[nr][nc]) {
				visit[nr][nc] = true;
				dfs(nr, nc, cnt + 1, sum + map[nr][nc]);
				visit[nr][nc] = false;
			}

		}

	}
}
