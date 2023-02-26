package day0226;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_4963_섬의개수 {
	static int[] dr = { 1, -1, 0, 0, -1, 1, 1, -1 };
	static int[] dc = { 0, 0, 1, -1, 1, 1, -1, -1 };

	static int h, w;
	static int[][] map;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			if (w == 0 && h == 0) break;

			map = new int[h][w];
			visited = new boolean[h][w];

			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int cnt = 0;
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (!visited[i][j] && map[i][j] == 1) {
						dfs(i, j);
						cnt++;
					}
				}
			}

			System.out.println(cnt);
		}

	}
	
	private static void dfs(int r, int c) {
		visited[r][c] = true;
		
		for(int d=0; d<8; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr >= 0 && nc >= 0 && nr < h && nc < w && !visited[nr][nc] && map[nr][nc] == 1) {
				visited[nr][nc] = true;
				dfs(nr, nc);
			}
		}
	}
}

