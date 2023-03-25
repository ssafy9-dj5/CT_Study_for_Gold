package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main13459����Ż�� {
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

		for (int i = 0; i < n; i++) {// ���� �Է� �ޱ�
			String s = br.readLine();
			for (int j = 0; j < m; j++) {
				arr[i][j] = s.charAt(j);
				if (arr[i][j] == 'R') {// ������ ��ġ
					ri = i;
					rj = j;
					arr[i][j] = '.';
				}
				if (arr[i][j] == 'B') {// �Ķ� �� ��ġ
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

					// ������
					int nexti = ri + di[d];
					int nextj = rj + dj[d];
					while (true) {
						if (arr[nexti][nextj] == '#') {
							break;
						}
						if (arr[nexti][nextj] == 'O') {// �������� ���ۿ� ����!
							ri = nexti;
							rj = nextj;
							break;
						}
						ri = nexti;
						rj = nextj;
						nexti += di[d];
						nextj += dj[d];
					}

					// �Ķ���
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

					// ��, �� �ڸ� �̵� �Ϸ�
					if (ri == bi && rj == bj) {
						if (arr[ri][rj] == 'O') {

							continue;// ��,�� �Ѵ� ��������!
						}
						//�� ������ �̵�������  ��ġ�� ������  �������ֱ�
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
						return 1;// �������� �������� !!


					queue.add(new Ball(ri, rj, bi, bj));
				}
			}
		}
		return 0;
	}
}
