package day0216;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12865 {
	static Luggage[] pack;
	static int n;
	static int k;
	static int[][] d;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		pack = new Luggage[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			pack[i] = new Luggage(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		d = new int[n][k + 1];
		System.out.println(dp(n - 1, k));
		
//		System.out.print(0+ " ");
//		for (int i = 0; i <= k; i++) {
//			System.out.print(i+ " ");
//		}
//		System.out.println();
//		for (int i = 0; i < n; i++) {
//			System.out.print(i + " ");
//			for (int j = 0; j <= k; j++) {
//				System.out.print(d[i][j] + " ");
//			}
//			System.out.println();
//		}
	}

	static class Luggage {
		int weight;
		int value;

		public Luggage(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}
	}

	static int dp(int idx, int cap) {
		if (idx < 0) {
			return 0;
		}

		int v = pack[idx].value;
		int w = pack[idx].weight;

		if (d[idx][cap] == 0) {
			if (w > cap) {
				d[idx][cap] = dp(idx - 1, cap);
			} else if (w <= cap) {
				d[idx][cap] = Math.max(dp(idx - 1, cap), dp(idx - 1, cap - w) + v);
			}
		}

		return d[idx][cap];
	}
}
