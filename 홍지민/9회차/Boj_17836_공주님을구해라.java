package day0416;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_17836_공주님을구해라 {
	static int n, m, t, ans, flag;
	static int[][] map, tmp;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());

		map = new int[n + 1][m + 1];
		tmp = new int[n + 1][m + 1];
		ans = Integer.MAX_VALUE;
		flag = 0;

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

//        for(int i=1; i<=n; i++){
//            for(int j=1; j<=m; j++){
//                System.out.print(map[i][j]+" ");
//            }
//            System.out.println();
//        }
		bfs();
		if (flag == 0)
			System.out.println("Fail");
		else
			System.out.println(ans);

	}

	private static void bfs() {
		Queue<Pos> q = new ArrayDeque<>();
		boolean[][] visit = new boolean[n + 1][m + 1];

		q.add(new Pos(1, 1, 0));
		visit[1][1] = true;

		while (!q.isEmpty()) {
			Pos now = q.poll();
//            System.out.println("현재좌표:"+now.r +","+now.c);
//            System.out.println(map[now.r][now.c]);

			if (now.r == n && now.c == m) {
				ans = Math.min(ans, now.time);

//                System.out.println("갱신"+ans);
//                System.out.println("반환");
				flag = 1;
				break;
			}

			if (map[now.r][now.c] == 2) {
//            	System.out.println("item");
//            	System.out.println(now.time);
				item(now.r, now.c, now.time);
				continue;
			}

			if (now.time >= t) {
				break;
			}

			for (int d = 0; d < 4; d++) {
				int nr = now.r + dr[d];
				int nc = now.c + dc[d];
				if (nr <= n && nc <= m && nr > 0 && nc > 0 && !visit[nr][nc] && map[nr][nc] != 1) {
					q.add(new Pos(nr, nc, now.time + 1));
					visit[nr][nc] = true;
				}
			}
		}
	}

	private static void item(int i, int j, int time2) {
		Queue<Pos> q = new ArrayDeque<>();
		boolean[][] visit = new boolean[n + 1][m + 1];

		q.add(new Pos(i, j, time2));
		visit[i][j] = true;

//        System.out.println("=================");

		while (!q.isEmpty()) {
			Pos now = q.poll();
//            System.out.println("현재좌표:"+now.r +","+now.c);
//            System.out.println(now.time);

			if (now.r == n && now.c == m) {
				ans = Math.min(ans, now.time);
//                System.out.println("갱신"+ans);
				flag = 1;
				break;
			}

			if (now.time >= t) {
				break;
			}

			for (int d = 0; d < 4; d++) {
				int nr = now.r + dr[d];
				int nc = now.c + dc[d];
				if (nr > 0 && nc > 0 && nr <= n && nc <= m && !visit[nr][nc]) {
					q.add(new Pos(nr, nc, now.time + 1));
					visit[nr][nc] = true;
				}
			}
		}
	}

	static class Pos {
		int r, c, time;

		public Pos(int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}
}
