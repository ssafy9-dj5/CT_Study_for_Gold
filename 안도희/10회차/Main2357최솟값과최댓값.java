package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2357최솟값과최댓값 {
	static long treemax[], treemin[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());// 데이터의 개수
		int m = Integer.parseInt(st.nextToken());// 구간 쌍

		int size = 0;
		while (true) {// 트리의 사이즈 구하기
			if (Math.pow(2, size) >= n)
				break;
			size++;
		}

		treemax = new long[(int) (Math.pow(2, size) * 2)];
		treemin = new long[(int) (Math.pow(2, size) * 2)];
		int d = (int) Math.pow(2, size);

		for (int i = 0; i < n; i++) {// 원본 데이터 채우기
			long num = Long.parseLong(br.readLine());
			treemax[d + i] = num;
			treemin[d + i] = num;
		}

		initmax(d, n);// max값
		initmin(d, n);// min값
		// System.out.println(Arrays.toString(treemin));

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			findmin(d + s - 1, d + e - 1, 0);
			findmax(d + s - 1, d + e - 1, 0);

			System.out.println();

		}

	}

	private static void findmin(int s, int e, long min) {
		if (s == e) {
			if (min == 0)
				min = treemin[s];
			else
				min = Math.min(treemin[s], min);
			System.out.print(min + " ");
			return;
		} else if (s > e) {
			System.out.print(min + " ");
			return;
		}
		if (s % 2 == 1) {
			if (min == 0 || min > treemin[s])
				min = treemin[s];

		}
		if (e % 2 == 0) {
			if (min == 0 || min > treemin[e])
				min = treemin[e];
		}
		findmin((s + 1) / 2, (e - 1) / 2, min);

	}

	private static void findmax(int s, int e, long max) {
		if (s == e) {
			if (max == 0)
				max = treemax[s];
			else
				max = Math.max(treemax[s], max);
			System.out.print(max);
			return;
		} else if (s > e) {
			System.out.print(max);
			return;
		}
		if (s % 2 == 1) {
			if (max == 0 || max < treemax[s])
				max = treemax[s];

		}
		if (e % 2 == 0) {
			if (max == 0 || max < treemax[e])
				max = treemax[e];

		}
		findmax((s + 1) / 2, (e - 1) / 2, max);

	}

	private static void initmin(int s, int n) {
		for (int i = s; i < s + n; i++) {
			int idx = i / 2;
			while (idx != 0) {
				if (treemin[idx] == 0 || treemin[idx] > treemin[i]) {
					treemin[idx] = treemin[i];
					idx = idx / 2;
				} else
					break;
			}
		}

	}

	private static void initmax(int s, int n) {
		for (int i = s; i < s + n; i++) {
			int idx = i / 2;
			while (idx != 0) {
				if (treemax[idx] >= treemax[i])
					break;
				treemax[idx] = treemax[i];
				idx = idx / 2;
			}
		}

	}

}
