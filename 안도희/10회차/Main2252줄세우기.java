package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main2252줄세우기 {
	static ArrayList<Integer>[] front;
	static boolean visited[];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		front = new ArrayList[n + 1];
		visited = new boolean[n + 1];

		for (int i = 1; i <= n; i++) {
			front[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			front[b].add(a);// b앞에a
		}

		for (int i = 1; i <= n; i++) {
			check(i);
		}
		System.out.println(sb);

	}

	private static void check(int i) {
		if (visited[i])
			return;

		for (int j = 0; j < front[i].size(); j++) {
			int num = front[i].get(j);
			check(num);
			if (!visited[num]) {
				if (sb.length() != 0)
					sb.append(" ");
				sb.append(num);
				visited[num] = true;
			}
		}
		if (sb.length() != 0)
			sb.append(" ");
		sb.append(i);
		visited[i] = true;


	}

}
