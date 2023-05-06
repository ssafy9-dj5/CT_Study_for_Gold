package day0430;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_1525_∆€¡Ò {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder start = new StringBuilder();

		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				String temp = st.nextToken();
				start.append(temp);
			}
		}

		Queue<String> q = new ArrayDeque<>();
		Map<String, Integer> m = new HashMap<>();
		q.offer(start.toString());
		m.put(start.toString(), 0);

		while (!q.isEmpty()) {
			String now = q.poll();
			int zero = now.indexOf("0");
			int r = zero / 3;
			int c = zero % 3;

			for (int i = 0; i < 4; i++) {
				int nr = dr[i] + r;
				int nc = dc[i] + c;
				int move = nr * 3 + nc;
				
				if (nr >= 0 && nr < 3 && nc >= 0 && nc < 3) {
					StringBuilder next = new StringBuilder(now);
					char temp = next.charAt(move);
					next.setCharAt(zero, temp);
					next.setCharAt(move, '0');
					String nextStr = next.toString();
					
					if (!m.containsKey(nextStr)) {
						q.offer(nextStr);
						m.put(nextStr, m.get(now) + 1);
					}
				}
			}
		}

		if (m.containsKey("123456780")) {
			System.out.println(m.get("123456780"));
		} else {
			System.out.println(-1);
		}

	}

}
