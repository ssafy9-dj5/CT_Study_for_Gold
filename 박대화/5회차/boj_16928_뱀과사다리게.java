import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static Map<Integer, Integer> map;
	static int n, m;

	public static int bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[101];

		q.offer(start);
		visited[start] = true;

		int time = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int s = 0; s < size; s++) {
				int cur = q.poll();

				if (cur == 100) {
					return time;
				}

				for (int i = 1; i <= 6; i++) {
					if (cur + i <= 100 && !visited[cur + i]) {
						if (map.containsKey(cur + i)) {
							q.offer(map.get(cur + i));
							visited[cur + i] = true;
							visited[map.get(cur + i)] = true;
						} else {
							q.offer(cur + i);
							visited[cur + i] = true;
						}
					}
				}
			}
			time++;
		}
		return time;
	}

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new HashMap<>();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());

			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			map.put(start, end);
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());

			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			map.put(start, end);
		}
		// end input

		int result = bfs(1);
		System.out.println(result);
	}
}