package day0226;

import java.util.Scanner;

public class BOJ_1086 {
	static int n;
	static int[] arr;
	static int leavesCnt = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		arr = new int[n];
		int rootNode = 0;
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
			if (arr[i] == -1)
				rootNode = i;
		}
		int d = sc.nextInt();

		dfsDel(d);
		dfsLeaf(rootNode);
		System.out.println(leavesCnt);

	}

	private static void dfsDel(int d) {
		arr[d] = -2;
		for (int i = 0; i < n; i++) {
			if (arr[i] == d) {
				dfsDel(i);
			}
		}
	}

	private static void dfsLeaf(int index) {
		boolean imNotLeaf = false;
		if (arr[index] != -2) {
			for (int i = 0; i < n; i++) {
				if (arr[i] == index) {
					imNotLeaf = true;
					dfsLeaf(i);
				}
			}
			if (!imNotLeaf) {
				leavesCnt++;
			}
		}
	}
}