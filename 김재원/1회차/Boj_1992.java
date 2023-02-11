package w1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_1992 {
	static StringBuilder sb = new StringBuilder();
	static int di[] = { 0, 0, 1, 1 };
	static int dj[] = { 0, 1, 0, 1 };
	static int arr[][]; // 화면을 담을 배열

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine()); // 화면의 길이
		arr = new int[n][n];

		for (int i = 0; i < n; i++) {
			String[] str = br.readLine().split("");
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
			}
		}

		func(0, 0, n);

		System.out.println(sb.toString());
	}

	static void func(int x, int y, int N) {
		int result = arr[x][y];
		result = checkCells(result, x, y, N);
		compressible(result, x, y, N);
		return;
	}

	static int checkCells(int result, int x, int y, int N) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[x][y] != arr[x + i][y + j]) {
					result = 2;
					break;
				}
			}
			if (result == 2)
				break;
		}
		return result;
	}

	static void compressible(int result, int x, int y, int N) {
		if (result == 2) {
			if (N <= 2) { // 한변의 크기가 2이므로 더이상 쪼갤 수 없을때
				sb.append("(");
				for (int i = 0; i < 4; i++)
					sb.append(String.valueOf(arr[x + di[i]][y + dj[i]]));
				sb.append(")");
			} else { // 한변의 크기가 N인 구역이 압축할 수 없어 더 쪼갤때
				sb.append("(");
				for (int k = 0; k < 4; k++) {
					int i = x + (N / 2 * di[k]);
					int j = y + (N / 2 * dj[k]);
					func(i, j, N / 2);
				}
				sb.append(")");
			}
		} else if (result == 1) {
			sb.append(String.valueOf(1));
		} else if (result == 0) {
			sb.append(String.valueOf(0));
		}
	}
}
