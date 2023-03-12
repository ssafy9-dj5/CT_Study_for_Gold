package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main1717집합의표현{
	static int[] disjoint;
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		disjoint = new int[n + 1];
		makeset();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int ope = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (ope == 0) {
                union(a,b);
				continue;
			}
			// ope==1 yes,no
			if (find(a)!=find(b))
				System.out.println("NO");
			else
				System.out.println("YES");

		}
System.out.println(Arrays.toString(disjoint));
	}

	private static void union(int a, int b) {
		int a1 = find(a);
		int b1 = find(b);
		if (a1 != b1) {
			disjoint[b1] = a1;
		}
	}

	private static int find(int a) {
		if (disjoint[a] == a)
			return a;
		return disjoint[a] = find(disjoint[a]);
	}

	private static void makeset() {
		for (int i = 0; i < n + 1; i++) {
			disjoint[i] = i;
		}

	}
}
