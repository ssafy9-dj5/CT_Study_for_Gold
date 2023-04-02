import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());

		int[] arr = new int[n + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		// end input

		for (int i = 2; i <= n; i++) {
			arr[i] = arr[i - 1] + arr[i];
		}

		int head = 0;
		int tail = 0;

		int size = n + 1;

		while (true) {
			if (arr[head] - arr[tail] < s) {
				head++;
				if (head == n + 1) {
					break;
				}
			} else {
				size = Math.min(size, head - tail);
				tail++;
			}
		}

		System.out.println(size == n + 1 ? 0 : size);
	}
}
