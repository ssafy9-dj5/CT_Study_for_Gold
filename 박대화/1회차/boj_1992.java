import java.util.Scanner;

public class Main {
	public static void loop(int[][] map, int xs, int xe, int ys, int ye) {
		if (xe - xs == 0) {
			if (map[ys][xs] == 0)
				System.out.print(0);
			else
				System.out.print(1);
			return;
		}

		boolean one = true;
		boolean zero = false;
		if (map[ys][xs] == 0) {
			one = false;
			zero = true;
		}

		out: for (int i = ys; i <= ye; i++) {
			for (int j = xs; j <= xe; j++) {
				if (map[i][j] == 1 && zero) {
					zero = false;
					break out;
				} else if (map[i][j] == 0 && one) {
					one = false;
					break out;
				}
			}
		}
		if (one) {
			System.out.print(1);
			return;
		}
		if (zero) {
			System.out.print(0);
			return;
		}

		System.out.print("(");
		loop(map, xs, xe - ((xe - xs) / 2) - 1, ys, ye - ((ye - ys) / 2) - 1);
		loop(map, xe - ((xe - xs) / 2), xe, ys, ye - ((ye - ys) / 2) - 1);
		loop(map, xs, xe - ((xe - xs) / 2) - 1, ye - ((ye - ys) / 2), ye);
		loop(map, xe - ((xe - xs) / 2), xe, ye - ((ye - ys) / 2), ye);
		System.out.print(")");
	}

	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int[][] map = new int[n][n];
		for (int i = 0; i < n; i++) {
			char[] ch = sc.next().toCharArray();
			for (int j = 0; j < n; j++) {
				map[i][j] = (int) ch[j] - '0';
			}
		}

		loop(map, 0, n - 1, 0, n - 1);
	}
}
