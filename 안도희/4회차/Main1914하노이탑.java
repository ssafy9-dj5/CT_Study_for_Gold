package week4;

import java.math.BigInteger;
import java.util.Scanner;

public class Main1914하노이탑 {
	static int cnt;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		if (n > 20)
			System.out.println((
					new BigInteger("2").pow(n).subtract(new BigInteger("1"))));
		else {
			hanoi(n, 1, 2, 3);
			System.out.println(cnt);
			System.out.println(sb);
		}
	}

	private static void hanoi(int n, int i, int j, int k) {
		cnt++;
		if (n == 1) {
			sb.append(i + " " + k + "\n");
		}

		else {
			hanoi(n - 1, i, k, j);
			sb.append(i + " " + k + "\n");
			hanoi(n - 1, j, i, k);
		}
	}
}
