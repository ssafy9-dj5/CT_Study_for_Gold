package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Main1916�ּҺ�뱸�ϱ� {
	static class Node implements Comparable<Node> {
		int num, weight;

		public Node(int num, int weight) {
			super();
			this.num = num;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());// ������ ����
		int m = Integer.parseInt(br.readLine());// ������ ����
		int inf = Integer.MAX_VALUE;
		int[] cost = new int[n + 1];
		boolean[] visited = new boolean[n + 1];
		Arrays.fill(cost, inf);
		ArrayList<Node>[] list = new ArrayList[n + 1];
		for (int i = 1; i < n + 1; i++) {
			list[i] = new ArrayList<>(); // ����� ���� list add
		}
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list[from].add(new Node(to, weight));
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		queue.add(new Node(start, 0)); // ó�� ������ ����ġ 0���� ����!!
		cost[start] = 0;

		while (!queue.isEmpty()) {
			Node current = queue.poll(); // ���� ���
			if (visited[current.num])
				continue;
			visited[current.num] = true;
			for (Node node : list[current.num]) {
				if (cost[node.num] > cost[current.num] + node.weight) {
					cost[node.num] = cost[current.num] + node.weight;
					queue.add(new Node(node.num, cost[node.num]));
				}

			}

		}
		System.out.println(cost[end]);

	}
}
