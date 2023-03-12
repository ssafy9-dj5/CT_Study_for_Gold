import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] disjoint;
	static int n, m;

	public static void makeSet() {
		disjoint = new int[n + 1];

		for (int i = 0; i <= n; i++) {
			disjoint[i] = i;
		}
	}

	public static int find(int num) {
		if (num == disjoint[num]) {
			return num;
		} else {
			return disjoint[num] = find(disjoint[num]);
		}
	}

	public static void union(int p1, int p2) {
		int a = find(p1);
		int b = find(p2);
		if (a == b) {
			return;
		}
		disjoint[b] = a;
	}

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		makeSet();

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());

			int union = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (union == 0) {
				union(a, b);
			} else {
				System.out.println(find(a) == find(b) ? "YES" : "NO");
			}
		}

	}
}