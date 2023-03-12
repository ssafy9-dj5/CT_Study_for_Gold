package day0312;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1717_집합의표현 {
	static int n, m, a, b;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken()); // 집합 개수
		m = Integer.parseInt(st.nextToken()); // 연산 개수
		parents = new int[n+1];
		
		for (int i = 1; i <= n; i++) {
			parents[i] = i;
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int state = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(state == 0) {
				if(findParent(a) != findParent(b)) {
					union(a,b);
				}
			}
			else {
				if(findParent(a) == findParent(b)) {
					System.out.println("YES");
					continue;
				}
				else {
					System.out.println("NO");
					continue;
				}
			}
		}
	}

	private static void union(int a, int b) {
		int rootA = parents[a];
		int rootB = parents[b];

		parents[rootB] = rootA;
		

	}

	private static int findParent(int num) {
		if (parents[num] == num) {
			return num;
		}

		return parents[num] = findParent(parents[num]);

	}

}
