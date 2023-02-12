package boj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Boj7507_올림픽_게임 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int M = Integer.parseInt(br.readLine());
			Game[] games = new Game[M];
			int days = 0;
			for (int m = 0; m < M; m++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				games[m] = new Game(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()));
				days = Math.max(days, games[m].day); // 경기가 며칠까지 있는지
			}

			Arrays.sort(games, new Comparator<Game>() { // 경기를 날짜순, 종료 시간순으로 정렬
				@Override
				public int compare(Game o1, Game o2) {
					if (o1.day == o2.day)
						return o1.end - o2.end;
					return o1.day - o2.day;
				}
			});

			int count = 0; // 볼 수 있는 총 경기 수
			int[] end_time = new int[days + 1]; // 날짜별 경기 종료 시간 저장
			for (int i = 0; i < M; i++) {
				int day = games[i].day;
				if (games[i].start >= end_time[day]) { // 시간이 겹치지 않으면
					end_time[day] = games[i].end;
					count++;
				}
			}

			System.out.println("Scenario #" + t + ":\n" + count + "\n");
		}
	}

	static class Game {
		int day, start, end;

		public Game(int day, int start, int end) {
			this.day = day;
			this.start = start;
			this.end = end;
		}
	}
}
