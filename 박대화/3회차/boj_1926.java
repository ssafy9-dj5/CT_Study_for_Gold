import java.util.Scanner;

public class Main {
	static boolean[][] visited;
	static int[][] map;
	static int n, m;
	static int max = 0;
	static int area = 0;

	public static void dfs(int r, int c) {
		area++;

		if (r - 1 >= 0 && map[r - 1][c] == 1 && !visited[r - 1][c]) {
			visited[r - 1][c] = true;
			dfs(r - 1, c);
		}
		if (r + 1 < n && map[r + 1][c] == 1 && !visited[r + 1][c]) {
			visited[r + 1][c] = true;
			dfs(r + 1, c);
		}
		if (c - 1 >= 0 && map[r][c - 1] == 1 && !visited[r][c - 1]) {
			visited[r][c - 1] = true;
			dfs(r, c - 1);
		}
		if (c + 1 < m && map[r][c + 1] == 1 && !visited[r][c + 1]) {
			visited[r][c + 1] = true;
			dfs(r, c + 1);
		}

	}

	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();

		map = new int[n][m];
		visited = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		// end input

		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					area = 0;
					cnt++;
					visited[i][j] = true;
					dfs(i, j);
					max = Math.max(area, max);
				}
			}
		}

		System.out.println(cnt);
		System.out.println(max);

	}
}