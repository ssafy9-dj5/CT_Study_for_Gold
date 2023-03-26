package day0319;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;

public class Boj_13459_구슬탈출 {
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static char[][] map;
	static int N, M;
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		Pair blue = new Pair(0, 0);
		Pair red = new Pair(0, 0);

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);

				if (map[i][j] == 'B') {
					blue = new Pair(i, j);
					map[i][j] = '.';
				}

				if (map[i][j] == 'R') {
					red = new Pair(i, j);
					map[i][j] = '.';
				}
			}
		}

		for (int i = 0; i < 4; i++) {
			dfs(i, red, blue, 0);
		}
		System.out.println(ans);
	}

	public static void dfs(int d, Pair red, Pair blue, int cnt) {
		if (cnt >= 10)
			return;

		int rx = red.x;
		int ry = red.y;
		int bx = blue.x;
		int by = blue.y;
		boolean flag = false;

		while (rx >= 0 && rx < N && ry >= 0 && ry < M) {
			rx += dx[d];
			ry += dy[d];

			if (map[rx][ry] == 'O') {
				flag = true;
				break;
			}

			if (map[rx][ry] == '#') {
				rx -= dx[d];
				ry -= dy[d];
				break;
			}
		}

		while (bx >= 0 && bx < N && by >= 0 && by < M) {
			bx += dx[d];
			by += dy[d];

			if (map[bx][by] == 'O')
				return;

			if (map[bx][by] == '#') {
				bx -= dx[d];
				by -= dy[d];
				break;
			}
		}

		if (flag) {
			ans = 1;
			return;
		}

		if (rx == bx && ry == by) {
			if (d == 0 && blue.x < red.x) {
				rx++;
			}

			else if (d == 0 && blue.x > red.x) {
				bx++;
			}

			else if (d == 1 && blue.x > red.x) {
				rx--;
			}

			else if (d == 1 && blue.x < red.x) {
				bx--;
			}

			else if (d == 2 && blue.y < red.y) {
				ry++;
			}

			else if (d == 2 && blue.y > red.y) {
				by++;
			}

			else if (d == 3 && blue.y > red.y) {
				ry--;
			}

			else if (d == 3 && blue.y < red.y) {
				by--;
			}
		}

		for (int i = 0; i < 4; i++)
			dfs(i, new Pair(rx, ry), new Pair(bx, by), cnt + 1);
	}

	public static class Pair {
		int x;
		int y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}