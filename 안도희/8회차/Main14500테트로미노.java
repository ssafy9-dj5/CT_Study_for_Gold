package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14500��Ʈ�ι̳� {
	static int[] di = { -1, 0, 1 };
	static int[] dj = { 0, 1, 0 };
	static int n, m, max;
	static int[][] visited;
	static int[][] arr;
	static int cm1, cm2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		visited = new int[n][m];

		max = 0;// ���� �ִ밪 ���ϱ�

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());

			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				//visited = new int[n][m]; �̷��� �ϸ� �ð��ʰ�/ dfs�ϰ� �����鼭 �湮üũ ���
				visited[i][j] = 5;
				dfs(i, j, 1, arr[i][j]);
				visited[i][j] = 0;
			}
		}
		System.out.println(max);
	}

	private static void dfs(int i, int j, int cnt, int sum) {

		if (cnt == 4) {
			max = Math.max(max, sum);
			return;
		}
		for (int d = 0; d < 3; d++) {
			int nexti = i + di[d];
			int nextj = j + dj[d];

			if (nexti >= 0 && nexti < n && nextj >= 0 && nextj < m && visited[nexti][nextj] == 0) {// �����ȿ�
				if (cnt == 2) {//��,��,��,��
					if (Math.min(cm1, cm2) < arr[nexti][nextj]) {
						if (cm1 > cm2)
							cm2 = arr[nexti][nextj];
						else
							cm1 = arr[nexti][nextj];
					}
				}
				visited[nexti][nextj] = cnt;
				dfs(nexti, nextj, cnt + 1, sum + arr[nexti][nextj]);
				visited[nexti][nextj]=0;
			}
		}
		if (cnt == 2) {
			max = Math.max(max, sum + cm1 + cm2);
			cm1 = 0;
			cm2 = 0;
		}
	}
}
