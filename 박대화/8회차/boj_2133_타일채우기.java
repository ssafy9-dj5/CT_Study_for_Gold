import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

		int[] d = new int[n + 1];

		d[0] = 1;
		for (int i = 2; i <= n; i += 2) {
			for (int j = 4; j <= i; j += 2) {
				d[i] += d[i - j] * 2;
			}
			d[i] += d[i - 2] * 3;
		}

		if (n % 2 == 1) {
			System.out.println(0);
		} else {
			System.out.println(d[n]);
		}

	}
}