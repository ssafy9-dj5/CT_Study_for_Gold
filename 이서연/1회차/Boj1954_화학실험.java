package boj.g1;

import java.util.Scanner;

public class Boj1954_화학실험 {
	static int[] gas;
	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		int[] A = new int[N];
		gas = new int[N];
		for (int i = 0; i < N; i++) {
			A[i] = sc.nextInt();
			gas[i] = sc.nextInt();
		}
		int M = sc.nextInt();

		while (true) {
			int min = getMinValue();
			for (int i = 0; i < N; i++) {
				if (gas[i] == min) {
					gas[i] += A[i]; // 가장 적은 양의 가스 발생시킨 시약에 용액 1 추가(a만큼 가스 발생)
					M--;
				}
			}
			if (M <= 0) // 용액 다 쓰면 종료
				break;
		}

		int answer = gas[0]; // 가스의 양
		if (M == 0) {
			for (int i = 1; i < N; i++) {
				if (answer != gas[i]) { // 가스가 모두 같은 양인지 검사
					answer = 0;
					break;
				}
			}
		} else { // 용액의 양이 음수일때
			answer = 0;
		}
		System.out.println(answer);
	}

	static int getMinValue() {
		int minVal = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			minVal = Math.min(minVal, gas[i]);
		}
		return minVal;
	}
}
