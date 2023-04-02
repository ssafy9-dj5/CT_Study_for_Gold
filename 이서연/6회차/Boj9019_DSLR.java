package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj9019_DSLR {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		boolean[] visited;
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			Queue<Result> queue = new ArrayDeque<>();
			queue.add(new Result(A, ""));
			visited = new boolean[10000];
			visited[A] = true;

			while (!queue.isEmpty()) {
				Result now = queue.poll();

				if (now.num == B) {
					System.out.println(now.cmd);
					break;
				}

				int next = D(now.num);
				if (!visited[next]) {
					queue.add(new Result(next, now.cmd + 'D'));
					visited[next] = true;
				}
				next = S(now.num);
				if (!visited[next]) {
					queue.add(new Result(next, now.cmd + 'S'));
					visited[next] = true;
				}
				next = L(now.num);
				if (!visited[next]) {
					queue.add(new Result(next, now.cmd + 'L'));
					visited[next] = true;
				}
				next = R(now.num);
				if (!visited[next]) {
					queue.add(new Result(next, now.cmd + 'R'));
					visited[next] = true;
				}
			}
		}
	}

	static class Result {
		int num;
		String cmd;

		public Result(int num, String cmd) {
			super();
			this.num = num;
			this.cmd = cmd;
		}
	}

	static int D(int n) {
		int num = 2 * n;
		num = num % 10000;

		return num;
	}

	static int S(int n) {
		int num = n == 0 ? 9999 : n - 1;

		return num;
	}

	static int L(int n) {
		int temp = n % 1000;
		int num = temp * 10 + n / 1000;

		return num;
	}

	static int R(int n) {
		int temp = n % 10;
		int num = n / 10 + temp * 1000;
		return num;
	}
}
