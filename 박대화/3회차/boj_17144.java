
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int r, c, t;
	static int[][] map, mapcopy;
	static int upclean;
	static int downclean;

	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };

	public static void diffusion() {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] > 0) {
					int cnt = 0;
					int diff = map[i][j] / 5;
					for (int d = 0; d < 4; d++) {
						int nexti = i + di[d];
						int nextj = j + dj[d];

						if (nexti >= 0 && nexti < r && nextj >= 0 && nextj < c && map[nexti][nextj] != -1) {
							cnt++;
							mapcopy[nexti][nextj] += diff;
						}
					}
					mapcopy[i][j] += map[i][j] - (diff * cnt);
				}
			}
		}

		map = mapcopy;
	}

	public static void clean() {
		for (int i = upclean - 1; i > 0; i--) {
			map[i][0] = map[i - 1][0];
		}
		for (int j = 0; j < c - 1; j++) {
			map[0][j] = map[0][j + 1];
		}
		for (int i = 0; i < upclean; i++) {
			map[i][c - 1] = map[i + 1][c - 1];
		}
		for (int j = c - 1; j > 1; j--) {
			map[upclean][j] = map[upclean][j - 1];
		}
		map[upclean][1] = 0;
		// 위쪽 청정기

		for (int i = downclean + 1; i < r - 1; i++) {
			map[i][0] = map[i + 1][0];
		}
		for (int j = 0; j < c - 1; j++) {
			map[r - 1][j] = map[r - 1][j + 1];
		}
		for (int i = r - 1; i > downclean; i--) {
			map[i][c - 1] = map[i - 1][c - 1];
		}
		for (int j = c - 1; j > 1; j--) {
			map[downclean][j] = map[downclean][j - 1];
		}
		map[downclean][1] = 0;
		// 아래쪽 청정기
	}

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		map = new int[r][c];

		int mcnt = 0;
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < c; j++) {
				int n = Integer.parseInt(st.nextToken());
				map[i][j] = n;
				if (n == -1) {
					if (mcnt == 0) {
						upclean = i;
						mcnt++;
					} else {
						downclean = i;
					}
				}
			}
		}

		for (int i = 0; i < t; i++) { // t초 반복
			mapcopy = new int[r][c];
			mapcopy[upclean][0] = -1;
			mapcopy[downclean][0] = -1;
			diffusion();
			clean();
		}

		int sum = 0;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] > 0) {
					sum += map[i][j];
				}
			}
		}

		System.out.println(sum);
	}
}