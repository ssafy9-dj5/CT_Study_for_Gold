package boj.s2;

import java.util.Scanner;

public class Boj19621_회의실_배정_2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		Meeting[] m = new Meeting[N];
		for (int i = 0; i < N; i++) {
			m[i] = new Meeting(sc.nextInt(), sc.nextInt(), sc.nextInt());
		}
		int[] d = new int[N];
		d[0] = m[0].num;
		
		if (N > 1) {			
			d[1] = Math.max(d[0], m[1].num);
			
			for (int i = 2; i < N; i++) {
				d[i] = Math.max(d[i - 1], d[i - 2] + m[i].num);
			}
		}

		System.out.println(d[N - 1]);
	}

	static class Meeting { // start, end 변수 사용 안됨
		int start;
		int end;
		int num;

		public Meeting(int start, int end, int num) {
			this.start = start;
			this.end = end;
			this.num = num;
		}
	}
}
