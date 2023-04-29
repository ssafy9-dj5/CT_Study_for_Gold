package day0423;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_2357_최솟값과최댓값 {
	static int n, m;
	static int[] map;
	static int[] treeMin, treeMax;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n + 1];
		treeMin = new int[n * 4];
		treeMax = new int[n * 4];

		for (int i = 1; i <= n; i++) {
			map[i] = Integer.parseInt(br.readLine());
		}

		initMin(1, n, 1);
		initMax(1, n, 1);

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			System.out.println(min(1, n, a, b, 1) + " " + max(1, n, a, b, 1));
		}
	}

	private static int initMin(int start, int end, int node) {
		if (start == end) {
			return treeMin[node] = map[start];
		}

		int mid = (start + end) / 2;
		return treeMin[node] = Math.min(initMin(start, mid, node * 2), initMin(mid + 1, end, (node * 2) + 1));
	}

	private static int initMax(int start, int end, int node) {
		if (start == end) {
			return treeMax[node] = map[start];
		}

		int mid = (start + end) / 2;
		return treeMax[node] = Math.max(initMax(start, mid, node * 2), initMax(mid + 1, end, (node * 2) + 1));
	}

	static int min(int start, int end, int left, int right, int node) {
		if ((left > end) || (right < start))
			return Integer.MAX_VALUE;
		else if ((left <= start) && (right >= end))
			return treeMin[node];
		else {
			int mid = (start + end) / 2;
			return Math.min(min(start, mid, left, right, node * 2), min(mid + 1, end, left, right, (node * 2) + 1));
		}
	}

	static int max(int start, int end, int left, int right, int node) {
		if ((left > end) || (right < start))
			return Integer.MIN_VALUE;
		else if ((left <= start) && (right >= end))
			return treeMax[node];
		else {
			int mid = (start + end) / 2;
			return Math.max(max(start, mid, left, right, node * 2), max(mid + 1, end, left, right, (node * 2) + 1));
		}
	}
}