package boj.g5;

import java.util.Arrays;
import java.util.Scanner;

public class Boj2293_동전_1 {
	static int N, K, count;
	static int[] coin;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();

		coin = new int[N];
		for (int i = 0; i < N; i++) {
			coin[i] = sc.nextInt();
		}

		Arrays.sort(coin);
		int[] D = new int[K + 1];

		for (int i = 0; i < K + 1; i++) {
			if (i % coin[0] == 0)
				D[i] = 1;
		}

		for (int c = 1; c < N; c++) {
			for (int i = 1; i <= K; i++) {
				if (i >= coin[c]) {
					D[i] += D[i - coin[c]];
				}
			}
		}
		System.out.println(D[K]);
	}
}
