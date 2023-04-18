package boj.g4;

import java.util.Scanner;

public class Boj2133_타일_채우기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] D = new int[N + 1];
		D[0] = 1;
		for (int i = 1; i <= N; i++) {
			if (i % 2 == 0) {
				D[i] += D[i - 2] * 3;
				for (int j = 4; j <= i; j += 2) {
					D[i] += D[i - j] * 2;
				}
			}
		}

		System.out.println(D[N]);
	}
}
