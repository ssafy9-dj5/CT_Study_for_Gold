package day0310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ_16928_뱀과사다리게임 {
	static int arr[] = new int[101];
	static int board[] = new int[101];
	static boolean isVisited[] = new boolean[101];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		for (int i = 0; i < n + m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			board[a] = b;
		}

		bfs();
	}

	static void bfs() {
		ArrayDeque<Integer> dq = new ArrayDeque<Integer>();
		dq.offer(1);
		arr[1] = 0;
		isVisited[1] = true;

		while (!dq.isEmpty()) {
			int cur = dq.poll();
			if (cur == 100) {
				System.out.println(arr[cur]);
				return;
			}

			for (int i = 1; i <= 6; i++) {
				int x = cur + i;
				if (100 < x || isVisited[x])
					continue;
				isVisited[x] = true;

				if (board[x] != 0) {
					if (!isVisited[board[x]]) {
						dq.offer(board[x]);
						isVisited[board[x]] = true;
						arr[board[x]] = arr[cur] + 1;
					}
				} else {
					dq.offer(x);
					arr[x] = arr[cur] + 1;
				}
			}
		}
	}

}