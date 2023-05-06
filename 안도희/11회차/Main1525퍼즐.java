package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;
//풀이 봤음
public class Main1525퍼즐 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String correct = "123456780"; // 찾아야 하는 문자열
		Queue<String> queue = new LinkedList<String>();
		int di[] = { -1, 1, -3, 3 };
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				sb.append(st.nextToken());
			}
		}

		String init = sb.toString();
		if (!init.equals(correct)) {
			map.put(init, 0);
			queue.add(init);
		}

		else {
			System.out.println(0);
			return;
		}

		while (!queue.isEmpty()) {
			String str = queue.poll();
			int z = str.indexOf("0");// 0의 위치
			for (int d = 0; d < 4; d++) {
				if (z + di[d] < 0 || z + di[d] > 8 || (z % 3 == 0 && d == 0) || (z % 3 == 2 && d == 1))
					continue;

				StringBuilder nsb = new StringBuilder(str);
				char c = nsb.charAt(z + di[d]);
				nsb.setCharAt(z + di[d], '0'); //
				nsb.setCharAt(z, c);

				if (nsb.toString().equals(correct)) {
					System.out.println(map.get(str) + 1);
					return;
				}
				if (!map.containsKey(nsb.toString())) {
					queue.offer(nsb.toString());
					map.put(nsb.toString(), map.get(str) + 1);
				}
			}
		}
		System.out.println(-1);
	}

}
