import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][][] tetro = { { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 0, 3 } }, { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 3, 0 } },
			{ { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } }, { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 2, 1 } },
			{ { 0, 0 }, { 1, 0 }, { 2, 0 }, { 2, -1 } }, { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 2 } },
			{ { 0, 0 }, { 1, 0 }, { 1, 1 }, { 2, 1 } }, { { 0, 0 }, { 0, 1 }, { -1, 1 }, { -1, 2 } },
			{ { 0, 0 }, { 0, 1 }, { -1, 1 }, { 1, 0 } }, { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 0 } },
			{ { 0, 0 }, { 0, 1 }, { 1, 1 }, { 2, 1 } }, { { 0, 0 }, { 0, 1 }, { 0, 2 }, { -1, 2 } },
			{ { 0, 0 }, { 1, 0 }, { 1, 1 }, { 1, 2 } }, { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 2, 0 } },
			{ { 0, 0 }, { 0, 1 }, { 1, 1 }, { 1, 2 } }, { { 0, 0 }, { 0, 1 }, { 0, 2 }, { -1, 1 } },
			{ { 0, 0 }, { 0, 1 }, { -1, 1 }, { 1, 1 } }, { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 1 } },
			{ { 0, 0 }, { 1, 0 }, { 2, 0 }, { 1, 1 } } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[][] map = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// end input

		int ans = -1;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				for (int t = 0; t < 19; t++) {
					int cnt = 0;
					for (int d = 0; d < 4; d++) {
						int nexti = i + tetro[t][d][0];
						int nextj = j + tetro[t][d][1];

						if (nexti < 0 || nexti >= n || nextj < 0 || nextj >= m) {
							break;
						}

						cnt += map[nexti][nextj];
					}
					ans = Math.max(ans, cnt);
				}
			}
		}

		System.out.println(ans);
	}
}