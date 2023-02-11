package boj.s3;

import java.util.ArrayList;
import java.util.Scanner;

public class Boj2312_수_복원하기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();

		for (int tc = 0; tc < TC; tc++) {
			int N = sc.nextInt();
			ArrayList<int[]> list = new ArrayList<>();
			int num = N;
			for (int i = 2; i <= N; i++) {
				if (num % i == 0) {
					int count = 0;
					while (true) {
						num /= i;
						count++;
						if (num % i != 0)
							break;
					}
					list.add(new int[] { i, count });
				}
				if (num == 1) // 다 나누어 떨어진 것
					break;
			}
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i)[0] + " " + list.get(i)[1]);
			}
		}
	}
}
