 package day0226;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_4963 {
	static int w;
	static int h;
	static int[][] map;
	static boolean[][] visit;
	static int[] di = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[] dj = { -1, 0, 1, 1, 1, 0, -1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 모든 방향에 대한 DFS를 한다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		while (w != 0 && h != 0) {
			// input
			map = new int[h][w];
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// check
			visit = new boolean[h][w];
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (map[i][j] == 0 && !visit[i][j])
						visit[i][j] = true;
					else if (map[i][j] == 1 && !visit[i][j]) {
						visit[i][j] = true;
						res++;
						dfs(i, j);
					}
				}
			}
			System.out.println(res);
			// input
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			res = 0;
		}
	}


	static int res = 0;

	static void dfs(int x, int y) {
		for (int i = 0; i < 8; i++) {
			int nexti = x + di[i];
			int nextj = y + dj[i];

			if (nexti >= 0 && nexti < h && nextj >= 0 && nextj < w && !visit[nexti][nextj] && map[nexti][nextj] == 1) {
				visit[nexti][nextj] = true;
				dfs(nexti, nextj);
			}
		}
	}
}
