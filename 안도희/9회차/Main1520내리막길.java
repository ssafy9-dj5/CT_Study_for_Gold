package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main1520내리막길 {
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	static int m, n, cnt;
	static boolean visited[][];
	static int[][] arr, check;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());

		arr = new int[m][n]; 
		check = new int[m][n];
		visited = new boolean[m][n];

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0, 0);
		System.out.println(cnt);
	}

	private static int dfs(int i, int j) {

		if (i == m - 1 && j == n - 1) { 
			cnt++;
			check[i][j] = 1;
			return check[i][j]; 
		}
		for (int d = 0; d < 4; d++) {
			int nexti = i + di[d];
			int nextj = j + dj[d];
			
			if (nexti >= 0 && nextj >= 0 && nexti < m && nextj < n && !visited[nexti][nextj]) {

				if (arr[nexti][nextj] < arr[i][j]) {
					if (check[nexti][nextj] >= 1) { // 가능
						cnt+=check[nexti][nextj];
						check[i][j] +=check[nexti][nextj];
						continue;
					} else if (check[nexti][nextj] == -1) // 불가능
						continue;

					visited[i][j] = true;
					if(dfs(nexti, nextj)>=1)
						check[i][j] += check[nexti][nextj];
					visited[i][j] = false;

				}
			}
		}

		if (check[i][j] == 0)
			check[i][j] = -1;
		
//		for (int p = 0; p < m; p++) {
//			for (int q = 0; q < n; q++)
//				System.out.print(check[p][q] + " ");
//			System.out.println();
//		}
//		System.out.println("____________________________");
		
		
		return check[i][j];
	}

}
