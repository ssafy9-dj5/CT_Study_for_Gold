package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main17070�������ű��1 {
	static int n;
	static int[][] arr;
	static int cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n + 1][n + 1];

		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		pipe(1, 2, 1);
		System.out.println(cnt);
	}

	private static void pipe(int i, int j, int p) {// �� �ε���, ���� ����1,����2,�밢��3
		if (i == n && j == n) {//(n,n) ����
			cnt++;
			return;
		}
		for (int d = 1; d <= 3; d++) { // ����,����,�밢��
			switch (d) {
			case 1:// ����
				if ((p == 1 || p == 3) && j + 1 <= n && arr[i][j + 1] == 0)
					pipe(i, j + 1, 1);

				break;
			case 2:// ����
				if ((p == 2 || p == 3) && i + 1 <= n && arr[i + 1][j] == 0)
					pipe(i + 1, j, 2);

				break;
			case 3:// �밢��
				if (i + 1 <= n && j + 1 <= n && 
				arr[i + 1][j + 1] == 0 && arr[i+1][j]==0 && arr[i][j+1]==0) {
					pipe(i + 1, j + 1, 3);
				}

				break;

			}

		}

	}
}
