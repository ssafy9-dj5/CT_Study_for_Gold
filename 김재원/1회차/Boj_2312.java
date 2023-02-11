package w1;

import java.util.Scanner;

public class Boj_2312 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int TC = sc.nextInt();

		for (int tc = 0; tc < TC; tc++) {
			int n = sc.nextInt();
			int num = n;
			int prime = 2;
			int cnt = 0;
			while (prime <= n) {
				if (num % prime == 0) {
					cnt++;
					num = num / prime;
				} else if (cnt > 0) {
					System.out.println(prime + " " + cnt);
					cnt = 0;
					prime += 1;
				} else {
					cnt = 0;
					prime += 1;
				}
			}
		}
	}
}
