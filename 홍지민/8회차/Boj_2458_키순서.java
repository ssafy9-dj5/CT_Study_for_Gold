package day0409;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_2458_키순서 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] D = new int[n + 1][n + 1];
		int[] in = new int[n + 1];
		int[] out = new int[n + 1];

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			D[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i != j && D[i][j] == 0) {
					D[i][j] = 501;
				}
			}
		}

		for (int k = 1; k <= n; k++) {
			for (int j = 1; j <= n; j++) {
				for (int i = 1; i <= n; i++) {
					D[i][j] = Math.min(D[i][k] + D[k][j], D[i][j]);
				}
			}
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (D[j][i] != 0 && D[j][i] != 501) {
					in[i]++;
				}
				if (D[i][j] != 0 && D[i][j] != 501) {
					out[i]++;
				}
			}
		}

		int ans = 0;
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			sum = in[i] + out[i];
			if (sum == n - 1)
				ans++;
		}

		System.out.println(ans);

	}
}
