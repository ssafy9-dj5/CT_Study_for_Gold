
import java.util.Scanner;

public class Boj_17070_파이프옮기기 {

	static int[][] map;
	static int n, cnt;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		map = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		// 가로:0, 세로:1, 대각선:2
		dfs(1, 2, 0);

		System.out.println(cnt);

	}

	private static void dfs(int r, int c, int d) {
		if (r == n && c == n) {
			cnt++;
			return;
		}

		if (d == 0) { // 가로
			if (check(r, c + 1) && map[r][c + 1] != 1) {
				dfs(r, c + 1, 0);
			}

			if (check(r + 1, c + 1) && map[r][c + 1] != 1 && map[r + 1][c] != 1 && map[r + 1][c + 1] != 1) {
				dfs(r + 1, c + 1, 2);
			}
		}

		else if (d == 1) { // 세로
			if (check(r + 1, c) && map[r + 1][c] != 1) {
				dfs(r + 1, c, 1);
			}

			if (check(r + 1, c + 1) && map[r][c + 1] != 1 && map[r + 1][c] != 1 && map[r + 1][c + 1] != 1) {
				dfs(r + 1, c + 1, 2);
			}
		}

		else if (d == 2) { // 대각선
			if (check(r, c + 1) && map[r][c + 1] != 1) {
				dfs(r, c + 1, 0);
			}

			if (check(r + 1, c) && map[r + 1][c] != 1) {
				dfs(r + 1, c, 1);
			}

			if (check(r + 1, c + 1) && map[r][c + 1] != 1 && map[r + 1][c] != 1 && map[r + 1][c + 1] != 1) {
				dfs(r + 1, c + 1, 2);
			}
		}

	}

	private static boolean check(int i, int j) {
		return i > 0 && i <= n && j > 0 && j <= n;
	}

}
