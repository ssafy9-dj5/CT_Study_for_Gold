package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16928_뱀과_사다리_게임 {
	static Map<Integer, Integer> start_end;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		start_end = new HashMap<>();
		
		int start, end;
		for (int i = 0; i < N + M; i++) {
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			
			start_end.put(start, end);
		}
		
		System.out.println(bfs(1));
	}

	private static int bfs(int start) {
		
		boolean[] visited = new boolean[101];
		
		Queue<Integer> queue = new LinkedList<>();
		visited[start] = true;
		queue.add(start);
		
		int now = 0;
		int size, next;
		int count = 0;
		while (!queue.isEmpty()) {
			size = queue.size();
			
			for (int s=0; s<size; s++) {
				now = queue.poll();

				if (now == 100)
					break;
				
				for (int i = 1; i <= 6; i++) { // 주사위 1 ~ 6
					next = now + i;
					if (start_end.containsKey(next))
						next = start_end.get(next);
					
					if (next <= 100 && !visited[next]) {
						queue.add(next);
						visited[next] = true;
					}
				}
			}
			
			if (now == 100)
				break;
			
			count++;
		}
		
		return count;
	}
}
