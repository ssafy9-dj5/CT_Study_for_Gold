package boj.g5;

import java.util.Scanner;

public class Boj1068_트리 {
	static int N;
	static int[][] tree;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		tree = new int[N][N];

		for (int j = 0; j < N; j++) {
			int parent = sc.nextInt();
			if (parent != -1)
				tree[parent][j] = 1;
		}

		int r = sc.nextInt();
		removeNode(r);

//		for (int i = 0; i < N; i++) {
//			if (tree[i] == null) {
//				System.out.println(tree[i]);
//				continue;
//			}
//			for (int j = 0; j < N; j++) {
//				System.out.print(tree[i][j] + " ");
//			}
//			System.out.println();
//		}

		int leafCnt = 0;
		for (int i = 0; i < N; i++) {
			if (tree[i] == null)
				continue;

			boolean isLeaf = true;
			for (int j = 0; j < N; j++) {
				if (tree[i][j] == 1) {
					isLeaf = false;
					break;
				}
			}
			if (isLeaf)
				leafCnt++;
		}

		System.out.println(leafCnt);
	}

	static void removeNode(int r) {
		for (int i = 0; i < N; i++) {
			if (tree[i] != null)
				tree[i][r] = -1;
		}
		for (int j = 0; j < N; j++) {
			if (tree[r][j] == 1)
				removeNode(j); // 지울 노드의 자식 노드 지우기
		}
		tree[r] = null;
	}
}
