package boj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj4963_섬의_개수_DFS {
	static int N, M;
	static int[] di = { -1, 1, 0, 0, -1, -1, 1, 1 };
	static int[] dj = { 0, 0, -1, 1, -1, 1, -1, 1 };
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			if (N == 0 && M == 0)
				break;

			map = new int[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int count = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 1) {
						count++;
						dfs(i, j);
					}
				}
			}
			System.out.println(count);
		}
	}

	static void dfs(int i, int j) {
		map[i][j] = 0; // 방문 체크
		
		for (int d = 0; d < 8; d++) {
			int ni = i + di[d];
			int nj = j + dj[d];

			if (ni >= 0 && ni < N && nj >= 0 && nj < M && map[ni][nj] == 1) {
				dfs(ni, nj);
			}
		}
	}
}
