package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj17144_미세먼지_안녕 {
	static int R, C;
	static int[] cleaner;
	static int[][] map, nextMap;
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		cleaner = new int[2];
		int idx = 0;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1) {
					cleaner[idx] = i;
					idx++;
				}
			}
		}

		for (int t = 0; t < T; t++) {
			nextMap = new int[R][C];
			calcDust();
			clean();

			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					map[i][j] = nextMap[i][j];
				}
			}
		}

		int result = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == -1)
					continue;
				result += map[i][j];
			}
		}
		System.out.println(result);
	}

	private static void calcDust() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == -1) {
					nextMap[i][j] = -1;
				}
				if (map[i][j] > 0) {
					int d_num = 0;
					int d_amount = map[i][j] / 5;
					for (int d = 0; d < 4; d++) {
						int nexti = i + di[d];
						int nextj = j + dj[d];

						if (nexti >= 0 && nexti < R && nextj >= 0 && nextj < C && map[nexti][nextj] != -1) {
							d_num++;
							nextMap[nexti][nextj] += d_amount;
						}
					}
					nextMap[i][j] += (map[i][j] - (d_num * d_amount));
				}
			}
		}
	}

	private static void clean() {
		// 위쪽 순환
		for (int i = cleaner[0] - 1; i > 0; i--) {
			nextMap[i][0] = nextMap[i - 1][0];
		}
		for (int j = 0; j < C - 1; j++) {
			nextMap[0][j] = nextMap[0][j + 1];
		}
		for (int i = 0; i < cleaner[0]; i++) {
			nextMap[i][C - 1] = nextMap[i + 1][C - 1];
		}
		for (int j = C - 1; j > 1; j--) {
			nextMap[cleaner[0]][j] = nextMap[cleaner[0]][j - 1];
		}
		nextMap[cleaner[0]][1] = 0;

		// 아래쪽 순환
		for (int i = cleaner[1] + 1; i < R - 1; i++) {
			nextMap[i][0] = nextMap[i + 1][0];
		}
		for (int j = 0; j < C - 1; j++) {
			nextMap[R - 1][j] = nextMap[R - 1][j + 1];
		}
		for (int i = R - 1; i > cleaner[1]; i--) {
			nextMap[i][C - 1] = nextMap[i - 1][C - 1];
		}
		for (int j = C - 1; j > 1; j--) {
			nextMap[cleaner[1]][j] = nextMap[cleaner[1]][j - 1];
		}
		nextMap[cleaner[1]][1] = 0;
	}

}
