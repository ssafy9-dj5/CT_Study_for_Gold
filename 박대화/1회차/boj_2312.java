import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int test = Integer.parseInt(br.readLine());

		for (int t = 0; t < test; t++) {
			StringBuilder sb = new StringBuilder();
			int n = Integer.parseInt(br.readLine());

			int[] arr = new int[n + 1];

			for (int i = 2; i <= n; i++) {
				int cnt = 0;
				while (n % i == 0) {
					n /= i;
					cnt++;
				}

				if (cnt != 0) {
					sb.append(i + " " + cnt + "\n");
				}
			}

			System.out.print(sb.toString());

		}

		br.close();
	}
}