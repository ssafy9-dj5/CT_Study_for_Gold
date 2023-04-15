import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long[] arr;
	static long[] tree;

	public static long init(int start, int end, int node) {
		if (start == end)
			return tree[node] = arr[start];

		int mid = (start + end) / 2;
		return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
	}

	public static long sum(int start, int end, int node, int left, int right) {
		if (right < start || left > end)
			return 0;

		if (left <= start && right >= end)
			return tree[node];

		int mid = (start + end) / 2;
		return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
	}

	public static void modify(int start, int end, int node, int index, long num) {
		if (index < start || index > end)
			return;

		tree[node] += num;
		if (start == end)
			return;
		int mid = (start + end) / 2;
		modify(start, mid, node * 2, index, num);
		modify(mid + 1, end, node * 2 + 1, index, num);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		arr = new long[n];
		tree = new long[4 * n];

		for (int i = 0; i < n; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}

		init(0, n - 1, 1);

		for (int i = 0; i < m + k; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());

			if (a == 1) {
				// modify
				int b = Integer.parseInt(st.nextToken());
				long c = Long.parseLong(st.nextToken());
				modify(0, n - 1, 1, b - 1, c - arr[b - 1]);
				arr[b - 1] = c;
			} else {
				// sum
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				long result = sum(0, n - 1, 1, b - 1, c - 1);

				sb.append(result + "\n");
			}
		}

		System.out.println(sb.toString());
	}
}
