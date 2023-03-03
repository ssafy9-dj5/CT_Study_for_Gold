package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main14502연구소 {
	static class wall { //벽 위치
		int i, j;

		public wall(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
	}

	static wall[] w = new wall[3];// 벽 조합
	static int n, m;
	static int[][] arr,copy;
	static int max =0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		copy = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		comb(0, 0, 0);
		System.out.println(max);
	}

	private static void comb(int cnt, int i, int j) {

		if (cnt == 3) {//3개 벽 조합 만들기
			for(int k=0;k<n;k++)
				copy[k] = Arrays.copyOf(arr[k], arr[k].length);
			virus();
			return;
		}

		while (true) {
			if (j == m) {
				if (i == n - 1)
					break;
				j = 0;
				i++;
			}
			if (arr[i][j] == 0) {
				w[cnt] = new wall(i, j);
				comb(cnt + 1, i, j + 1);
			}
			j++;

		}
	}

	private static void virus() {
		for(int i=0;i<3;i++) {//벽세우기
			int r = w[i].i;
			int c=w[i].j;
			copy[r][c] =1;
		}
		
		for(int i=0;i<n;i++) {//바이러스 퍼지기
			for(int j=0;j<m;j++) {
				if(copy[i][j]==2)
					dfs(i,j);
			}
		}

		int result =0;
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(copy[i][j]==0) result++;
			}
		}
		max = Math.max(max, result);
		
	}


	private static void dfs(int i, int j) {
		copy[i][j]=3;//방문한 바이러스는 3
		int[] di = {-1,0,1,0};
		int[] dj = {0,1,0,-1};
		
		for(int d=0;d<4;d++) {
			if(i+di[d]>=0 && i+di[d]<n &&j+dj[d]>=0 && j+dj[d]<m
					&& copy[i+di[d]][j+dj[d]]==0)
				dfs(i+di[d],j+dj[d]);
				
		}
	}
}
