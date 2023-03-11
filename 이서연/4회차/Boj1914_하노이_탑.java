package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Boj1914_하노이_탑 {
	static int N;
	static StringBuilder sb;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		sb = new StringBuilder();

		BigInteger bigNum = new BigInteger("2");
		bigNum = bigNum.pow(N);
		bigNum = bigNum.subtract(new BigInteger("1"));

		sb.append(bigNum).append("\n");

		if (N <= 20)
			hanoi(N, 1, 2, 3);

		System.out.println(sb.toString());
	}

	private static void hanoi(int cnt, int from, int temp, int to) {
		if (cnt == 0)
			return;

		hanoi(cnt - 1, from, to, temp);
		sb.append(from).append(" ").append(to).append("\n");
		hanoi(cnt - 1, temp, from, to);
	}
}
