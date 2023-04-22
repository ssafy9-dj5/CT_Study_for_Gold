import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static int[] arr;
	static int[] segtree;
	static int[] segtreemin;
	static StringBuilder sb;

	public static int initmax(int start, int end, int node) {
		if (start == end)
			return segtree[node] = arr[start];

		int mid = (start + end) / 2;
		return segtree[node] = Math.max(initmax(start, mid, node * 2), initmax(mid + 1, end, node * 2 + 1));
	}

	public static int initmin(int start, int end, int node) {
		if (start == end)
			return segtreemin[node] = arr[start];

		int mid = (start + end) / 2;
		return segtreemin[node] = Math.min(initmin(start, mid, node * 2), initmin(mid + 1, end, node * 2 + 1));
	}

	public static int findMax(int start, int end, int left, int right, int node) {
		if (left > end || right < start) {
			return 0;
		}

		if (left <= start && right >= end) {
			return segtree[node];
		}

		int mid = (start + end) / 2;
		return Math.max(findMax(start, mid, left, right, node * 2), findMax(mid + 1, end, left, right, node * 2 + 1));
	}

	public static int findMin(int start, int end, int left, int right, int node) {
		if (left > end || right < start) {
			return Integer.MAX_VALUE;
		}

		if (left <= start && right >= end) {
			return segtreemin[node];
		}

		int mid = (start + end) / 2;
		return Math.min(findMin(start, mid, left, right, node * 2), findMin(mid + 1, end, left, right, node * 2 + 1));
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new int[n + 1];
		segtree = new int[n * 4];
		segtreemin = new int[n * 4];

		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		initmax(1, n, 1);
		initmin(1, n, 1);

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			int min = findMin(1, n, a, b, 1);
			sb.append(min + " ");

			int max = findMax(1, n, a, b, 1);
			sb.append(max + "\n");
		}

		System.out.println(sb);
	}
}
