package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1926±×¸² {
	static int[][] arr;
	static int s;
	static int n;
	static int m;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		int total = 0;
		int max = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == 1) {
					s = 0;
					arr[i][j] = 2;
					find(i, j);
					s++;
					total++;
					
					if (max < s)
						max = s;
				}
			}
		}
		System.out.println(total);
		System.out.println(max);
	}

	private static void find(int i, int j) {
		int[] di = { 0, 0, -1, 1 };
		int[] dj = { -1, 1, 0, 0 };
		for (int d = 0; d < 4; d++) {
			if (i + di[d] >= 0 && i + di[d] < n && j + dj[d] >= 0 && j + dj[d] < m && arr[i + di[d]][j + dj[d]] == 1) {
				s++;
				arr[i + di[d]][j + dj[d]] = 2;
				find(i + di[d], j + dj[d]);

			}
		}

	}
}
