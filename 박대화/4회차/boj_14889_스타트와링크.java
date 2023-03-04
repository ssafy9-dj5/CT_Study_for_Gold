import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static int n;
	static int[][] arr;
	static boolean[] result;
	static int min = Integer.MAX_VALUE;

	public static void comb(int idx, int cnt) {
		if (cnt == n / 2) {
			ArrayList<Integer> alist = new ArrayList<>();
			ArrayList<Integer> blist = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				if (result[i]) {
					alist.add(i);
				} else {
					blist.add(i);
				}
			}

			int suma = 0;
			int sumb = 0;
			for (int i = 0; i < alist.size(); i++) { // 조합
				for (int j = i + 1; j < alist.size(); j++) {
					suma += arr[alist.get(i)][alist.get(j)];
					suma += arr[alist.get(j)][alist.get(i)];
				}
			}

			for (int i = 0; i < blist.size(); i++) {
				for (int j = i + 1; j < blist.size(); j++) {
					sumb += arr[blist.get(i)][blist.get(j)];
					sumb += arr[blist.get(j)][blist.get(i)];
				}
			}

			int sub = Math.abs(suma - sumb);
			min = Math.min(min, sub);
			return;
		}
		if (idx == n) {
			return;
		}

		result[idx] = true;
		comb(idx + 1, cnt + 1);
		result[idx] = false;
		comb(idx + 1, cnt);
	}

	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		arr = new int[n][n];
		result = new boolean[n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		// end input

		comb(0, 0);

		System.out.println(min);
	}
}