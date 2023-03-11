package day0303;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17070_파이프옮기기 {
	static int n;
	static int res = 0;
	static int arr[][];
	static int dx[] = { 0, 1, 1 };
	static int dy[] = { 1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// input
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		StringTokenizer st;
		for (int i = 0; i < arr.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < arr.length; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// check
		// 가로 0, 세로 1, 대각선 2
		dfs(0, 1, 0);
		// output
		System.out.println(res);
	}

	static void dfs(int x, int y, int d) {
		// System.out.println(x + " " + y);
		if (x == n - 1 && y == n - 1) {
			res++;
			// System.out.println();
			return;
		}

		if ((d == 0 || d == 2) && x + dx[0] <= n - 1 && y + dy[0] <=  n - 1) {
			if (arr[x + dx[0]][y + dy[0]] != 1)
				dfs(x + dx[0], y + dy[0], 0);
		}
		if ((d == 1 || d == 2) && x + dx[1] <= n - 1 && y + dy[1] <=  n - 1) {
			if (arr[x + dx[1]][y + dy[1]] != 1)
				dfs(x + dx[1], y + dy[1], 1);
		}
		if (x + dx[2] <=  n - 1 && y + dy[2] <= n - 1 && 
				arr[x + dx[2]][y + dy[2]] != 1 && arr[x + dx[1]][y + dy[1]] != 1 && arr[x + dx[0]][y + dy[0]] != 1)
			dfs(x + dx[2], y + dy[2], 2);
	}
}
