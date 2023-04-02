import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static class DSLR {
		int num;
		String oper;

		public DSLR(int num, String oper) {
			this.num = num;
			this.oper = oper;
		}

		@Override
		public String toString() {
			return "DSLR [num=" + num + ", oper=" + oper + "]";
		}
	}

	public static void bfs(int init, int result) {
		Queue<DSLR> q = new ArrayDeque<>();
		boolean[] visited = new boolean[10000];

		q.offer(new DSLR(init, ""));
		visited[init] = true;

		while (!q.isEmpty()) {
			DSLR cur = q.poll();

			if (cur.num == result) {
				System.out.println(cur.oper);
				return;
			}

			int d = (cur.num * 2) % 10000;
			int s = cur.num - 1;
			if (s == -1) {
				s = 9999;
			}
			int l = ((cur.num % 1000) * 10) + (cur.num / 1000);
			int r = ((cur.num % 10) * 1000) + (cur.num / 10);

			if (!visited[d]) {
				q.offer(new DSLR(d, cur.oper + "D"));
				visited[d] = true;
			}

			if (!visited[s]) {
				q.offer(new DSLR(s, cur.oper + "S"));
				visited[s] = true;
			}

			if (!visited[l]) {
				q.offer(new DSLR(l, cur.oper + "L"));
				visited[l] = true;
			}

			if (!visited[r]) {
				q.offer(new DSLR(r, cur.oper + "R"));
				visited[r] = true;
			}

		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tc = Integer.parseInt(br.readLine());
		for (int t = 0; t < tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int init = Integer.parseInt(st.nextToken());
			int result = Integer.parseInt(st.nextToken());

			bfs(init, result);
		}
	}
}
