package w1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

class Schedule implements Comparable<Schedule> {
	int day;
	int start;
	int end;

	public Schedule(int day, int start, int end) {
		this.day = day;
		this.start = start;
		this.end = end;
	}

	@Override
	public int compareTo(Schedule sc) {
		if (sc.start < this.start)
			return 1;
		else if (sc.start > this.start)
			return -1;
		else {
			if (sc.end < this.end)
				return 1;
			else if (sc.end < this.end)
				return 1;
			else
				return 0;
		}

	}
}

public class Boj_7507 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int TC = Integer.parseInt(st.nextToken());

		for (int tc = 0; tc < TC; tc++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			Schedule[] sc = new Schedule[m];

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				String sDay = st.nextToken();
				int day = Integer.parseInt(sDay);
				int start = Integer.parseInt(sDay + st.nextToken());
				int end = Integer.parseInt(sDay + st.nextToken());
				sc[i] = new Schedule(day, start, end);
			}
			Arrays.sort(sc);

//			for (int i = 0; i < m; i++)
//				System.out.println(sc[i].start + ", " + sc[i].end);

			// 1번부터 가능한 제일 빨리 끝나는 애들의 조합
			// 지금 보려는 애 보다 시작시간이 지금보는도중이면서 빨리 끝나는 애가 있다면 그걸로
			int cnt = 1; // 왜 값을 + 1 해야할까?
			int present = 0;
			// 현재(present) 이 경기를 보는게 적절한지 판단
			for (int i = 1; i < m; i++) {
				if (sc[i].day > sc[present].day || sc[i].start >= sc[present].end) {
					cnt++;
					present = i;
				} else if (sc[i].end < sc[present].end) {
					present = i;
				}
			}
			System.out.printf("Scenario #%d:\n", tc + 1);
			System.out.println(cnt);
			System.out.println();

		}
	}
}
