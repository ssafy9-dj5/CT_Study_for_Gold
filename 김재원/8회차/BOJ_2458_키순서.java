import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * BOJ_2458_키순서
 */
public class BOJ_2458_키순서 {
	static int n;
	static int m;
	static boolean[] isVisited;
	static ArrayList<ArrayList<Integer>> adjList;
	static ArrayList<ArrayList<Integer>> adjListRev;

	public static void main(String[] args) throws IOException {
		// 내 뒤로 전부 탐색, 단 앞으로 가면 안됨
		// 내 앞으로 전부 탐색, 단 뒤로 가면 안됨
		// DFS로 깊게 탐색해서 탐색 된 노드 갯수를 세서 모든 노드를 방문했으면 키순서를 알 수 있는 학생이다
		// 거의 무방향 그래프와 다를바 없다.

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		adjList = new ArrayList<>();
		adjListRev = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			adjList.add(new ArrayList<>());
			adjListRev.add(new ArrayList<>());
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			adjList.get(start).add(end);
			adjListRev.get(end).add(start);
		}

		isVisited = new boolean[n];
		int res = 0;
		for (int i = 0; i < n; i++) {
			if (dfs(i)) {
				res++;
			}
			isVisited = new boolean[n];
		}
		System.out.println(res);
	}

	static boolean dfs(int idx) {
		isVisited[idx] = true;
		dfs_front(idx);
		dfs_back(idx);
		for (int i = 0; i < isVisited.length; i++) {
			if (!isVisited[i]) {
				return false;
			}
		}
		return true;
	}

	static void dfs_front(int idx) {
		// 나보다 큰애 찾기
		for (int node : adjList.get(idx)) {
			if (isVisited[node])
				continue;
			isVisited[node] = true;
			dfs_front(node);
		}
	}

	static void dfs_back(int idx) {
		// 나보다 작은애 찾기
		for (int node : adjListRev.get(idx)) {
			if (isVisited[node])
				continue;
			isVisited[node] = true;
			dfs_back(node);
		}
	}
}