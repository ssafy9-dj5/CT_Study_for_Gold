

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class Boj_14502_연구소 {
	static int n, m, ans;
	static int[][] map, tmp;
	static int[] num;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();

		map = new int[n][m];
		num = new int[6];
		ans = Integer.MIN_VALUE;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		Perm(0);

		System.out.println(ans);

	}

	private static void Perm(int cnt) {
		if (cnt == 3) {
			comb(3, 0);
			return;
		}

		for (int i = 0; i < n; i++) {
			num[cnt] = i;
			Perm(cnt + 1);
		}

	}

	private static void comb(int cnt, int start) {
		if (cnt == 6) {
			tmp = new int[n][m];
			tmp = deepcopy(map);
			make();
			return;
		}

		for (int i = start; i < m; i++) {
			num[cnt] = i;
			comb(cnt + 1, i);
		}

	}

	private static void make() {
		int cnt = 0;
		for (int i = 0; i < 3; i++) {
			int r = num[i];
			int c = num[i + 3];

			if (tmp[r][c] == 0) {
				tmp[r][c] = 3;
				cnt++;
			}
		}

		if (cnt < 3) {
			return;
		}
		
		
		virus();
		count();

	}

	private static void count() {
		int cnt = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(tmp[i][j] == 0) cnt++;
			}
		}
		
		ans = Math.max(ans, cnt);
		
	}

	private static void virus() {
		Queue<Point> q = new ArrayDeque<>();
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(tmp[i][j] == 2) {
					q.offer(new Point(i,j));
				}
			}
		}
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			for(int d=0; d<4; d++) {
				int nr = now.r + dr[d];
				int nc = now.c + dc[d];
				
				if(check(nr, nc) && tmp[nr][nc] == 0) {
					q.add(new Point(nr, nc));
					tmp[nr][nc] = 2;
				}
			}
		}
		


	}

	private static boolean check(int r, int c) {
		return r >= 0 && r < n && c >= 0 && c < m;
	}

	static int[][] deepcopy(int[][] arr) {
		int[][] tmp = new int[arr.length][arr[0].length];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				tmp[i][j] = arr[i][j];
			}
		}

		return tmp;
	}

	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;

		}
	}

}
