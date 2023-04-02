import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


// 63%에서 계속 터져서 못풀음 ㅠ
public class Main {
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	static int N, M;
	static char[][] map;
	static boolean[][][][] visited;

	public static class Pos {
		int rx;
		int ry;
		int bx;
		int by;

		public Pos(int rx, int ry, int bx, int by) {
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
		}

		public Pos() {
		}
	}

	public static boolean bfs(int redx, int redy, int bluex, int bluey) {
		Queue<Pos> q = new ArrayDeque<>();

		q.offer(new Pos(redx, redy, bluex, bluey));
		visited[redx][redy][bluex][bluey] = true;

		int time = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int s = 0; s < size; s++) {
				Pos cur = q.poll();

				for (int d = 0; d < 4; d++) {
					// 기울이기
					boolean result = slide(cur, d, q);

					if (result) {
						return true;
					}
				}
			}

			time++;
			if (time > 10) {
				return false;
			}
		}

		return false;
	}

	public static boolean slide(Pos cur, int dir, Queue<Pos> q) {
		Pos nextCur = new Pos();

		boolean redstop = false;
		boolean bluestop = false;
		boolean redgoal = false;
		boolean bluegoal = false;

		int nowbx = cur.bx;
		int nowby = cur.by;
		int nowrx = cur.rx;
		int nowry = cur.ry;
		int nextrx = cur.rx;
		int nextry = cur.ry;
		int nextbx = cur.bx;
		int nextby = cur.by;
		while (true) {
			if (redstop && bluestop) {
				break;
			}

			if (!redstop) {
				nextrx = nowrx + di[dir];
				nextry = nowry + dj[dir];
				if (map[nextrx][nextry] == '#') {
					nextrx = nowrx;
					nextry = nowry;
					nextCur.rx = nowrx;
					nextCur.ry = nowry;
					redstop = true;
				}
			}
			if (!bluestop) {
				nextbx = nowbx + di[dir];
				nextby = nowby + dj[dir];
				if (map[nextbx][nextby] == '#') {
					nextbx = nowbx;
					nextby = nowby;
					nextCur.bx = nowbx;
					nextCur.by = nowby;
					bluestop = true;
				}
			}

			if (nextrx == nextbx && nextry == nextby) {
				nextCur.rx = nowrx;
				nextCur.ry = nowry;
				redstop = true;
			}
			if (nextbx == nextrx && nextby == nextry) {
				nextCur.bx = nowbx;
				nextCur.by = nowby;
				bluestop = true;
			}
			if (map[nextbx][nextby] == 'O') {
				bluestop = true;
				bluegoal = true;
				break;
			}

			if (map[nextrx][nextry] == 'O') {
				redstop = true;
				redgoal = true;
				nextrx = 0;
				nextry = 0;
			}

			if (!redstop) {
				nowrx = nextrx;
				nowry = nextry;
			}
			if (!bluestop) {
				nowbx = nextbx;
				nowby = nextby;
			}
		}

		if ((redgoal && bluegoal) || bluegoal) {
			return false;
		}

		if (redgoal) {
			return true;
		}

		if (!visited[nextCur.rx][nextCur.ry][nextCur.bx][nextCur.by]) {
			q.offer(nextCur);
			visited[nextCur.rx][nextCur.ry][nextCur.bx][nextCur.by] = true;
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		visited = new boolean[N][M][N][M];

		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		// end input

		int redx = 0;
		int redy = 0;
		int bluex = 0;
		int bluey = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'R') {
					redx = i;
					redy = j;
					map[i][j] = '.';
				} else if (map[i][j] == 'B') {
					bluex = i;
					bluey = j;
					map[i][j] = '.';
				}
			}
		}

		boolean ans = bfs(redx, redy, bluex, bluey);

		System.out.println(ans ? 1 : 0);

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
	}
}
