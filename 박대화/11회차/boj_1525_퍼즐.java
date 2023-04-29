import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };

	public static int bfs(String start) {
		Queue<String> q = new ArrayDeque<>();
		Map<String, Integer> visited = new HashMap<String, Integer>();

		q.add(start);
		visited.put(start, 1);

		int time = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int s = 0; s < size; s++) {
				String cur = q.poll();

				if (cur.equals("123456780")) {
					return time;
				}

				int zi = 0;
				int zj = 0;

				int idx = cur.indexOf('0');
				zi = idx / 3;
				zj = idx % 3;

				for (int d = 0; d < 4; d++) {
					int nzi = zi + di[d];
					int nzj = zj + dj[d];

					if (nzi >= 0 && nzi < 3 && nzj >= 0 && nzj < 3) {
						int nidx = nzi * 3 + nzj;
						char ch = cur.charAt(nidx);
						String ns = cur.replace(ch, 'x');
						ns = ns.replace('0', ch);
						ns = ns.replace('x', '0');

						if (!visited.containsKey(ns)) {
							q.add(ns);
							visited.put(ns, 1);
						}
					}

				}
			}
			time++;
		}
		return -1;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String arr = "";

		for (int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				arr += st.nextToken();
			}
		}
		// end input

		int result = bfs(arr);

		System.out.println(result);
	}
}
