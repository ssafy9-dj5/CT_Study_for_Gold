package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main2458키순서 {
	static boolean visited[];
	static int cnt, n;
	static int[][] student;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());// 학생수
		int m = Integer.parseInt(st.nextToken());// 정보수
		student = new int[n + 1][n + 1];
		int result = 0;

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			student[s][e] = 1; // s보다 e가 더 크다!
		}

		for (int i = 1; i <= n; i++) {
			cnt = 0;
			visited = new boolean[n + 1];
			bdfs(i);// 나보다 더 큰거!
			sdfs(i);// 나보다 더 작은거!
			if (cnt == n - 1)
				result++;

		}

		System.out.println(result);

	}

	private static void bdfs(int num) {
		visited[num] = true;

		for (int i = 1; i <= n; i++) {
			if (visited[i])
				continue;
			if (student[num][i] == 1) {// num보다 i가 더 크다!
				cnt++;// 나보다 큰거 ++
				bdfs(i);// i보다 큰거면 num보다도 클테니 확인하기
			}

		}
	}

	private static void sdfs(int num) {
		visited[num] = true;

		for (int i = 1; i <= n; i++) {
			if (visited[i])
				continue;
			if (student[i][num] == 1) {// num보다 i가 더 크다!
				cnt++;// 나보다 큰거 ++
				sdfs(i);// i보다 큰거면 num보다도 클테니 확인하기
			}

		}
	}
}
