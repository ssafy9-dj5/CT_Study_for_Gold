import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R, C, M;
	static SharkPos[][] map, copy;
	static int result;

	static int[] di = { 0, -1, 1, 0, 0 };
	static int[] dj = { 0, 0, 0, 1, -1 };

	public static class SharkPos {
		int s;
		int d;
		int z;

		public SharkPos(int s, int d, int z) {
			this.s = s;
			this.d = d;
			this.z = z;
		}

		@Override
		public String toString() {
			return "SharkPos [s=" + s + ", d=" + d + ", z=" + z + "]";
		}
	}

	public static void fishing(int j) {
		for (int r = 1; r <= R; r++) { // 낚시
			if (map[r][j] != null) {
				result += map[r][j].z;
				map[r][j] = null;
				break;
			}
		}

		copy = new SharkPos[R + 1][C + 1];

		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				if (map[r][c] != null) {
					int move;
					if (map[r][c].d == 1 || map[r][c].d == 2) {
						move = map[r][c].s % (R * 2 - 2);
					} else {
						move = map[r][c].s % (C * 2 - 2);
					}

					int nowr = r;
					int nowc = c;
					for (int m = 0; m < move; m++) {
						int nextr = nowr + di[map[r][c].d];
						int nextc = nowc + dj[map[r][c].d];
						if (nextr > R) {
							map[r][c].d = 1;
							nextr = nowr + di[map[r][c].d];
							nextc = nowc + dj[map[r][c].d];
						} else if (nextr <= 0) {
							map[r][c].d = 2;
							nextr = nowr + di[map[r][c].d];
							nextc = nowc + dj[map[r][c].d];
						} else if (nextc > C) {
							map[r][c].d = 4;
							nextr = nowr + di[map[r][c].d];
							nextc = nowc + dj[map[r][c].d];
						} else if (nextc <= 0) {
							map[r][c].d = 3;
							nextr = nowr + di[map[r][c].d];
							nextc = nowc + dj[map[r][c].d];
						}
						nowr = nextr;
						nowc = nextc;
					}
					if (copy[nowr][nowc] == null || (copy[nowr][nowc] != null && map[r][c].z > copy[nowr][nowc].z)) {
						copy[nowr][nowc] = map[r][c];
					}
				}
			}
		}
		map = copy;
		// print();
	}

	public static void print() {
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				System.out.print(map[i][j] == null ? 0 + " " : 1 + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new SharkPos[R + 1][C + 1];
		result = 0;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());

			map[r][c] = new SharkPos(s, d, z);
		}
		// end input

		for (int j = 1; j <= C; j++) {
			fishing(j);
		}

		System.out.println(result);

	}
}
