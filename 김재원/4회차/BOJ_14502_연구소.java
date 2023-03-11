package day0303;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 일부칸에 바이러스 존재
// 바이러스는 4방 확산
// 1. 3개를 뽑아 벽을 만든다 -> O
// 2. 바이러스 검색 ->
// 3. 최대값 업데이트
public class BOJ_14502_연구소 {
	static int n;
	static int m;
	static int maxSafeZone = Integer.MIN_VALUE;
	static int[][] lab; // 연구소
	static Point[] point; // lab의 좌표를 저장하는 새로운 point
	static int[] wall; // 벽을 세운 lab의 좌표를 저장하는 point의 index를 저장
	static int[] virus;

	public static void main(String[] args) throws IOException {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		lab = new int[n][m];
		point = new Point[n * m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				lab[i][j] = Integer.parseInt(st.nextToken());
				point[i * m + j] = new Point(i, j, lab[i][j]);
			}
		}
		// logic
		// 1. 벽 생성
		wall = new int[3];
		buildWall(0, 0);
		// output
		System.out.println(maxSafeZone);
	}

	static class Point {
		int x, y, state;

		public Point(int x, int y, int state) {
			super();
			this.x = x;
			this.y = y;
			this.state = state;
		}
	}

	static void buildWall(int ptIdx, int wallIdx) {
		if (wallIdx == 3) {
			int[][] labCopy = new int[n][m];
			deepCopy(lab, labCopy);

			for (int idx : wall) {
				// System.out.println(point[idx].x + " " + point[idx].y);
				labCopy[point[idx].x][point[idx].y] = 1;
			}
			// 2번 확산
			for (int i = 0; i < n * m; i++) {
				if (point[i].state == 2) {
					spread(point[i].x, point[i].y, labCopy);
				}
			}
			// 확산 후 test
//			for (int i = 0; i < n; i++) {
//				for (int j = 0; j < m; j++) {
//					System.out.print(labCopy[i][j] + " ");
//				}
//				System.out.println();
//			}
			// 3번 검사
			int tmpSafeZone = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (labCopy[i][j] == 0) {
						tmpSafeZone++;
					}
				}
			}
			maxSafeZone = Math.max(maxSafeZone, tmpSafeZone);
			return;
		}
		if (ptIdx >= n * m || wallIdx >= 3) {
			return;
		}

		if (point[ptIdx].state == 0) {
			wall[wallIdx] = ptIdx;
			buildWall(ptIdx + 1, wallIdx + 1);
		}

		buildWall(ptIdx + 1, wallIdx);
	}

	static void deepCopy(int[][] src, int[][] copy) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				copy[i][j] = src[i][j];
			}
		}
	}

	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { 1, -1, 0, 0 };

	static void spread(int i, int j, int[][] lab) {
		for (int d = 0; d < 4; d++) {
			int x = i + dx[d];
			int y = j + dy[d];
			if (x >= 0 && y >= 0 && x < n && y < m && lab[x][y] == 0) {
				lab[x][y] = 2;
				spread(x, y, lab);
			}
		}
	}

}
