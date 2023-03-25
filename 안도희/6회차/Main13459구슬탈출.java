package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main13459구슬탈출 {
	static class Ball {
		int ri, rj, bi, bj;

		public Ball(int ri, int rj, int bi, int bj) {
			this.ri = ri;
			this.rj = rj;
			this.bi = bi;
			this.bj = bj;
		}
	}

	static char[][] arr;
	static Queue<Ball> queue;
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		arr = new char[n][m];
		visited = new boolean[n][m];

		int ri = 0, rj = 0, bi = 0, bj = 0;

		for (int i = 0; i < n; i++) {// 보드 입력 받기
			String s = br.readLine();
			for (int j = 0; j < m; j++) {
				arr[i][j] = s.charAt(j);
				if (arr[i][j] == 'R') {// 빨간공 위치
					ri = i;
					rj = j;
					arr[i][j] = '.';
				}
				if (arr[i][j] == 'B') {// 파란 공 위치
					bi = i;
					bj = j;
					arr[i][j] = '.';
				}
			}
		}
		queue = new LinkedList<>();
		queue.add(new Ball(ri, rj, bi, bj));

		System.out.println(bfs());

	}

	private static int bfs() {
		int cnt = 0;
		while (!queue.isEmpty() && cnt < 10) {
			int num = queue.size();
			cnt++;

			for (int i = 0; i < num; i++) {
				Ball now = queue.poll();

				for (int d = 0; d < 4; d++) {
					int ri = now.ri;
					int rj = now.rj;
					int bi = now.bi;
					int bj = now.bj;

					// 빨간공
					int nexti = ri + di[d];
					int nextj = rj + dj[d];
					while (true) {
						if (arr[nexti][nextj] == '#') {
							break;
						}
						if (arr[nexti][nextj] == 'O') {// 빨간공이 구멍에 들어갔다!
							ri = nexti;
							rj = nextj;
							break;
						}
						ri = nexti;
						rj = nextj;
						nexti += di[d];
						nextj += dj[d];
					}

					// 파란공
					nexti = now.bi + di[d];
					nextj = now.bj + dj[d];
					while (true) {
						if (arr[nexti][nextj] == '#') {
							break;
						}
						if (arr[nexti][nextj] == 'O') {
							bi = nexti;
							bj = nextj;
							break;
						}
						bi = nexti;
						bj = nextj;
						nexti += di[d];
						nextj += dj[d];
					}

					// 빨, 파 자리 이동 완료
					if (ri == bi && rj == bj) {
						if (arr[ri][rj] == 'O') {

							continue;// 빨,파 둘다 구멍으로!
						}
						//공 끝까지 이동했을때  위치가 같으면  조정해주기
						if (now.ri != now.bi) {
							if (Math.abs(ri - now.ri) < Math.abs(bi - now.bi)) {
								bi -= di[d];
							} else {
								ri -= di[d];
							}
						}
						if (now.rj != now.bj) {
							if (Math.abs(rj - now.rj) < Math.abs(bj - now.bj)) {
								bj -= dj[d];
							} else {
								rj -= dj[d];
							}

						}

					}
					if (arr[bi][bj] == 'O')
						continue;
					if (arr[ri][rj] == 'O')
						return 1;// 빨간공만 구멍으로 !!


					queue.add(new Ball(ri, rj, bi, bj));
				}
			}
		}
		return 0;
	}
}
