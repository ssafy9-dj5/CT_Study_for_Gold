package boj.g1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj13459_구슬_탈출 {
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };
	static int N, M, hi, hj;
	static char[][] board;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new char[N][];
		int ri = -1;
		int rj = -1;
		int bi = -1;
		int bj = -1;
		hi = -1;
		hj = -1;
		for (int i = 0; i < N; i++) {
			board[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 'R') {
					ri = i;
					rj = j;
					board[i][j] = '.';
				} else if (board[i][j] == 'B') {
					bi = i;
					bj = j;
					board[i][j] = '.';
				} else if (board[i][j] == 'O') {
					hi = i;
					hj = j;
				}
			}
		}

		System.out.println(bfs(ri, rj, bi, bj));
	}

	private static int bfs(int ri, int rj, int bi, int bj) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { ri, rj, bi, bj });

		int[] now, next;
		int size;
		int cnt = 0;
		while (!queue.isEmpty()) {
			size = queue.size();

			if (cnt > 10)
				return 0;

			for (int s = 0; s < size; s++) {
				now = queue.poll();
//				System.out.println(cnt+"번째: "+ Arrays.toString(now));
				if (now[2] == hi && now[3] == hj) { // 파란 구슬이 구멍에 들어가는 경우
					continue;
				}
				if (now[0] == hi && now[1] == hj) { // 빨간 구슬을 10번 이하로 움직여서 구멍에 넣는 경우
					return 1;
				}
				for (int d = 0; d < 4; d++) {
					next = moveBoard(now, d);
					if (now[0] == next[0] && now[1] == next[1] && now[2] == next[2] && now[3] == next[3])
						continue;
					queue.add(next);
				}
			}
			cnt++;
		}
		return 0;
	}

	private static int[] moveBoard(int[] now, int dir) {
		int ri = now[0];
		int rj = now[1];
		int bi = now[2];
		int bj = now[3];

		int next_ri, next_rj, next_bi, next_bj;

		while (true) {
			next_ri = ri + di[dir];
			next_rj = rj + dj[dir];

			if (next_ri < 0 || next_ri >= N || next_rj < 0 || next_rj >= M || board[next_ri][next_rj] == '#') {
				break;
			}
			ri = next_ri;
			rj = next_rj;

			if (board[ri][rj] == 'O')
				break;
		}

		while (true) {
			next_bi = bi + di[dir];
			next_bj = bj + dj[dir];

			if (next_bi < 0 || next_bi >= N || next_bj < 0 || next_bj >= M || board[next_bi][next_bj] == '#') {
				break;
			}
			bi = next_bi;
			bj = next_bj;

			if (board[bi][bj] == 'O')
				break;
		}

		if ((ri == bi && rj == bj) && !(ri == hi && rj == hj)) { // 동시에 구멍에 들어가는 경우는 그대로
			switch (dir) {
			case 0:
				if (now[0] < now[2])
					bi++;
				else
					ri++;
				break;
			case 1:
				if (now[0] < now[2])
					ri--;
				else
					bi--;
				break;
			case 2:
				if (now[1] < now[3])
					bj++;
				else
					rj++;
				break;
			case 3:
				if (now[1] < now[3])
					rj--;
				else
					bj--;
				break;
			}
		}
		return new int[] { ri, rj, bi, bj };
	}
}
