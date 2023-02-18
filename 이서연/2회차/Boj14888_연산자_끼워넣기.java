package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj14888_연산자_끼워넣기 {
	static int N, maxAns, minAns;
	static int[] number, result;
	static ArrayList<Integer> operator;
	static boolean[] selected;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		number = new int[N];
		for (int i = 0; i < N; i++) {
			number[i] = Integer.parseInt(st.nextToken());
		}
		operator = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			int op_count = Integer.parseInt(st.nextToken());
			while (op_count > 0) {
				operator.add(i);
				op_count--;
			}
		}
		result = new int[N - 1];
		selected = new boolean[N - 1];
		maxAns = Integer.MIN_VALUE;
		minAns = Integer.MAX_VALUE;
		perm(0);
		System.out.println(maxAns);
		System.out.println(minAns);
	}

	static void perm(int idx) {
		if (idx == N - 1) {
			int answer = number[0];
			for (int i = 0; i < N - 1; i++) {
				switch (operator.get(result[i])) {
				case 0:
					answer += number[i + 1];
					break;
				case 1:
					answer -= number[i + 1];
					break;
				case 2:
					answer *= number[i + 1];
					break;
				case 3:
					answer /= number[i + 1];
					break;
				}
			}
			maxAns = Math.max(maxAns, answer);
			minAns = Math.min(minAns, answer);
			return;
		}

		for (int i = 0; i < N - 1; i++) {
			if (!selected[i]) {
				result[idx] = i;
				selected[i] = true;
				perm(idx + 1);
				selected[i] = false;
			}
		}
	}
}
