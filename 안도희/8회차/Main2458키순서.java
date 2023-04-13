package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main2458Ű���� {
	static boolean visited[];
	static int cnt, n;
	static int[][] student;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());// �л���
		int m = Integer.parseInt(st.nextToken());// ������
		student = new int[n + 1][n + 1];
		int result = 0;

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			student[s][e] = 1; // s���� e�� �� ũ��!
		}

		for (int i = 1; i <= n; i++) {
			cnt = 0;
			visited = new boolean[n + 1];
			bdfs(i);// ������ �� ū��!
			sdfs(i);// ������ �� ������!
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
			if (student[num][i] == 1) {// num���� i�� �� ũ��!
				cnt++;// ������ ū�� ++
				bdfs(i);// i���� ū�Ÿ� num���ٵ� Ŭ�״� Ȯ���ϱ�
			}

		}
	}

	private static void sdfs(int num) {
		visited[num] = true;

		for (int i = 1; i <= n; i++) {
			if (visited[i])
				continue;
			if (student[i][num] == 1) {// num���� i�� �� ũ��!
				cnt++;// ������ ū�� ++
				sdfs(i);// i���� ū�Ÿ� num���ٵ� Ŭ�״� Ȯ���ϱ�
			}

		}
	}
}
