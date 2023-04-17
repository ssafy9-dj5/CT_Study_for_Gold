package day0416;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_2042_구간합구하기 {
	static int n, m, k;
	static long[] map;
	static long[] tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		map = new long[n + 1];

		for (int i = 1; i <= n; ++i) {
			map[i] = Long.parseLong(br.readLine());
		}

		tree = new long[n * 4];
		init(1, n, 1);

		int t = m + k;
		while (t-- > 0) {
			st = new StringTokenizer(br.readLine());

			if (st.nextToken().equals("1")) {
				int idx = Integer.parseInt(st.nextToken());
				long value = Long.parseLong(st.nextToken());
				long diff = value - map[idx];
				update(1, n, 1, idx, diff);
			} else {
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				System.out.println(sum(1, n, start, end, 1));
			}
		}
	}

	static long init(int start, int end, int node) {
		if (start == end) {
			return tree[node] = map[start];
		}

		int mid = (start + end) / 2;
		return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, (node * 2) + 1);
	}

	static void update(int start, int end, int node, int idx, long diff) {
		if (idx < start || idx > end)
			return;
		tree[node] += diff;
		if (start == end)
			map[idx] = tree[node];
		if (start != end) {
			int mid = (start + end) / 2;
			update(start, mid, node * 2, idx, diff);
			update(mid + 1, end, (node * 2) + 1, idx, diff);
		}
	}

	static long sum(int start, int end, int left, int right, int node) {
		if ((left > end) || (right < start))
			return 0;
		else if ((left <= start) && (right >= end))
			return tree[node];
		else {
			int mid = (start + end) / 2;
			return sum(start, mid, left, right, node * 2) + sum(mid + 1, end, left, right, (node * 2) + 1);
		}
	}
}