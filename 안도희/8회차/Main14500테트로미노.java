package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14500테트로미노 {
	static int[] di = { -1, 0, 1 };
	static int[] dj = { 0, 1, 0 };
	static int n, m, max;
	static boolean[][] visited;
	static int[][] arr;
	static int cm1, cm2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		visited = new boolean[n][m];

		max = 0;// 합의 최대값 구하기

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());

			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				//visited = new int[n][m]; 이렇게 하면 시간초과/ dfs하고 나오면서 방문체크 취소
				visited[i][j] = true;
				dfs(i, j, 1, arr[i][j]);
				visited[i][j] = false;
			}
		}
		System.out.println(max);
	}

	private static void dfs(int i, int j, int cnt, int sum) {

		if (cnt == 4) {//dfs로 연결된 테트로미노
			max = Math.max(max, sum);
			return;
		}
		
		for (int d = 0; d < 3; d++) {//왼쪽 부분은 겹쳐
			int nexti = i + di[d];
			int nextj = j + dj[d];

			if (nexti >= 0 && nexti < n && nextj >= 0 && nextj < m && !visited[nexti][nextj]) {// 범위안에
				if (cnt == 2) {//ㅏ,ㅓ,ㅗ,ㅜ 테트로미노
					if (Math.min(cm1, cm2) < arr[nexti][nextj]) {
						if (cm1 > cm2)
							cm2 = arr[nexti][nextj];
						else
							cm1 = arr[nexti][nextj];
					}
				}
				visited[nexti][nextj] = true;
				dfs(nexti, nextj, cnt + 1, sum + arr[nexti][nextj]);
				visited[nexti][nextj]=false;
			}
		}
		if (cnt == 2) {
			max = Math.max(max, sum + cm1 + cm2);
			cm1 = 0;
			cm2 = 0;
		}
	}
}
