import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] dp;
	static int[][] map;
	static int n, p;

	static final int MAX = 17000000; // 하나당 1000000 x 도시 16개 보다 조금 큰 수

	public static int dfs(int num, int visit) {

		if (visit == p - 1) { // 모두 돌았다면
			if (map[num][0] == 0) { // 마지막에서 처음으로 못가는 길이 아니면
				return MAX;
			}
			return map[num][0];
		}

		if (dp[num][visit] != -1) { //
			return dp[num][visit];
		}

		dp[num][visit] = MAX; //갔다고 체크

		for (int i = 0; i < n; i++) {
			if ((visit & (1 << i)) == 0 && map[num][i] != 0) { // i가 현재 있는 도시가 아니면서 방문한 적이 없고
																// 갈 수 있는 도시라면
				dp[num][visit] = Math.min(dp[num][visit], dfs(i, visit | (1 << i)) + map[num][i]); // 최소 비용 갱신

			}
		}
		return dp[num][visit];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		p = (1 << n);

		map = new int[n][n];
		dp = new int[n][p - 1];

		for (int i = 0; i < n; i++) { // -1로 초기화 해주는 이유 - 한번 갔다가 못가는 경우와 처음가는 경우가 똑같이 MAX로 되어 있기 때문에 또 확인함....
										// 내리막길에서도 당했던 건데...
			Arrays.fill(dp[i], -1);
		}

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// end input

		int result = dfs(0, 1);

		System.out.println(result);
	}
}
