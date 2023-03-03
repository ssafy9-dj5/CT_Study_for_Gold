package day0302;

import java.math.BigInteger;
import java.util.Scanner;

public class BOJ_1914_하노이탑 {
	static StringBuilder sb;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		// 2의 100승은 long도 불가
		BigInteger bigNumber = new BigInteger("2");
		bigNumber = bigNumber.pow(n);
		System.out.println(bigNumber.subtract(new BigInteger("1")));

		sb =new StringBuilder();
		if (n <= 20) {
			hanoi(n, 1, 3, 2);
			System.out.print(sb);
		}
	}

	static void hanoi(int n, int from, int to, int via) {
		if (n == 1) {
			sb.append(from).append(" ").append(to).append("\n");
		} else {
			hanoi(n - 1, from, via, to);
			sb.append(from).append(" ").append(to).append("\n");
			hanoi(n - 1, via, to, from);
		}
	}
}
