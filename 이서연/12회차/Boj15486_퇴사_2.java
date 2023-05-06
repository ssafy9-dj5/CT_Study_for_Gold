package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj15486_퇴사_2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] input = new int[N][2];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			input[i][0] = Integer.parseInt(st.nextToken());
			input[i][1] = Integer.parseInt(st.nextToken());
		}

		int[] memo = new int[N + 1];

		// i번째 일을 포함할 때와 포함하지 않을 때 중 큰 값
		for (int i = N - 1; i >= 0; i--) {
			memo[i] = memo[i + 1];
			if (i + input[i][0] <= N) {
				memo[i] = Math.max(input[i][1] + memo[i + input[i][0]], memo[i]);
			}
		}
		System.out.println(memo[0]);
	}
}
