package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Boj7662_이중_우선순위_큐 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		char op;
		int K, n;
		StringBuilder sb = new StringBuilder();

		for (int t = 0; t < T; t++) {
			K = Integer.parseInt(br.readLine());
			TreeMap<Integer, Integer> queue = new TreeMap<>();

			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				op = st.nextToken().charAt(0);
				n = Integer.parseInt(st.nextToken());

				if (op == 'I') { // 삽입
					queue.put(n, queue.getOrDefault(n, 0) + 1);
				} else if (op == 'D' && !queue.isEmpty()) { // 삭제
					int key;
					if (n == 1) { // 최댓값 삭제
						key = queue.lastKey();
					} else { // 최솟값 삭제
						key = queue.firstKey();
					}

					if (queue.get(key) == 1)
						queue.remove(key);
					else
						queue.put(key, queue.get(key) - 1);
				}
			}

			if (queue.isEmpty()) {
				sb.append("EMPTY").append("\n");
			} else {
				sb.append(queue.lastKey()).append(" ").append(queue.firstKey()).append("\n");
			}
		}
		System.out.println(sb.toString());
	}
}
