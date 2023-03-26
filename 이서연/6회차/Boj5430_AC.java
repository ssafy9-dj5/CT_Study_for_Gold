package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj5430_AC {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			char[] p = br.readLine().toCharArray();
			int n = Integer.parseInt(br.readLine());
			char[] input = br.readLine().toCharArray();
			int[] numArr = new int[n];

			int idx = 0;
			int num = 0;
			for (int i = 1; i < input.length; i++) {
				if (input[i] >= '0' && input[i] <= '9') {
					num = num * 10 + (input[i] - '0');
				} else if (input[i] == ',' || input[i] == ']') {
					if (num != 0) { // 입력되는 수는 1 이상 (0이면 저장하지 않음)
						numArr[idx] = num;
						idx++;
						num = 0;
					}
					if (input[i] == ']')
						break;
				}
			}

			boolean reverse = false;
			boolean result = true;
			int si = 0;
			int ei = n - 1;
			for (int i = 0; i < p.length; i++) {
				if (p[i] == 'R') {
					reverse = !reverse;
				} else if (p[i] == 'D') {
					if (si > ei) {
						result = false;
						break;
					}
					if (reverse) {
						ei--;
					} else {
						si++;
					}
				}
			}

			if (!result) {
				sb.append("error");
			} else {
				sb.append('[');
				if (!reverse) {
					for (int i = si; i <= ei; i++) {
						sb.append(numArr[i]);
						if (i != ei) {
							sb.append(',');
						}
					}
				} else {
					for (int i = ei; i >= si; i--) {
						sb.append(numArr[i]);
						if (i != si) {
							sb.append(',');
						}
					}
				}
				sb.append(']');
			}
			sb.append('\n');
		}

		System.out.println(sb.toString());
	}
}
