package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main9019DSLR {
	static Queue<Integer> queuen;
	static Queue<String> queues;
	static int b;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());

		for (int t = 0; t < tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			String sb = new String();
			visited = new boolean[10000];
			queuen = new LinkedList<Integer>();
			queues = new LinkedList<String>();

			visited[a]=true;
			queuen.add(a);
			queues.add(sb);

			while (true) {
				int now = queuen.poll();
				String nows = queues.poll();

				// D
				int d = now * 2;
				if (d >= 10000)
					d %= 10000;
				StringBuilder cur = new StringBuilder().append(nows);
				cur.append("D");
				if (check(d, cur))
					break;
				// S
				int s = now - 1;
				if (s == -1)
					s = 9999;
				cur = new StringBuilder().append(nows);
				cur.append("S");
				if (check(s, cur))
					break;
				// L
				int l = (now % 1000) * 10 + (now / 1000);
				cur = new StringBuilder().append(nows);
				cur.append("L");
				if (check(l, cur))
					break;

				// R
				int r = (now % 10) * 1000 + (now / 10);
				cur = new StringBuilder().append(nows);
				cur.append("R");
				if (check(r, cur))
					break;

			}

		}
	}

	private static boolean check(int n, StringBuilder cur) {
		if (n == b) {
			System.out.println(cur);
			return true;
		}
		if (!visited[n]) {
			queuen.add(n);
			queues.add(cur.toString());
			visited[n]=true;
		}
		return false;
	}
}
