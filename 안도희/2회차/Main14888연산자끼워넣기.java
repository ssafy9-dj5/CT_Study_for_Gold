package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main14888연산자끼워넣기 {
	static int n;
	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;
	static int[] s;
	static int[] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[n];
		s = new int[n - 1];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int[] ope = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			ope[i] = Integer.parseInt(st.nextToken());
		}

		perm(0, ope);

		System.out.println(max);
		System.out.println(min);
	}

	private static void perm(int cnt, int[] ope) {
		if (cnt == n - 1) {
			int result = arr[0];
			for (int i = 0; i < n - 1; i++) {
				switch (s[i]) {
				case 0:
					result += arr[i + 1];
					break;
				case 1:
					result -= arr[i + 1];
					break;
				case 2:
					result *= arr[i + 1];
					break;
				case 3:
					result /= arr[i + 1];
					break;
				}

			}
			if (min > result)
				min = result;
			if (max < result)
				max = result;
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (ope[i] == 0)
				continue;
			s[cnt] = i;
			ope[i] -= 1;
			perm(cnt + 1, ope);
			ope[i] += 1;

		}

	}
}
