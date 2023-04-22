package boj.g1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2357_최솟값과_최댓값 {
	static int[] num;
	static int[] min_tree, max_tree;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		num = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			num[i] = Integer.parseInt(br.readLine());
		}

		int h = (int) Math.ceil(Math.log(N) / Math.log(2)); // 트리 높이
		int treeSize = (int) Math.pow(2, h + 1); // 트리에 들어가는 노드의 개수는 2^(h+1) 미만 개
		min_tree = new int[treeSize];
		max_tree = new int[treeSize];

		initTree(min_tree, 1, 1, N, true);
		initTree(max_tree, 1, 1, N, false);

//		printTree(min_tree);
//		printTree(max_tree);
		//////////////////////////////////////////////
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			int min = getValue(min_tree, 1, 1, N, a, b, true);
			int max = getValue(max_tree, 1, 1, N, a, b, false);

			System.out.println(min + " " + max);
		}
	}

	public static int initTree(int[] tree, int node_idx, int start, int end, boolean isMinTree) {
		if (start == end) {
			return tree[node_idx] = num[start];
		} else {
			if (isMinTree)
				return tree[node_idx] = Math.min(initTree(tree, node_idx * 2, start, (start + end) / 2, isMinTree),
						initTree(tree, node_idx * 2 + 1, (start + end) / 2 + 1, end, isMinTree));
			else
				return tree[node_idx] = Math.max(initTree(tree, node_idx * 2, start, (start + end) / 2, isMinTree),
						initTree(tree, node_idx * 2 + 1, (start + end) / 2 + 1, end, isMinTree));
		}
	}

	public static void printTree(int[] tree) {
		for (int i = 1; i < tree.length; i++) {
			System.out.print(tree[i] + " ");
		}
		System.out.println();
	}

	public static int getValue(int[] tree, int node_idx, int start, int end, int left, int right, boolean isMinTree) {
		if (end < left || right < start) { // 노드가 가지는 값의 구간이 구하려는 구간에 속하지 않는 경우
			if (isMinTree)
				return Integer.MAX_VALUE;
			else
				return Integer.MIN_VALUE;
		}
		if (left <= start && end <= right) { // 노드가 가지는 값의 구간이 구하려는 구간에 완전히 포함되는 경우 (더 내려가지 않고 리턴)
			return tree[node_idx];
		}

		// 그 외의 경우 자식노드를 탐색해서 값을 리턴
		if (isMinTree)
			return Math.min(getValue(tree, node_idx * 2, start, (start + end) / 2, left, right, isMinTree),
					getValue(tree, node_idx * 2 + 1, (start + end) / 2 + 1, end, left, right, isMinTree));
		else
			return Math.max(getValue(tree, node_idx * 2, start, (start + end) / 2, left, right, isMinTree),
					getValue(tree, node_idx * 2 + 1, (start + end) / 2 + 1, end, left, right, isMinTree));
	}
}
