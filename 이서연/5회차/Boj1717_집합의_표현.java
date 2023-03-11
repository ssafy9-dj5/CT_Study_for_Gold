package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1717_집합의_표현 {
	static int N;
	static int[] disjoint;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		makeSet();
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (cmd == 0) {
				union(a, b);
			} else if (cmd == 1) {
				if (findSet(a) == findSet(b))
					System.out.println("YES");
				else
					System.out.println("NO");
			}
		}
	}

	private static int findSet(int a) {
		if (disjoint[a] == a)
			return a;

		return disjoint[a] = findSet(disjoint[a]);
	}

	private static void union(int a, int b) {
		int p1 = findSet(a);
		int p2 = findSet(b);

		if (p1 == p2)
			return;

		disjoint[p1] = p2;
	}

	private static void makeSet() {
		disjoint = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			disjoint[i] = i;
		}
	}
}
