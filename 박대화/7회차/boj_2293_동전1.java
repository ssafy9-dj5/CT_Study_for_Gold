import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] d = new int[K + 1];
		int[] num = new int[N];

		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(br.readLine());
		}

		d[0] = 1;
		for (int i = 0; i < num.length; i++) {
			for (int j = 1; j <= K; j++) {
				if (j >= num[i]) {
					d[j] += d[j - num[i]];
				}
			}
		}

		System.out.println(d[K]);

	}
}