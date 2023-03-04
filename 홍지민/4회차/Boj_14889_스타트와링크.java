
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_14889_스타트와링크 {
	static int n;
	static int[][] team;
	static boolean[] visited;
	static int[] teamS;
	static int min;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());

		team = new int[n][n];
		visited = new boolean[n];
		teamS = new int[n/2];
		min = Integer.MAX_VALUE;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				team[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		comb(0, 0);
		System.out.println(min);

	}

	private static void comb(int cnt, int start) {
		if (cnt == n / 2) {
			int s = 0;
			int l = 0;
			for (int i = 0; i < teamS.length - 1; i++) {
				for (int j = i; j < teamS.length; j++) {
					s += team[teamS[i]][teamS[j]] + team[teamS[j]][teamS[i]];
				}
			}

			int[] teamL = new int[n/2];
			int idx = 0;
			for(int i=0; i<n; i++) {
				if(!visited[i]) {
					teamL[idx++] = i;
				}
			}
			
			for (int i = 0; i < teamL.length - 1; i++) {
				for (int j = i; j < teamL.length; j++) {
					l += team[teamL[i]][teamL[j]] + team[teamL[j]][teamL[i]];
				}
			}
			
			min = Math.min(min, Math.abs(s-l));
			return;
			
		}

		for (int i = start; i < n; i++) {
			visited[i] = true;
			teamS[cnt] = i;
			comb(cnt + 1, i + 1);
			visited[i] = false;
		}

	}
}
