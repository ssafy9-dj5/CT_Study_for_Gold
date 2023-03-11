package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1916_최소비용_구하기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		int[][] adjMatrix = new int[N + 1][N + 1];
		StringTokenizer st;
		int from, to, cost;

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				adjMatrix[i][j] = -1;
				if (i == j)
					adjMatrix[i][j] = 0;
			}
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());

			if (adjMatrix[from][to] == -1)
				adjMatrix[from][to] = cost;
			else
				adjMatrix[from][to] = Math.min(adjMatrix[from][to], cost);
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int goal = Integer.parseInt(st.nextToken());

		int[] distance = new int[N + 1];
		boolean[] visited = new boolean[N + 1];

		for (int i = 1; i <= N; i++) {
			distance[i] = Integer.MAX_VALUE;
		}

		distance[start] = 0;

		while (true) {
			int now = -1;
			int min = Integer.MAX_VALUE;
			for (int i = 1; i <= N; i++) { // 방문하지 않은 정점 중 최소 비용이 가장 적은 것 찾기
				if (distance[i] < min && !visited[i]) {
					min = distance[i];
					now = i;
				}
			}

			if (now == -1)
				break;

			visited[now] = true;

			if (now == goal)
				break;

			for (int j = 1; j <= N; j++) {
				if (!visited[j] && adjMatrix[now][j] != -1 && distance[j] > distance[now] + adjMatrix[now][j]) {
					distance[j] = distance[now] + adjMatrix[now][j];
				}
			}
		}

		System.out.println(distance[goal]);
	}
}
