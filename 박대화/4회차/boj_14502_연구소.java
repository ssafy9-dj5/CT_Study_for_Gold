import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static int[][] map, copy;
	static boolean[][] visited;
	static ArrayList<Virus> list;
	static int max = Integer.MIN_VALUE;

	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };

	public static class Virus {
		int r, c;

		public Virus(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void bfs() {
		for (int i = 0; i < n; i++) {
			copy[i] = Arrays.copyOf(map[i], m);
		}

		Queue<Virus> q = new ArrayDeque<>();

		for (int i = 0; i < list.size(); i++) {
			q.offer(list.get(i));
			copy[list.get(i).r][list.get(i).c] = 2;
		}

		while (!q.isEmpty()) {
			Virus cur = q.poll();

			for (int d = 0; d < 4; d++) {
				int nexti = cur.r + di[d];
				int nextj = cur.c + dj[d];

				if (nexti >= 0 && nexti < n && nextj >= 0 && nextj < m && copy[nexti][nextj] == 0) {
					q.offer(new Virus(nexti, nextj));
					copy[nexti][nextj] = 2;
				}
			}
		}
		max = Math.max(max, check());
	}

	public static int check() {
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (copy[i][j] == 0) {
					cnt++;
				}
			}
		}

		return cnt;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		copy = new int[n][m];
		list = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					list.add(new Virus(i, j));
				}
			}
		}
		// end input

		for (int i = 0; i < n * m; i++) {
			if (map[i / m][i % m] == 0) {
				map[i / m][i % m] = 1;
				for (int j = i + 1; j < n * m; j++) {
					if (map[j / m][j % m] == 0) {
						map[j / m][j % m] = 1;
						for (int k = j + 1; k < n * m; k++) {
							if (map[k / m][k % m] == 0) {
								// 벽 3개 골랐음
								map[k / m][k % m] = 1;
								bfs();
								map[k / m][k % m] = 0;
							}
						}
						map[j / m][j % m] = 0;
					}
				}
				map[i / m][i % m] = 0;
			}
		}

		System.out.println(max);

		br.close();
	}
}