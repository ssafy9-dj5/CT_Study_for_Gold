import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static class Olym implements Comparable<Olym> {
		int d;
		int s;
		int e;

		public Olym() {
		}

		public Olym(int d, int s, int e) {
			this.d = d;
			this.s = s;
			this.e = e;
		}

		@Override
		public int compareTo(Olym o) {
			if (this.d > o.d) {
				return 1;
			} else if (this.d < o.d) {
				return -1;
			} else {
				if (this.e > o.e) {
					return 1;
				} else if (this.e < o.e) {
					return -1;
				} else {
					if (this.s > o.s) {
						return 1;
					} else if (this.s < o.s) {
						return -1;
					}
					return 0;
				}
			}
		}
	}

	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

		for (int t = 1; t <= n; t++) {
			int m = sc.nextInt();
			Olym[] o = new Olym[m];
			int count = 1;

			for (int i = 0; i < m; i++) {
				int d = sc.nextInt();
				int s = sc.nextInt();
				int e = sc.nextInt();
				o[i] = new Olym(d, s, e);
			}

			Arrays.sort(o);

			int index = 0;
			for (int i = 1; i < m; i++) {
				if (o[index].d == o[i].d && o[index].e <= o[i].s) {
					count++;
					index = i;
				} else if (o[index].d != o[i].d) {
					count++;
					index = i;
				}
			}

			System.out.println("Scenario #" + t + ":");
			System.out.println(count);
			System.out.println();
		}

	}
}