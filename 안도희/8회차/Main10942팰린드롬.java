package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main10942팰린드롬 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n + 1];
		StringBuilder sb = new StringBuilder();
		int[][] check = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			Arrays.fill(check[i], 1);
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = n - 1; i >= 1; i--) {
			for (int j = i; j <= n; j++) {
				if (arr[i] != arr[j])
					check[i][j] = 0;
				else if (check[i + 1][j - 1] == 0)
					check[i][j] = 0;
			}
		}
		int tc = Integer.parseInt(br.readLine());
		for (int t = 0; t < tc; t++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			sb.append(check[s][e] + "\n");//sb사용했더니 시간초과 해결
		}
		System.out.println(sb);
	}

}
