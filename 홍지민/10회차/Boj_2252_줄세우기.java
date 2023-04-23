package day0423;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_2252_줄세우기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] graph = new int[n+1];
		List<Integer>[] list = new ArrayList[n+1];
		for(int i=1;i<=n;i++)
			list[i] = new ArrayList<>();
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			graph[b]++;
		}
		
		Queue<Integer> q = new ArrayDeque<>();
		for(int i=1;i<=n;i++) {
			if(graph[i]==0) q.add(i);
		}
		
		
		while(!q.isEmpty()) {
			int start = q.poll();
			sb.append(start).append(" ");
			
			for(int end : list[start]) {
				graph[end]--;
				if(graph[end]==0) q.add(end);
			}
		}
		
		System.out.println(sb);		
	}
}
