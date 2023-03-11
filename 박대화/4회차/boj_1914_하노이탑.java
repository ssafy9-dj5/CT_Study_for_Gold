import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	static int n;
	static StringBuilder sb = new StringBuilder();

	public static void hanoi(int num, int now, int dest) {
		if (num == 0) {
			return;
		}

		int temp = 6 - now - dest;

		hanoi(num - 1, now, temp);
		sb.append(now + " " + dest + "\n");
		hanoi(num - 1, temp, dest);

	}

	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		BigInteger[] d = new BigInteger[n + 1];
		d[0] = BigInteger.valueOf(0l);

		for (int i = 1; i <= n; i++) {
			d[i] = d[i - 1].multiply(new BigInteger("2")).add(new BigInteger("1"));
		}

		if (n <= 20) {
			hanoi(n, 1, 3);
		}

		System.out.println(d[n]);
		if (n <= 20) {
			System.out.println(sb.toString());
		}
	}
}