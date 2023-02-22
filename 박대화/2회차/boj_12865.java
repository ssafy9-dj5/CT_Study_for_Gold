import java.util.Scanner;

public class Main {
	public static void main(String args[]) throws Exception { // 못풀었어요...
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int k = sc.nextInt();

		int[][] d = new int[n + 1][k + 1];
		int[] w = new int[n + 1];
		int[] v = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			w[i] = sc.nextInt();
			v[i] = sc.nextInt();
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= k; j++) {
				if (j - w[i] >= 0) {
					d[i][j] = Math.max(d[i - 1][j], d[i - 1][j - w[i]] + v[i]);
				} else {
					d[i][j] = d[i - 1][j];
				}
			}
		}

		System.out.println(d[n][k]);

	}
}