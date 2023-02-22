import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n][2];

		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}

		int m = Integer.parseInt(br.readLine());

		int[][] map = new int[n][m + 1];

		for (int i = 0; i < n; i++) {
			for (int j = 1; j <= m; j++) {
				map[i][j] = (arr[i][0] * j) + arr[i][1];
			}
		}

		boolean ok = false;

		for (int k = 1; k <= m - n + 1; k++) {
			int sum = 0;
			int t = map[0][k];
			sum += k;
			out: for (int i = 1; i < n; i++) {
				for (int j = 1; j <= m; j++) {
					if (map[i][j] > t) {
						break out;
					} else if (map[i][j] == t) {
						sum += j;
						break;
					}
				}
			}
			if (sum == m) {
				ok = true;
				System.out.println(t);
				break;
			}
		}
		if (!ok) {
			System.out.println(0);
		}

		br.close();
	}
}
