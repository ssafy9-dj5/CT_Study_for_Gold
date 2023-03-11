import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static ArrayList<Pos>[] al;
	static int[] distance;
	static boolean[] visited;
	static int end;

	public static class Pos implements Comparable<Pos> {
		int to;
		int weight;

		public Pos(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Pos o) {
			return this.weight - o.weight;
		}

		@Override
		public String toString() {
			return "Pos [to=" + to + ", weight=" + weight + "]";
		}
	}

	public static int dijkstra(int start) {
		PriorityQueue<Pos> pq = new PriorityQueue<>();
		visited = new boolean[n + 1];
		distance = new int[n + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;

		pq.offer(new Pos(start, 0));

		while (!pq.isEmpty()) {
			Pos cur = pq.poll();

			if (visited[cur.to])
				continue;

			visited[cur.to] = true;

			if (cur.to == end) {
				return cur.weight;
			}

			for (Pos p : al[cur.to]) {
				if (!visited[p.to] && distance[p.to] > distance[cur.to] + p.weight) {
					distance[p.to] = distance[cur.to] + p.weight;
					pq.offer(new Pos(p.to, distance[p.to]));
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		al = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			al[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			al[from].add(new Pos(to, weight));
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		int result = dijkstra(start);

		System.out.println(result);

		br.close();
	}
}