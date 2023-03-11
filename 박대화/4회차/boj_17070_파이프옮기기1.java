import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] di = { 0, 1, 1 };
	static int[] dj = { 1, 0, 1 };
	static int n;
	static int[][] map;
	static int cnt = 0;

	public static void dfs(int r, int c, int dir) {
		if (r == n - 1 && c == n - 1) {
			cnt++;
			return;
		}

		if (dir != 0) {
			int nexti = r + di[1];
			int nextj = c + dj[1];
			if (nexti < n && map[nexti][nextj] != 1) {
				dfs(nexti, nextj, 1);
			}
		}
		if (dir != 1) {
			int nexti = r + di[0];
			int nextj = c + dj[0];
			if (nextj < n && map[nexti][nextj] != 1) {
				dfs(nexti, nextj, 0);
			}
		}
		int nexti = r + di[2];
		int nextj = c + dj[2];
		if (nextj < n && nexti < n && map[nexti][nextj] != 1 && map[r + 1][c] != 1 && map[r][c + 1] != 1) {
			dfs(nexti, nextj, 2);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		map = new int[n][n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// end input

		dfs(0, 1, 0);

		System.out.println(cnt);

		br.close();
	}
}