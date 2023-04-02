package day0402;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_2589_보물섬 {
	static char[][] map;
	static int n,m,time;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,1,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new char[n][m];
		int ans = Integer.MIN_VALUE;
		
		for(int i=0; i<n; i++) {
			String s = br.readLine();
			for(int j=0; j<m; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j] == 'L') {
					bfs(i,j);
					ans = Math.max(ans, time);
				}
			}
		}
		
		System.out.println(ans);
	}
	private static void bfs(int i, int j) {
		Queue<point> q = new ArrayDeque<>();
		boolean[][] visit = new boolean[n][m];
		q.add(new point(i, j));
		visit[i][j] = true;
		time = 0;
		
		while(!q.isEmpty()) {
			int size = q.size();
			int flag = 0;
			for(int t=0; t<size; t++) {
				point now = q.poll();
				for(int d =0; d<4; d++) {
					int nr = now.i + dr[d];
					int nc = now.j + dc[d];
					
					if(nr<n && nc<m && nr>=0 &&nc>=0&& !visit[nr][nc] && map[nr][nc]=='L') {
						q.add(new point(nr, nc));
						visit[nr][nc] = true;
						flag = 1;
					}
				}
			}
			if(flag==1) time++;
		}
	}
	
	static class point{
		int i, j;
		public point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
