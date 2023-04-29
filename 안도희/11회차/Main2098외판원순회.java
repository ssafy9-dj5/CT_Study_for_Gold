//시간초과
package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2098외판원순회 {
	static int map[][];
	static boolean visited[];
	static int min = 16000000;
	static int n, start;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < n; i++) {
			start = i;
			visited[i] = true;
			dfs(i, 1, 0);
			visited[i] = false;
		}
		System.out.println(min);

	}

	private static void dfs(int s, int cnt, int result) {
		if (result >= min) 
			return;
		

		if (cnt == n) {
			if (map[s][start] == 0) 
				return;
			
			result += map[s][start];
			if (min > result)
				min = result;
			return;
		}

		for (int i = 0; i < n; i++) {
			if (visited[i])
				continue;
			if (map[s][i] != 0) {
				visited[i] = true;
				dfs(i, cnt + 1, result + map[s][i]);
				visited[i] = false;
			}

		}

	}

}
