package boj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2252_줄_세우기 {
	static int N;
	static int[] indegree;
	static ArrayList<Integer>[] adj;
	static ArrayList<Integer> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		adj = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		indegree = new int[N + 1];
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			adj[a].add(b); // a가 b 앞에 서야 한다
			indegree[b]++;
		}

		list = new ArrayList<>();
		topologySort();

		for (int x : list) {
			System.out.print(x + " ");
		}
	}

	private static void topologySort() {
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0)
				queue.add(i);
		}

		while (!queue.isEmpty()) {
			int now = queue.poll();
			list.add(now);

			for (int j : adj[now]) {
				indegree[j]--;
				if (indegree[j] == 0) {
					queue.add(j);
				}
			}
		}
	}
}
