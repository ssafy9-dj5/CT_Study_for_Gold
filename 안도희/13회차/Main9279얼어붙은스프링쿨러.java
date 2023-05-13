import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main9279얼어붙은스프링쿨러 {
	static boolean[] visited;
	static List<Integer>[] tree;
	static List<Integer>[] edge;
	static List<Integer>[] min;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			if(st.countTokens()==0)
				break;
			int n = Integer.parseInt(st.nextToken());
			int root = Integer.parseInt(st.nextToken());
			tree = new List[n+1];
			edge = new List[n+1];
			min = new List[n+1];
			visited = new boolean[n+1];
			
			for(int i=1;i<=n;i++) {
				tree[i] = new ArrayList<Integer>();
			}
			for(int i=1;i<=n;i++) {
				edge[i] = new ArrayList<Integer>();
			}
			for(int i=1;i<=n;i++) {
				min[i] = new ArrayList<Integer>();
			}
			for(int i=0;i<n-1;i++) {
				st = new StringTokenizer(br.readLine());
				int n1 = Integer.parseInt(st.nextToken());
				int n2 = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				tree[n1].add(n2);
				edge[n1].add(p);
				tree[n2].add(n1);
				edge[n2].add(p);	
			}
			visited[root]=true;
			dfs(root);
			sb.append(min[root].get(0)+"\n");
		}
		System.out.println(sb);
	}
	
	private static void dfs(int node) {
		int m =0;
		for(int i=0;i<tree[node].size();i++) {
			int n = tree[node].get(i);
			if(visited[n]) {
				tree[node].remove(i);
				edge[node].remove(i);
				i--;
			}
			else {
				visited[n]=true;
				dfs(n);
				if(edge[n].size()==0) {
					m+=edge[node].get(i);
				}
				else {
					if(min[n].get(0)<edge[node].get(i))
						m+=min[n].get(0);
					else
						m+=edge[node].get(0);
				}
			}
		}
		min[node].add(m);
	}
	
	
	

}
