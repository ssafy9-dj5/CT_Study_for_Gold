package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj10942_팰린드롬 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] num = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		boolean[][] oddD = new boolean[N + 1][];
		boolean[][] evenD = new boolean[N][];

		for (int i = 1; i <= N; i++) {
			int len = Math.min(i - 1, N - i);
			oddD[i] = new boolean[len + 1];
			oddD[i][0] = true;
			for (int d = 1; d <= len; d++) {
				if (num[i - d] == num[i + d] && oddD[i][d - 1])
					oddD[i][d] = true;
				else
					break;
			}
		}

		for (int i = 1; i < N; i++) {
			int len = Math.min(i, N - i);
			evenD[i] = new boolean[len + 1];
			evenD[i][0] = true;
			for (int d = 1; d <= len; d++) {
				if (num[i - d + 1] == num[i + d] && evenD[i][d - 1])
					evenD[i][d] = true;
				else
					break;
			}
		}

		// 질문에 답하기
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int si = Integer.parseInt(st.nextToken());
			int ei = Integer.parseInt(st.nextToken());
			int mid = (si + ei) / 2;
			int d = ei - mid;
			if ((ei - si) % 2 == 0) { // 숫자 개수 홀수 개
				sb.append(oddD[mid][d] ? 1 : 0).append('\n');
			} else { // 숫자 개수 짝수개
				sb.append(evenD[mid][d] ? 1 : 0).append('\n');
			}
		}

		System.out.println(sb.toString());
	}
}
